package dev.aminnorouzi.mcqueen.service;

import dev.aminnorouzi.mcqueen.model.job.Job;
import dev.aminnorouzi.mcqueen.model.job.Status;
import dev.aminnorouzi.mcqueen.model.rss.Item;
import dev.aminnorouzi.mcqueen.repository.JobRepository;
import dev.aminnorouzi.mcqueen.util.DateUtil;
import dev.aminnorouzi.mcqueen.util.JobUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
    private final JobUtil jobUtil;
    private final DateUtil dateUtil;

    public Job save(Item item) {
        String title = jobUtil.simplify(item.getTitle());
        String description = jobUtil.shorten(item.getDescription());
        String upworkId = jobUtil.extractId(item.getLink());
        Date postedAt = dateUtil.convert(item.getPubDate());
        Map<String, String> metadata = jobUtil.extractMetadata(item.getDescription());

        Job job = Job.builder()
                .upworkId(upworkId)
                .url(item.getLink())
                .title(title)
                .description(description)
                .salary(metadata.get("Hourly Range"))
                .country(metadata.get("Country"))
                .category(metadata.get("Category"))
                .skills(metadata.get("Skills"))
                .status(Status.POSTED)
                .postedAt(postedAt)
                .build();

        Job saved = jobRepository.save(job);

        log.info("Saved new job: {}", saved);
        return saved;
    }

    public List<Job> separate(List<Item> items) {
        List<Job> newJobs = new ArrayList<>();
        Date startDate = dateUtil.getStartDate();

        for (Item item : items) {
            if (!dateUtil.convert(item.getPubDate()).after(startDate)) {
                System.out.println(item.getTitle() + " is outdated.");
                continue;
            }

            String upworkId = jobUtil.extractId(item.getLink());
            if (!exists(upworkId)) {
                newJobs.add(save(item));
            }
        }

        return newJobs;
    }

    public List<Job> report(Date date, Status status) {
        List<Job> jobs = jobRepository.findByStatus(status).stream()
                .filter(job -> DateUtils.isSameDay(job.getCreatedAt(), date))
                .toList();

        log.info("Reported jobs: status, {}, date={}, {}", status, date, jobs);
        return jobs;
    }

    public boolean exists(String upworkId) {
        return jobRepository.existsByUpworkId(upworkId);
    }

    public Job assign(String upworkId, Long userId) {
        Job job = getByUpworkId(upworkId);
        job.setUserId(userId);

        Job assigned = jobRepository.save(job);

        log.info("Assigned a job: {}", assigned);
        return assigned;
    }

    public Job update(String upworkId, String status) {
        Job job = getByUpworkId(upworkId);
        job.setStatus(Status.find(status));

        Job submitted = jobRepository.save(job);

        log.info("Submitted a job: {}", submitted);
        return submitted;
    }

    public Job getByUpworkId(String upworkId) {
        Job found = jobRepository.findByUpworkId(upworkId)
                .orElseThrow(() -> new RuntimeException("Job not found!"));

        log.info("Found a job by upworkId: {}", found);
        return found;
    }
}
