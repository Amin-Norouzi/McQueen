package dev.aminnorouzi.mcqueen.task;

import dev.aminnorouzi.mcqueen.client.JobClient;
import dev.aminnorouzi.mcqueen.event.JobNotificationEvent;
import dev.aminnorouzi.mcqueen.model.job.Job;
import dev.aminnorouzi.mcqueen.model.rss.Item;
import dev.aminnorouzi.mcqueen.model.rss.Rss;
import dev.aminnorouzi.mcqueen.service.JobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
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

    @Scheduled(cron = "0 */10 * * * ?")
    public void getJobsFromRss() throws URISyntaxException {
        log.info("Searching for new jobs...");

        List<Item> items = new ArrayList<>();

        for (String keyword : keywords) {
            String link = url.replace("keyword", keyword);
            Rss rss = jobClient.getJobs(link);
            items.addAll(rss.getItems());
        }

        List<Job> jobs = jobService.separate(items);
        applicationEventPublisher.publishEvent(new JobNotificationEvent(jobs));
    }
}
