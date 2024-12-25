package com.bible_canvas.biblecanvas.init;

import com.bible_canvas.biblecanvas.bible.entity.BibleTitle;
import com.bible_canvas.biblecanvas.bible.repository.BibleTitleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@TestConfiguration
public class TestInitData {

    @Autowired
    BibleTitleRepository bibleTitleRepository;

    public final static Map<String, BibleTitle> bibleShortenTitleMap = new HashMap<>();

    @PostConstruct
    public void initBibleTitle() {
        bibleTitleRepository.deleteAllInBatch();
        bibleTitleRepository.resetAutoIncrement();

        bibleTitleRepository.saveAll(getBibleTitles());
        bibleTitleRepository.findAll()
                .forEach(bibleTitle -> bibleShortenTitleMap.put(bibleTitle.getShortenTitle(), bibleTitle));
    }

    private List<BibleTitle> getBibleTitles() {
        return List.of(
                BibleTitle.of("창세기", "창"),
                BibleTitle.of("출애굽기", "출"),
                BibleTitle.of("레위기", "레"),
                BibleTitle.of("민수기", "민"),
                BibleTitle.of("신명기", "신"),
                BibleTitle.of("여호수아", "수"),
                BibleTitle.of("사사기", "삿"),
                BibleTitle.of("룻기", "룻"),
                BibleTitle.of("사무엘상", "삼상"),
                BibleTitle.of("사무엘하", "삼하"),
                BibleTitle.of("열왕기상", "왕상"),
                BibleTitle.of("열왕기하", "왕하"),
                BibleTitle.of("역대상", "대상"),
                BibleTitle.of("역대하", "대하"),
                BibleTitle.of("에스라", "스"),
                BibleTitle.of("느헤미야", "느"),
                BibleTitle.of("에스더", "에"),
                BibleTitle.of("욥기", "욥"),
                BibleTitle.of("시편", "시"),
                BibleTitle.of("잠언", "잠"),
                BibleTitle.of("전도서", "전"),
                BibleTitle.of("아가", "아"),
                BibleTitle.of("이사야", "사"),
                BibleTitle.of("예레미야", "렘"),
                BibleTitle.of("예레미야애가", "애"),
                BibleTitle.of("에스겔", "겔"),
                BibleTitle.of("다니엘", "단"),
                BibleTitle.of("호세아", "호"),
                BibleTitle.of("요엘", "욜"),
                BibleTitle.of("아모스", "암"),
                BibleTitle.of("오바댜", "옵"),
                BibleTitle.of("요나", "욘"),
                BibleTitle.of("미가", "미"),
                BibleTitle.of("나훔", "나"),
                BibleTitle.of("하박국", "합"),
                BibleTitle.of("스바냐", "습"),
                BibleTitle.of("학개", "학"),
                BibleTitle.of("스가랴", "슥"),
                BibleTitle.of("말라기", "말"),
                BibleTitle.of("마태복음", "마"),
                BibleTitle.of("마가복음", "막"),
                BibleTitle.of("누가복음", "눅"),
                BibleTitle.of("요한복음", "요"),
                BibleTitle.of("사도행전", "행"),
                BibleTitle.of("로마서", "롬"),
                BibleTitle.of("고린도전서", "고전"),
                BibleTitle.of("고린도후서", "고후"),
                BibleTitle.of("갈라디아서", "갈"),
                BibleTitle.of("에베소서", "엡"),
                BibleTitle.of("빌립보서", "빌"),
                BibleTitle.of("골로새서", "골"),
                BibleTitle.of("데살로니가전서", "살전"),
                BibleTitle.of("데살로니가후서", "살후"),
                BibleTitle.of("디모데전서", "딤전"),
                BibleTitle.of("디모데후서", "딤후"),
                BibleTitle.of("디도서", "딛"),
                BibleTitle.of("빌레몬서", "몬"),
                BibleTitle.of("히브리서", "히"),
                BibleTitle.of("야고보서", "약"),
                BibleTitle.of("베드로전서", "벧전"),
                BibleTitle.of("베드로후서", "벧후"),
                BibleTitle.of("요한일서", "요일"),
                BibleTitle.of("요한이서", "요이"),
                BibleTitle.of("요한삼서", "요삼"),
                BibleTitle.of("유다서", "유"),
                BibleTitle.of("요한계시록", "계")
        );
    }
}
