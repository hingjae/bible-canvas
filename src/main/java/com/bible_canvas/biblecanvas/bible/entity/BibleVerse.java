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

    @JoinColumn(name = "bible_title_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private BibleTitle bibleTitle;

    private int chapter;

    private int verse;

    private String subtitle;

    @Column(length = 1000, nullable = false)
    private String content;

    @Builder
    public BibleVerse(BibleTitle bibleTitle, int chapter, int verse, String subtitle, String content) {
        this.bibleTitle = bibleTitle;
        this.chapter = chapter;
        this.verse = verse;
        this.subtitle = subtitle;
        this.content = content;
    }

    public String getShortenTitle() {
        return bibleTitle.getShortenTitle();
    }
}
