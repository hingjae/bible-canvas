package com.bible_canvas.biblecanvas.bible.controller;

import com.bible_canvas.biblecanvas.IntegrationTest;
import com.bible_canvas.biblecanvas.bible.BibleVerse;
import com.bible_canvas.biblecanvas.bible.controller.response.BibleVerseResponse;
import com.bible_canvas.biblecanvas.bible.repository.BibleVerseRepository;
import com.bible_canvas.biblecanvas.bible.service.BibleVerseService;
import com.bible_canvas.biblecanvas.init.factory.BibleVerseFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class BibleControllerTest extends IntegrationTest {

    @Autowired
    BibleVerseRepository bibleVerseRepository;
    @Autowired
    BibleVerseService bibleVerseService;

    private void init() {
        List<BibleVerse> bibleVerses = BibleVerseFactory.mockBibleVerses();
        bibleVerseRepository.saveAll(bibleVerses);
    }

    @AfterEach
    public void clear() {
        bibleVerseRepository.deleteAllInBatch();
    }

    @Test
    void 검색페이지() throws Exception {
        init();

        String keyword = "하나님";
        String page = "0";
        String size = "5";

        Page<BibleVerseResponse> results = bibleVerseService.searchByContent(
                keyword, PageRequest.of(Integer.parseInt(page), Integer.parseInt(size)));

        mockMvc.perform(get("/bible-verse/search")
                        .queryParam("keyword", keyword)
                        .param("page", page)
                        .param("size", size)
                ).andExpect(status().isOk())
                .andExpect(view().name("search-results"))
                .andExpect(model().attribute("keyword", keyword))
                .andExpect(model().attributeExists("results"));
    }

    @Test
    void 키워드는_두글자_이상이어야한다() {

    }
}