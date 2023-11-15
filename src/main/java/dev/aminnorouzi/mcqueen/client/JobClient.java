package dev.aminnorouzi.mcqueen.client;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Component
public class JobClient {

    public List<SyndEntry> getJobs(String url) {
        RestTemplate template = new RestTemplate();
        SyndFeed feed = template.execute(url, HttpMethod.GET, null, response -> {
            SyndFeedInput input = new SyndFeedInput();
            try {
                return input.build(new XmlReader(response.getBody()));
            } catch (FeedException e) {
                throw new IOException("Could not parse response!", e);
            }
        });

        return feed.getEntries();
    }

}
