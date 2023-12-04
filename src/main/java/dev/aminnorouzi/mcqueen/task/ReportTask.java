package dev.aminnorouzi.mcqueen.task;

import dev.aminnorouzi.mcqueen.event.ReportNotificationEvent;
import dev.aminnorouzi.mcqueen.model.report.Report;
import dev.aminnorouzi.mcqueen.service.ReportService;
import dev.aminnorouzi.mcqueen.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class ReportTask {

    private final ReportService reportService;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final DateUtil dateUtil;

    @Scheduled(cron = "0 15 0 * * *", zone = "UTC")
    public void getDailyReport() {
        Instant yesterday = dateUtil.yesterday();

        Report report = reportService.generate(yesterday);
        applicationEventPublisher.publishEvent(new ReportNotificationEvent(report));
    }
}
