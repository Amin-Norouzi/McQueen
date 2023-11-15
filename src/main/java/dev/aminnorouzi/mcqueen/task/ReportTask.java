package dev.aminnorouzi.mcqueen.task;

import dev.aminnorouzi.mcqueen.event.ReportNotificationEvent;
import dev.aminnorouzi.mcqueen.model.job.Job;
import dev.aminnorouzi.mcqueen.model.job.Status;
import dev.aminnorouzi.mcqueen.model.report.Report;
import dev.aminnorouzi.mcqueen.model.user.User;
import dev.aminnorouzi.mcqueen.service.JobService;
import dev.aminnorouzi.mcqueen.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ReportTask {

    private final JobService jobService;
    private final UserService userService;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Scheduled(cron = "0 59 23 * * ?")
    public void getDailyReport() {
        Date today = new Date();
        List<Job> jobs = jobService.report(today, Status.POSTED);
        List<Job> submitted = jobService.report(today, Status.SUBMITTED);
        List<Job> rejected = jobService.report(today, Status.REJECTED);

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
                .date(today)
                .build();

        applicationEventPublisher.publishEvent(new ReportNotificationEvent(report));
    }
}
