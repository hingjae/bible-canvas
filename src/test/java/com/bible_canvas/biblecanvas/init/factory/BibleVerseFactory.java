package com.bible_canvas.biblecanvas.init.factory;

import com.bible_canvas.biblecanvas.bible.BibleVerse;

public class BibleVerseFactory {
    public static BibleVerse of(String shortenTitle, int chapter, int verse, String subTitle, String content) {
        return BibleVerse.builder()
                .shortenTitle(shortenTitle)
                .chapter(chapter)
                .verse(verse)
                .subtitle(subTitle)
                .content(content)
                .build();
    }
}
