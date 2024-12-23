package com.bible_canvas.biblecanvas.bible.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BibleVerse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String shortenTitle;

    private int chapter;

    private int verse;

    private String subtitle;

    @Column(length = 1000, nullable = false)
    private String content;

    @Builder
    public BibleVerse(Long id, String shortenTitle, int chapter, int verse, String subtitle, String content) {
        this.id = id;
        this.shortenTitle = shortenTitle;
        this.chapter = chapter;
        this.verse = verse;
        this.subtitle = subtitle;
        this.content = content;
    }
}
