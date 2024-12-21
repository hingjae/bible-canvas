package com.bible_canvas.biblecanvas.bible.controller;

import com.bible_canvas.biblecanvas.bible.controller.response.BibleVerseResponse;
import com.bible_canvas.biblecanvas.bible.service.BibleVerseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/bible-verse")
@RequiredArgsConstructor
@Controller
public class BibleController {

    private final BibleVerseService bibleVerseService;

    @GetMapping("/search")
    public String searchByContent(@RequestParam String keyword, Pageable pageable, Model model) {
        Page<BibleVerseResponse> results = bibleVerseService.searchByContent(keyword, pageable);
        model.addAttribute("keyword", keyword);
        model.addAttribute("results", results);
        return "search-results";
    }
}
