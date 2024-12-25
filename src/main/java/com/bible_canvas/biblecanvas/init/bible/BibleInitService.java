package com.bible_canvas.biblecanvas.init.bible;

import com.bible_canvas.biblecanvas.bible.repository.BibleVerseRepository;
import com.bible_canvas.biblecanvas.bible.service.BibleVerseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class BibleInitService implements CommandLineRunner {

    private final BibleVerseRepository bibleVerseRepository;

    private final BibleReader bibleReader;
    private final BibleVerseService bibleVerseService;

    @Value("${bible.init-data}")
    private boolean initData;

    private static final String BIBLE_PATH = "bible/bible_utf8.txt";

    @Override
    public void run(String... args) {

        if (initData) {
            bibleVerseRepository.deleteAllInBatch();
            bibleVerseRepository.resetAutoIncrement();

            log.info("Init Bible Data..........");
            String bibleText = bibleReader.readBibleTextFile(BIBLE_PATH);

            long startTime = System.nanoTime(); // 시작 시간 기록

            bibleVerseService.bibleInitSave(bibleText);

            long endTime = System.nanoTime(); // 종료 시간 기록
            long durationInMillis = (endTime - startTime) / 1_000_000; // 나노초를 밀리초로 변환

            log.info("Bible verses saved successfully in {} ms.", durationInMillis);
        } else {
            log.info("initData false");
        }
    }
}
