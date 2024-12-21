package com.bible_canvas.biblecanvas.bible.repository;

import com.bible_canvas.biblecanvas.IntegrationTest;
import com.bible_canvas.biblecanvas.bible.BibleVerse;
import com.bible_canvas.biblecanvas.init.factory.BibleVerseFactory;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BibleVerseRepositoryTest extends IntegrationTest {

    @Autowired
    BibleVerseRepository bibleVerseRepository;

    @BeforeEach
    void 성경초기데이터() {
        List<BibleVerse> bibleVerses = BibleVerseFactory.mockBibleVerses();
        bibleVerseRepository.saveAll(bibleVerses);
    }

    @AfterEach
    void clear() {
        bibleVerseRepository.deleteAllInBatch();
    }

    @Test
    void 성경구절로검색() {
        String keyword = "하나님";
        PageRequest pageRequest = PageRequest.of(0, 5, Sort.by("id").ascending());

        Page<BibleVerse> result = bibleVerseRepository.findByContentContaining(keyword, pageRequest);

        assertThat(result.getContent()).hasSize(5);
        assertThat(result.getTotalElements()).isEqualTo(6);
        assertThat(result.getTotalPages()).isEqualTo(2);
        assertThat(result.getContent()).extracting(BibleVerse::getShortenTitle, BibleVerse::getChapter, BibleVerse::getVerse).containsExactly(
                Tuple.tuple("창", 1, 1),
                Tuple.tuple("고전", 1, 18),
                Tuple.tuple("엡", 2, 8),
                Tuple.tuple("요", 1, 12),
                Tuple.tuple("롬", 12, 1)
        );
    }

    @Test
    void 성경구절로검색2() {
        String keyword = "하나님";
        PageRequest pageRequest = PageRequest.of(1, 5, Sort.by("id").ascending());

        Page<BibleVerse> result = bibleVerseRepository.findByContentContaining(keyword, pageRequest);

        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getTotalElements()).isEqualTo(6);
        assertThat(result.getTotalPages()).isEqualTo(2);
        assertThat(result.getContent()).extracting(BibleVerse::getShortenTitle, BibleVerse::getChapter, BibleVerse::getVerse).containsExactly(
                Tuple.tuple("왕상", 18, 36)
        );
    }

    @Test
    void 성경구절로검색3() {
        String keyword = "여호수아";
        PageRequest pageRequest = PageRequest.of(0, 5, Sort.by("id").ascending());

        Page<BibleVerse> result = bibleVerseRepository.findByContentContaining(keyword, pageRequest);

        assertThat(result.getContent()).hasSize(3);
        assertThat(result.getTotalElements()).isEqualTo(3);
        assertThat(result.getTotalPages()).isEqualTo(1);
        assertThat(result.getContent()).extracting(BibleVerse::getShortenTitle, BibleVerse::getChapter, BibleVerse::getVerse).containsExactly(
                Tuple.tuple("히", 4, 8),
                Tuple.tuple("민", 32, 12),
                Tuple.tuple("민", 32, 28)
        );
    }

    @Test
    void 성경구절로검색4() {
        String keyword = "너희";
        PageRequest pageRequest = PageRequest.of(0, 5, Sort.by("id").ascending());

        Page<BibleVerse> result = bibleVerseRepository.findByContentContaining(keyword, pageRequest);

        assertThat(result.getContent()).hasSize(5);
        assertThat(result.getTotalElements()).isEqualTo(5);
        assertThat(result.getTotalPages()).isEqualTo(1);
        assertThat(result.getContent()).extracting(BibleVerse::getShortenTitle, BibleVerse::getChapter, BibleVerse::getVerse).containsExactly(
                Tuple.tuple("엡", 2, 8),
                Tuple.tuple("행", 1, 8),
                Tuple.tuple("마", 28, 19),
                Tuple.tuple("마", 28, 20),
                Tuple.tuple("롬", 12, 1)
        );
    }
}