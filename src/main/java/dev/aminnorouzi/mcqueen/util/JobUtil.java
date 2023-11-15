package dev.aminnorouzi.mcqueen.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class JobUtil {

    private static final String SIMPLIFY_WORD = "- Upwork";
    private static final String METADATA_KEYWORD = "<b>Hourly Range</b>";
    private static final String URL_CHAR = "_%7";
    private static final int SHORT_STRING_LENGTH = 35;
    private static final Pattern METADATA_PATTERN = Pattern.compile("<b>([^<]+)</b>:\\s*([^<]+)");


    public String simplify(String str) {
        return str.replace(SIMPLIFY_WORD, "")
                .replaceAll("-", "").trim();
    }

    public String extractId(String url) {
        return url.split(URL_CHAR)[1]
                .replace("?source=rss", "").trim();
    }

    public String shorten(String str) {
        String extracted = str.split(METADATA_KEYWORD)[0]
                .replaceAll("<br />|<br>|<b>|</b>|\n", "").trim();

        String[] words = extracted.split("\\s+");
        if (words.length <= SHORT_STRING_LENGTH) {
            return extracted;
        } else {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < SHORT_STRING_LENGTH; i++) {
                builder.append(words[i]).append(" ");
            }

            return builder.toString()
                    .trim() + "...";
        }
    }

    public Map<String, String> extractMetadata(String desc) {
        Map<String, String> metadata = new HashMap<>();

        int startIndex = desc.indexOf(METADATA_KEYWORD);
        if (startIndex != -1) {
            String extractedText = desc.substring(startIndex);

            Matcher matcher = METADATA_PATTERN.matcher(extractedText);
            while (matcher.find()) {
                String key = matcher.group(1);
                String value = matcher.group(2).trim();
                metadata.put(key, value);
            }

            if (metadata.get("Country") == null) {
                metadata.put("Country", "unknown");
            }

            String category = metadata.get("Category").replace("&amp;", "&");
            metadata.put("Category", category);

            if (metadata.get("Skills") != null) {
                StringBuilder builder = new StringBuilder();
                for (String skill : metadata.get("Skills").split(",")) {
                    builder.append(skill.trim())
                            .append(", ");
                }

                String formattedSkills = builder.toString();
                int lastCommaIndex = formattedSkills.lastIndexOf(",");

                String skills = formattedSkills.substring(0, lastCommaIndex) + formattedSkills.substring(lastCommaIndex + 1);
                metadata.put("Skills", skills);
            } else {
                metadata.put("Skills", "no skills");
            }
        }

        return metadata;
    }
}
