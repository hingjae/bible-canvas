package com.bible_canvas.biblecanvas.init.util;

import com.bible_canvas.biblecanvas.bible.BibleVerse;
import com.bible_canvas.biblecanvas.init.exception.InvalidParseException;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class BibleVerseParser {
    private static final String REGEX = "^(\\S+?)(\\d+):(\\d+)\\s*(.*)$";

    public static BibleVerse parse(String line) {
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
