package dev.aminnorouzi.mcqueen.client;

import dev.aminnorouzi.mcqueen.model.rss.Rss;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class JobClient {

    public Rss getJobs(String rss) throws URISyntaxException {
        String url = "https://api.rss2json.com/v1/api.json?rss_url=" + URLEncoder.encode(rss, StandardCharsets.UTF_8);

        RestTemplate template = new RestTemplate();
        ResponseEntity<Rss> response = template.getForEntity(new URI(url), Rss.class);

        return response.getBody();
    }

}
