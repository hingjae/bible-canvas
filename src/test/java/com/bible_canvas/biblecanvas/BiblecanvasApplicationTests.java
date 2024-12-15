package com.bible_canvas.biblecanvas;

import com.bible_canvas.biblecanvas.init.bible.BibleTextReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BiblecanvasApplicationTests {

	@DisplayName("bible.txt를 읽어서 String 으로 반환한다.")
	@Test
	void contextLoads() {
		String bible = BibleTextReader.readBibleText();
		System.out.println("bible = " + bible);
	}

	@DisplayName("bible.txt를 읽어서 책이름, 장, 절, 소제목, 내용으로 파싱한다.")
	@Test
	void parseBible() {
		String bible = BibleTextReader.parseBible();
	}
}
