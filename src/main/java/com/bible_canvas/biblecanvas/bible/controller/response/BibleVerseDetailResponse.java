package com.bible_canvas.biblecanvas.bible.controller.response;

import com.bible_canvas.biblecanvas.bible.entity.BibleVerse;
import lombok.Getter;

@Getter
public class BibleVerseDetailResponse {
    private final Long id;

    private final String titleChapterVerse;

    private final String content;

    public BibleVerseDetailResponse(BibleVerse entity) {
        this.id = entity.getId();
        this.titleChapterVerse = String.format("%s %s:%s", entity.getBibleTitle().getTitle(), entity.getChapter(), entity.getVerse());
        this.content = entity.getContent();
    }
}
