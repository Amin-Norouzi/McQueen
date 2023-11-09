package dev.aminnorouzi.mcqueen.model.rss;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Rss {

    @JsonProperty("status")
    private String status;

    @JsonProperty("feed")
    private Feed feed;

    @JsonProperty("items")
    private List<Item> items;
}