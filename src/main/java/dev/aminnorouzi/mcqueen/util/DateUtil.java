package dev.aminnorouzi.mcqueen.util;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateUtil {

    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Date convert(String value) {
        try {
            return formatter.parse(value);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public String now() {
        return formatter.format(new Date());
    }
}