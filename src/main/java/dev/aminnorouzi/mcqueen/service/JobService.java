package dev.aminnorouzi.mcqueen.service;

import dev.aminnorouzi.mcqueen.model.Item;
import dev.aminnorouzi.mcqueen.model.Job;
import dev.aminnorouzi.mcqueen.repository.JobRepository;
import dev.aminnorouzi.mcqueen.util.JobUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
    private final JobUtil jobUtil;

    public Job save(Item item) {
        String title = jobUtil.simplify(item.getTitle());
        String description = jobUtil.shorten(item.getDescription());
        Long upworkId = jobUtil.extract(item.getLink());

        Job job = Job.builder()
                .upworkId(upworkId)
                .url(item.getLink())
                .title(title)
                .description(description)
                .build();

        Job saved = jobRepository.save(job);

        log.info("Saved new job: {}", saved);
        return saved;
    }
}
