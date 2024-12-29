package com.bible_canvas.biblecanvas.bible.controller.response;

import com.bible_canvas.biblecanvas.bible.entity.BibleTitle;
import com.bible_canvas.biblecanvas.bible.entity.BibleVerse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BibleVerseDetailResponseTest {

    @DisplayName("엔티티를 입력하면 BibleVerseDetailResponse 반환한다.")
    @Test
    void test() {
        BibleTitle bibleTitle = BibleTitle.builder()
                .title("창세기")
                .shortenTitle("창")
                .build();
        BibleVerse bibleVerse = BibleVerse.builder()
                .bibleTitle(bibleTitle)
                .chapter(10)
                .verse(10)
                .content("그의 나라는 시날 땅의 바벨과 에렉과 악갓과 갈레에서 시작되었으며")
                .subtitle(null)
                .build();

        BibleVerseDetailResponse response = new BibleVerseDetailResponse(bibleVerse);

        assertThat(response.getId()).isEqualTo(null);
        assertThat(response.getTitleChapterVerse()).isEqualTo("창세기 10:10");
        assertThat(response.getContent()).isEqualTo("그의 나라는 시날 땅의 바벨과 에렉과 악갓과 갈레에서 시작되었으며");
    }
}