package dev.aminnorouzi.mcqueen.task;

import dev.aminnorouzi.mcqueen.client.JobClient;
import dev.aminnorouzi.mcqueen.event.NotificationEvent;
import dev.aminnorouzi.mcqueen.model.job.Job;
import dev.aminnorouzi.mcqueen.model.rss.Item;
import dev.aminnorouzi.mcqueen.model.rss.Rss;
import dev.aminnorouzi.mcqueen.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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
    private final ApplicationEventPublisher applicationEventPublisher;

    @Value("${rss.client.url}")
    private String url;

    @Value("#{'${rss.client.keywords}'.split(',')}")
    private List<String> keywords;

    @Scheduled(cron = "0 */1 * * * ?")
    public void getJobsFromRss() throws URISyntaxException {
        List<Item> items = new ArrayList<>();

        for (String keyword : keywords) {
            Rss rss = jobClient.getJobs(url.formatted(keyword));
            items.addAll(rss.getItems());
        }

        List<Job> jobs = jobService.separate(items);
        applicationEventPublisher.publishEvent(new NotificationEvent(jobs));
    }
}
