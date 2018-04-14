package com.example.wiki.page;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

/**
 * 위키 페이지 Entity
 */
@Entity
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Page {
    @Id
    @GeneratedValue
    private Long pageId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_page_id")
    @JsonIgnore
    private Page parentPage;
    private String title;
    @Lob
    private String content;

    public static Page forTopPage(String title, String content) {
        return new Page(null, null, title, content);
    }
}
