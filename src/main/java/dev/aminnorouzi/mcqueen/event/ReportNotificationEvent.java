package dev.aminnorouzi.mcqueen.event;

import dev.aminnorouzi.mcqueen.model.report.Report;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class ReportNotificationEvent extends ApplicationEvent {

    private Report report;

    public ReportNotificationEvent(Report report) {
        super(report);
        this.report = report;
    }
}
