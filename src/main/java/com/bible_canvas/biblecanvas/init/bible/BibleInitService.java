package com.bible_canvas.biblecanvas.init.bible;

import com.bible_canvas.biblecanvas.bible.BibleVerse;
import com.bible_canvas.biblecanvas.bible.repository.BibleVerseRepository;
import com.bible_canvas.biblecanvas.bible.service.BibleVerseService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BibleInitService implements CommandLineRunner {

    private final BibleVerseRepository bibleVerseRepository;

    private final BibleTextReader bibleTextReader;
    private final BibleVerseService bibleVerseService;

    @Value("${bible.init-data}")
    private boolean initData;

    @PersistenceContext
    private EntityManager entityManager;

    private static final String BIBLE_PATH = "bible/bible_utf8.txt";
    private static final int BATCH_SIZE = 5000;

    @Override
    public void run(String... args) {

        if (initData) {
            bibleVerseRepository.deleteAllInBatch();
            bibleVerseRepository.resetAutoIncrement();

            log.info("Init Bible Data..........");
            String bibleText = bibleTextReader.readBibleText(BIBLE_PATH);

            bibleVerseService.bibleInitSave(bibleText);

            log.info("Bible verses saved successfully.");
        } else {
            log.info("initData false");
        }
    }
}
