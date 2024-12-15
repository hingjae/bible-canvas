package com.bible_canvas.biblecanvas.init.bible;

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
public class BibleTextReader {
    public static String parseBible() {
        String bible = readBibleText();
        if (bible != null) {
            String[] lines = bible.split("\n");
            for (String line : lines) {
                parseLine(line);
            }
        }
        return null;
    }

    public static String readBibleText() {
        StringBuilder text = new StringBuilder();
        try {
            // 프로젝트 루트 경로로 파일 경로 설정
            Path filePath = Paths.get("bible/bible_utf8.txt");

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

    public static void parseLine(String line) {
        // 정규식을 이용해 줄을 파싱
        String regex = "^(\\S+?)(\\d+):(\\d+)\\s*(?:<(.*?)>)?\\s*(.*)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);

        if (matcher.matches()) {
            String shortenTitle = matcher.group(1); // shorten_title
            String chapter = matcher.group(2);     // 장
            String verse = matcher.group(3);       // 절
            String subtitle = matcher.group(4);    // 소제목 (null 가능)
            String content = matcher.group(5);     // 내용

            System.out.printf("책 제목: %s / 장: %s / 절: %s / 소제목: %s / 내용: %s%n",
                    shortenTitle, chapter, verse, subtitle != null ? subtitle : "없음", content);
        } else {
            System.out.println("Invalid line format: " + line);
        }
    }
}
