package dev.aminnorouzi.mcqueen.util;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class DateUtil {

    public Instant now() {
        return Instant.now();
    }

    public Instant convert(Date date) {
        return date.toInstant();
    }

    public Instant yesterday() {
        Instant now = Instant.now();
        return now.minus(1, ChronoUnit.DAYS);
    }

    public Instant getStartDate() {
        return Instant.parse("2023-11-27T00:00:00Z");
    }

    public boolean verify(Date date) {
        Instant entryDate = convert(date);
        return entryDate.isAfter(getStartDate());
    }
}
