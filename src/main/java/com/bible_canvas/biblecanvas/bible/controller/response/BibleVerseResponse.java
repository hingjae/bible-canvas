package com.bible_canvas.biblecanvas.bible.controller.response;

import com.bible_canvas.biblecanvas.bible.entity.BibleVerse;
import lombok.Getter;

@Getter
public class BibleVerseResponse {
    private final Long id;

    private final String shortenTitle;

    private final int chapter;

    private final int verse;

    private final String subtitle;

    private final String content;

    public BibleVerseResponse(BibleVerse entity) {
        this.id = entity.getId();
        this.shortenTitle = entity.getShortenTitle();
        this.chapter = entity.getChapter();
        this.verse = entity.getVerse();
        this.subtitle = entity.getSubtitle();
        this.content = entity.getContent();
    }
}
