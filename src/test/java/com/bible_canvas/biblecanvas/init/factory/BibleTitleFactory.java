package com.bible_canvas.biblecanvas.init.factory;

import com.bible_canvas.biblecanvas.bible.entity.BibleTitle;

import java.util.List;

public class BibleTitleFactory {
    public static BibleTitle of(String title, String shortenTitle) {
        return BibleTitle.builder()
                .title(title)
                .shortenTitle(shortenTitle)
                .build();
    }
}
