package dev.aminnorouzi.mcqueen.util;

import org.springframework.stereotype.Component;

@Component
public class JobUtil {

    private static final String SIMPLIFY_WORD = "- Upwork";
    private static final String URL_CHAR = "_~";
    private static final int SHORT_STRING_LENGTH = 25;


    public String simplify(String str) {
        return str.replace(SIMPLIFY_WORD, "")
                .replaceAll("-", "").trim();
    }

    public String extract(String url) {
       return url.split(URL_CHAR)[1]
                .replace("?source=rss", "").trim();
    }

    public String shorten(String str) {
        str = str.replaceAll("<br>", "");

        String[] words = str.split("\\s+");
        if (words.length <= SHORT_STRING_LENGTH) {
            return str;
        } else {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < SHORT_STRING_LENGTH; i++) {
                builder.append(words[i]).append(" ");
            }
            return builder.toString()
                    .trim() + "...";
        }
    }
}
