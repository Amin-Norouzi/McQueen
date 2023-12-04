package dev.aminnorouzi.mcqueen.service;

import dev.aminnorouzi.mcqueen.model.job.Job;
import dev.aminnorouzi.mcqueen.model.job.Status;
import dev.aminnorouzi.mcqueen.model.report.Report;
import dev.aminnorouzi.mcqueen.model.report.ReportRequest;
import dev.aminnorouzi.mcqueen.model.report.Type;
import dev.aminnorouzi.mcqueen.model.user.User;
import dev.aminnorouzi.mcqueen.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;
    private final JobService jobService;
    private final UserService userService;

    @Value("${telegram.bot.user-id}")
    private Long defaultUserId;

    public Report save(ReportRequest request) {
        Report report = Report.builder()
                .total(request.getJobs().size())
                .submitted(request.getSubmitted().size())
                .rejected(request.getRejected().size())
                .hero(request.getHero())
                .type(Type.DAILY)
                .date(request.getDate())
                .build();

        Report saved = reportRepository.save(report);

        log.info("Saved new report: {}", saved);
        return saved;
    }

    public Report generate(Instant date) {
        List<Job> jobs = jobService.report(date);
        List<Job> submitted = jobService.report(date, Status.SUBMITTED);
        List<Job> rejected = jobService.report(date, Status.REJECTED);

        User hero = who(jobs);

        ReportRequest request = new ReportRequest(jobs, submitted, rejected, hero, date);
        return save(request);
    }

    // TODO Auto-generated method stub
    public Report find() {
        return null;
    }

    public User who(List<Job> jobs) {
        Long heroId = jobs.stream()
                .filter(job -> !job.getStatus().equals(Status.POSTED))
                .collect(Collectors.groupingBy(Job::getUserId, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        if (heroId != null) {
            return userService.get(heroId);
        }

        return userService.get(defaultUserId);
    }
}
