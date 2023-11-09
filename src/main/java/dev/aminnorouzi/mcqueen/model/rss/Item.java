package dev.aminnorouzi.mcqueen.model.rss;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Item {

    @JsonProperty("title")
    private String title;

    @JsonProperty("pubDate")
    private String pubDate;

    @JsonProperty("link")
    private String link;

    @JsonProperty("guid")
    private String guid;

    @JsonProperty("author")
    private String author;

    @JsonProperty("thumbnail")
    private String thumbnail;

    @JsonProperty("description")
    private String description;

    @JsonProperty("content")
    private String content;

    @Valid
    @JsonProperty("categories")
    private List<Object> categories;
}