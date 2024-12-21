package com.bible_canvas.biblecanvas.bible.service;

import com.bible_canvas.biblecanvas.bible.BibleVerse;
import com.bible_canvas.biblecanvas.bible.controller.response.BibleVerseResponse;
import com.bible_canvas.biblecanvas.bible.repository.BibleVerseRepository;
import com.bible_canvas.biblecanvas.init.bible.BibleReader;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BibleVerseService {
    private final BibleVerseRepository bibleVerseRepository;
    private final BibleReader bibleReader;

    private static final int BATCH_SIZE = 5000;

    @Transactional
    public void bibleInitSave(String bibleText) {
        String[] lines = bibleText.split("\n");
        List<BibleVerse> bibleVerses = new ArrayList<>();

        for (String line : lines) {
            BibleVerse bibleVerse = bibleReader.parseLine(line);
            bibleVerses.add(bibleVerse);

            if (bibleVerses.size() == BATCH_SIZE) {
                bibleVerseRepository.saveAll(bibleVerses);
                bibleVerses.clear();
            }
        }

        if (!bibleVerses.isEmpty()) {
            bibleVerseRepository.saveAll(bibleVerses);
        }
    }

    public Page<BibleVerseResponse> searchByContent(String keyword, Pageable pageable) {
        return bibleVerseRepository.findByContentContaining(keyword, pageable)
                .map(BibleVerseResponse::new);
    }
}
