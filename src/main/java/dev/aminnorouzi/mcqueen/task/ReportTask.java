package dev.aminnorouzi.mcqueen.task;

import dev.aminnorouzi.mcqueen.event.ReportNotificationEvent;
import dev.aminnorouzi.mcqueen.model.job.Job;
import dev.aminnorouzi.mcqueen.model.job.Status;
import dev.aminnorouzi.mcqueen.model.report.Report;
import dev.aminnorouzi.mcqueen.model.user.User;
import dev.aminnorouzi.mcqueen.service.JobService;
import dev.aminnorouzi.mcqueen.service.UserService;
import dev.aminnorouzi.mcqueen.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ReportTask {

    private final JobService jobService;
    private final UserService userService;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final DateUtil dateUtil;

    @Scheduled(cron = "0 15 0 * * *", zone = "UTC")
    public void getDailyReport() {
        Instant yesterday = dateUtil.yesterday();

        List<Job> jobs = jobService.report(yesterday);
        List<Job> submitted = jobService.report(yesterday, Status.SUBMITTED);
        List<Job> rejected = jobService.report(yesterday, Status.REJECTED);

        Long heroId = jobs.stream()
                .filter(job -> !job.getStatus().equals(Status.POSTED))
                .collect(Collectors.groupingBy(Job::getUserId, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        User hero;
        if (heroId != null) {
            hero = userService.get(heroId);
        } else {
            hero = User.builder()
                    .username(" no data available")
                    .build();
        }

        Report report = Report.builder()
                .total(jobs.size())
                .submitted(submitted.size())
                .rejected(rejected.size())
                .hero(hero)
                .date(yesterday)
                .build();

        applicationEventPublisher.publishEvent(new ReportNotificationEvent(report));
    }
}
