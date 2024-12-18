package com.bible_canvas.biblecanvas.init.bible;

import com.bible_canvas.biblecanvas.bible.BibleVerse;
import com.bible_canvas.biblecanvas.init.exception.InvalidParseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * bible_utf8.txt 를 읽음
 */
@Component
@Slf4j
public class BibleTextReader {

    private static final String REGEX = "^(\\S+?)(\\d+):(\\d+)\\s*(.*)$";

    public String readBibleText(String biblePath) {
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
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(line);

        if (matcher.matches()) {
            String shortenTitle = matcher.group(1); // 책 이름
            int chapter = Integer.parseInt(matcher.group(2)); // 장
            int verse = Integer.parseInt(matcher.group(3)); // 절
            String rawContent = matcher.group(4).trim(); // 본문 (소제목 포함 가능)

            // 본문에서 소제목 추출
            String subtitle = null;
            String content = rawContent;

            // 소제목이 있으면 추출
            int subtitleStart = rawContent.indexOf('<');
            int subtitleEnd = rawContent.indexOf('>');
            if (subtitleStart != -1 && subtitleEnd != -1 && subtitleStart < subtitleEnd) {
                subtitle = rawContent.substring(subtitleStart + 1, subtitleEnd).trim();
                content = rawContent.substring(0, subtitleStart).trim()
                        + " "
                        + rawContent.substring(subtitleEnd + 1).trim();
            }

            log.info("책 제목: {} / 장: {} / 절: {} / 소제목: {} / 본문: {}",
                    shortenTitle, chapter, verse, subtitle , content.trim());

            return BibleVerse.builder()
                    .shortenTitle(shortenTitle)
                    .chapter(chapter)
                    .verse(verse)
                    .subtitle(subtitle)
                    .content(content.trim())
                    .build();
        }

        log.warn("Invalid line format: {}", line);
        throw new InvalidParseException();
    }
}
