package dev.aminnorouzi.mcqueen;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@SpringBootApplication
public class McQueenApplication {

    public static void main(String[] args) {
        SpringApplication.run(McQueenApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            try {
                Document doc = connect("https://www.upwork.com/nx/jobs/search/?q=java%20spring&sort=recency&t=0&payment_verified=1&hourly_rate=20-&per_page=10");

                int count = 1;

                for (Element element :doc.select(".job-tile-list section")) {
                 String title = element.select("my-0.p-sm-right.job-tile-title span").text();
                    System.out.println(count + ". " + title + "\n");

                    count++;
                }

            } catch (RuntimeException exception) {
                System.err.println(exception.getMessage());
            }
        };
    }

    public Document connect(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
//            throw new RuntimeException(e);
        }
    }

}
