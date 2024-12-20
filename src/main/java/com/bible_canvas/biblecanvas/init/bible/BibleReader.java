package com.bible_canvas.biblecanvas.init.bible;

import com.bible_canvas.biblecanvas.bible.BibleVerse;
import com.bible_canvas.biblecanvas.init.util.BibleVerseParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * bible_utf8.txt 를 읽음
 */
@Component
@Slf4j
public class BibleReader {

    public String readBibleTextFile(String biblePath) {
        StringBuilder text = new StringBuilder();
        try {
            // 프로젝트 루트 경로로 파일 경로 설정
            Path filePath = Paths.get(biblePath);

            // UTF-8로 파일 읽기
            try (BufferedReader reader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    text.append(line).append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // 에러 처리
        }
        return text.toString();
    }

    public BibleVerse parseLine(String line) {
        BibleVerse bibleVerse = BibleVerseParser.parse(line);

        log.info("책 제목: {} / 장: {} / 절: {} / 소제목: {} / 본문: {}",
                bibleVerse.getShortenTitle(), bibleVerse.getChapter(), bibleVerse.getVerse(), bibleVerse.getShortenTitle(), bibleVerse.getContent());

        return bibleVerse;
    }
}
