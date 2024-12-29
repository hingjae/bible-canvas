package com.bible_canvas.biblecanvas.init.factory;

import com.bible_canvas.biblecanvas.bible.entity.BibleTitle;
import com.bible_canvas.biblecanvas.bible.entity.BibleVerse;

import java.util.List;

public class BibleVerseFactory {
    public static BibleVerse of(String shortenTitle, int chapter, int verse, String subTitle, String content) {
        return BibleVerse.builder()
                .bibleTitle(BibleTitle.of(null, shortenTitle))
                .chapter(chapter)
                .verse(verse)
                .subtitle(subTitle)
                .content(content)
                .build();
    }

    public static BibleVerse of(BibleTitle bibleTitle, int chapter, int verse, String subTitle, String content) {
        return BibleVerse.builder()
                .bibleTitle(bibleTitle)
                .chapter(chapter)
                .verse(verse)
                .subtitle(subTitle)
                .content(content)
                .build();
    }

    public static List<BibleVerse> mockBibleVerses() {

        // TODO BibleTitle 조회해서 넣는게 필요함.
        return List.of(
                BibleVerseFactory.of("창", 1, 1, "천지 창조", "태초에 하나님이 천지를 창조하시니라"),
                BibleVerseFactory.of("고전", 1, 18, "하나님의 능력과 지혜이신 그리스도", "십자가의 도가 멸망하는 자들에게는 미련한 것이요 구원을 받는 우리에게는 하나님의 능력이라"),
                BibleVerseFactory.of("엡", 2, 8, null, "너희는 그 은혜에 의하여 믿음으로 말미암아 구원을 받았으니 이것은 너희에게서 난 것이 아니요 하나님의 선물이라"),
                BibleVerseFactory.of("요", 1, 12, null, "영접하는 자 곧 그 이름을 믿는 자들에게는 하나님의 자녀가 되는 권세를 주셨으니"),
                BibleVerseFactory.of("행", 1, 8, null, "오직 성령이 너희에게 임하시면 너희가 권능을 받고 예루살렘과 온 유대와 사마리아와 땅 끝까지 이르러 내 증인이 되리라 하시니라"),
                BibleVerseFactory.of("마", 28, 19, null, "그러므로 너희는 가서 모든 민족을 제자로 삼아 아버지와 아들과 성령의 이름으로 세례를 베풀고"),
                BibleVerseFactory.of("마", 28, 20, null, "내가 너희에게 분부한 모든 것을 가르쳐 지키게 하라 볼지어다 내가 세상 끝날까지 너희와 항상 함께 있으리라 하시니라"),
                BibleVerseFactory.of("롬", 12, 1, "하나님의 뜻을 분별하는 새 생활", "그러므로 형제들아 내가 하나님의 모든 자비하심으로 너희를 권하노니 너희 몸을 하나님이 기뻐하시는 거룩한 산 제물로 드리라 이는 너희가 드릴 영적 예배니라"),
                BibleVerseFactory.of("히", 4, 8, null, "만일 여호수아가 그들에게 안식을 주었더라면 그 후에 다른 날을 말씀하지 아니하셨으리라"),
                BibleVerseFactory.of("민", 32, 12, null, "그러나 그나스 사람 여분네의 아들 갈렙과 눈의 아들 여호수아는 여호와를 온전히 따랐느니라 하시고"),
                BibleVerseFactory.of("민", 32, 28, null, "이에 모세가 그들에 대하여 제사장 엘르아살과 눈의 아들 여호수아와 이스라엘 자손 지파의 수령들에게 명령하니라"),
                BibleVerseFactory.of("마", 1, 1, "예수 그리스도의 계보(눅 3:23-38)", "아브라함과 다윗의 자손 예수 그리스도의 계보라"),
                BibleVerseFactory.of("왕상", 18, 36, null, "저녁 소제 드릴 때에 이르러 선지자 엘리야가 나아가서 말하되 아브라함과 이삭과 이스라엘의 하나님 여호와여 주께서 이스라엘 중에서 하나님이신 것과 내가 주의 종인 것과 내가 주의 말씀대로 이 모든 일을 행하는 것을 오늘 알게 하옵소서"),
                BibleVerseFactory.of("왕하", 13, 23, null, "여호와께서 아브라함과 이삭과 야곱과 더불어 세우신 언약 때문에 이스라엘에게 은혜를 베풀며 그들을 불쌍히 여기시며 돌보사 멸하기를 즐겨하지 아니하시고 이 때까지 자기 앞에서 쫓아내지 아니하셨더라")
        );
    }
}
