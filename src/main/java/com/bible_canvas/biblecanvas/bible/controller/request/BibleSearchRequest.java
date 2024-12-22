package com.bible_canvas.biblecanvas.bible.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class BibleSearchRequest {
    @NotBlank
    @Size(min = 2)
    private final String keyword;

    public BibleSearchRequest(String keyword) {
        this.keyword = keyword;
    }
}
