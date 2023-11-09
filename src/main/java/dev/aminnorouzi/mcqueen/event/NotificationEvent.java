package dev.aminnorouzi.mcqueen.event;

import dev.aminnorouzi.mcqueen.model.job.Job;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.util.List;

@Getter
@Setter
public class NotificationEvent extends ApplicationEvent {

    private List<Job> jobs;

    public NotificationEvent(List<Job> jobs) {
        super(jobs);
        this.jobs = jobs;
    }
}
