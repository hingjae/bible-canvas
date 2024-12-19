package com.bible_canvas.biblecanvas.init.util;

import com.bible_canvas.biblecanvas.bible.BibleVerse;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class BibleVerseParserTest {

    @ParameterizedTest
    @MethodSource("bibleLinesAndResult")
    void bibleParseText(String line, String title, int chapter, int verse, String subtitle, String content) {
        BibleVerse bibleVerse = BibleVerseParser.parse(line);

        assertThat(bibleVerse.getShortenTitle()).isEqualTo(title);
        assertThat(bibleVerse.getChapter()).isEqualTo(chapter);
        assertThat(bibleVerse.getVerse()).isEqualTo(verse);
        assertThat(bibleVerse.getSubtitle()).isEqualTo(subtitle);
        assertThat(bibleVerse.getContent()).isEqualTo(content);
    }

    private static Stream<Arguments> bibleLinesAndResult() {
        return Stream.of(
                arguments("창1:1 <천지 창조> 태초에 하나님이 천지를 창조하시니라", "창", 1, 1, "천지 창조", "태초에 하나님이 천지를 창조하시니라"),
                arguments("출30:10 아론이 일 년에 한 번씩 이 향단 뿔을 위하여 속죄하되 속죄제의 피로 일 년에 한 번씩 대대로 속죄할지니라 이 제단은 여호와께 지극히 거룩하니라", "출", 30, 10, null, "아론이 일 년에 한 번씩 이 향단 뿔을 위하여 속죄하되 속죄제의 피로 일 년에 한 번씩 대대로 속죄할지니라 이 제단은 여호와께 지극히 거룩하니라"),
                arguments("신28:45 네가 네 하나님 여호와의 말씀을 청종하지 아니하고 네게 명령하신 그의 명령과 규례를 지키지 아니하므로 이 모든 저주가 네게 와서 너를 따르고 네게 이르러 마침내 너를 멸하리니", "신", 28, 45, null, "네가 네 하나님 여호와의 말씀을 청종하지 아니하고 네게 명령하신 그의 명령과 규례를 지키지 아니하므로 이 모든 저주가 네게 와서 너를 따르고 네게 이르러 마침내 너를 멸하리니"),
                arguments("왕상13:2 하나님의 사람이 제단을 향하여 여호와의 말씀으로 외쳐 이르되 제단아 제단아 여호와께서 이와 같이 말씀하시기를 다윗의 집에 요시야라 이름하는 아들을 낳으리니 그가 네 위에 분향하는 산당 제사장을 네 위에서 제물로 바칠 것이요 또 사람의 뼈를 네 위에서 사르리라 하셨느니라 하고", "왕상", 13, 2, null, "하나님의 사람이 제단을 향하여 여호와의 말씀으로 외쳐 이르되 제단아 제단아 여호와께서 이와 같이 말씀하시기를 다윗의 집에 요시야라 이름하는 아들을 낳으리니 그가 네 위에 분향하는 산당 제사장을 네 위에서 제물로 바칠 것이요 또 사람의 뼈를 네 위에서 사르리라 하셨느니라 하고"),
                arguments("욥16:1 <욥의 대답> 욥이 대답하여 이르되", "욥", 16, 1, "욥의 대답", "욥이 대답하여 이르되"),
                arguments("마28:19 그러므로 너희는 가서 모든 민족을 제자로 삼아 아버지와 아들과 성령의 이름으로 세례를 베풀고", "마", 28, 19, null, "그러므로 너희는 가서 모든 민족을 제자로 삼아 아버지와 아들과 성령의 이름으로 세례를 베풀고"),
                arguments("롬12:1 <하나님의 뜻을 분별하는 새 생활> 그러므로 형제들아 내가 하나님의 모든 자비하심으로 너희를 권하노니 너희 몸을 하나님이 기뻐하시는 거룩한 산 제물로 드리라 이는 너희가 드릴 영적 예배니라", "롬", 12, 1, "하나님의 뜻을 분별하는 새 생활", "그러므로 형제들아 내가 하나님의 모든 자비하심으로 너희를 권하노니 너희 몸을 하나님이 기뻐하시는 거룩한 산 제물로 드리라 이는 너희가 드릴 영적 예배니라"),
                arguments("행1:8 오직 성령이 너희에게 임하시면 너희가 권능을 받고 예루살렘과 온 유대와 사마리아와 땅 끝까지 이르러 내 증인이 되리라 하시니라", "행", 1, 8, null, "오직 성령이 너희에게 임하시면 너희가 권능을 받고 예루살렘과 온 유대와 사마리아와 땅 끝까지 이르러 내 증인이 되리라 하시니라"),
                arguments("행8:1 사울은 그가 죽임 당함을 마땅히 여기더라 그 <사울이 교회를 박해하다> 날에 예루살렘에 있는 교회에 큰 박해가 있어 사도 외에는 다 유대와 사마리아 모든 땅으로 흩어지니라", "행", 8, 1, "사울이 교회를 박해하다", "사울은 그가 죽임 당함을 마땅히 여기더라 그 날에 예루살렘에 있는 교회에 큰 박해가 있어 사도 외에는 다 유대와 사마리아 모든 땅으로 흩어지니라")
        );
    }

}