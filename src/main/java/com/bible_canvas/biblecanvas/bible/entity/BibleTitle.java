package com.bible_canvas.biblecanvas.bible.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class BibleTitle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String shortenTitle;

    public static BibleTitle of(String title, String shortenTitle) {
        return BibleTitle.builder()
                .title(title)
                .shortenTitle(shortenTitle)
                .build();
    }

    @Builder
    public BibleTitle(String title, String shortenTitle) {
        this.title = title;
        this.shortenTitle = shortenTitle;
    }
}
