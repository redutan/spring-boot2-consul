package com.example.edge;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class Page {
    public static final Page EMPTY = new Page(null, null, null);
    private Long pageId;
    private String title;
    private String content;

    @SuppressWarnings("WeakerAccess")
    public Page(@JsonProperty("pageId") Long pageId,
                @JsonProperty("title") String title,
                @JsonProperty("content") String content) {
        this.pageId = pageId;
        this.title = title;
        this.content = content;
    }
}
