package dev.aminnorouzi.mcqueen.task;

import dev.aminnorouzi.mcqueen.client.JobClient;
import dev.aminnorouzi.mcqueen.event.NotificationEvent;
import dev.aminnorouzi.mcqueen.model.rss.Item;
import dev.aminnorouzi.mcqueen.model.job.Job;
import dev.aminnorouzi.mcqueen.model.rss.Rss;
import dev.aminnorouzi.mcqueen.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JobTask {

    private final JobClient jobClient;
    private final JobService jobService;
    private final ApplicationEventPublisher applicationEventPublisher; // q=java+spring
                                                                        // sort=recency hourly_rate=20-
    @Scheduled(cron = "0 */1 * * * ?")                                  // job_type=hourly verified_payment_only=1
    public void getJobsFromRss() {
        List<Item> items;
        try {
            Rss rss = jobClient.getJobs("");
            items = new ArrayList<>(rss.getItems()); // get all jobs with the filters (list of filters)
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        List<Job> jobs = jobService.separate(items);
        applicationEventPublisher.publishEvent(new NotificationEvent(jobs));
    }
}
