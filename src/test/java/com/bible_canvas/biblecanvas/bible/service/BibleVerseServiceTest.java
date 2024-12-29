package com.bible_canvas.biblecanvas.bible.service;

import com.bible_canvas.biblecanvas.IntegrationTest;
import com.bible_canvas.biblecanvas.bible.controller.response.BibleVerseDetailResponse;
import com.bible_canvas.biblecanvas.bible.entity.BibleTitle;
import com.bible_canvas.biblecanvas.bible.entity.BibleVerse;
import com.bible_canvas.biblecanvas.bible.repository.BibleTitleRepository;
import com.bible_canvas.biblecanvas.bible.repository.BibleVerseRepository;
import com.bible_canvas.biblecanvas.init.factory.BibleVerseFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BibleVerseServiceTest extends IntegrationTest {

    @Autowired
    BibleVerseService bibleVerseService;
    @Autowired
    BibleVerseRepository bibleVerseRepository;
    @Autowired
    BibleTitleRepository bibleTitleRepository;

    @AfterEach
    void clearBibleVerseRepository() {
        bibleVerseRepository.deleteAllInBatch();
    }

    @DisplayName("id로 BibleVerse를 조회한다.")
    @Test
    void searchById() {
        //저장
        BibleTitle bibleTitle = bibleTitleRepository.findByShortenTitle("창").orElse(null);
        BibleVerse bibleVerse = BibleVerseFactory.of(bibleTitle, 10, 10, null, "그의 나라는 시날 땅의 바벨과 에렉과 악갓과 갈레에서 시작되었으며");
        Long savedId = bibleVerseRepository.save(bibleVerse).getId();

        //조회
        BibleVerseDetailResponse response = bibleVerseService.searchById(savedId);

        assertThat(response.getId()).isEqualTo(savedId);
        assertThat(response.getContent()).isEqualTo("그의 나라는 시날 땅의 바벨과 에렉과 악갓과 갈레에서 시작되었으며");
        assertThat(response.getTitleChapterVerse()).isEqualTo("창세기 10:10");
    }
}