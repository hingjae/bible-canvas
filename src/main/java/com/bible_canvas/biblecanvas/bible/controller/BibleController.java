package com.bible_canvas.biblecanvas.bible.controller;

import com.bible_canvas.biblecanvas.bible.controller.request.BibleSearchRequest;
import com.bible_canvas.biblecanvas.bible.controller.response.BibleVerseDetailResponse;
import com.bible_canvas.biblecanvas.bible.controller.response.BibleVerseResponse;
import com.bible_canvas.biblecanvas.bible.service.BibleVerseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping("/bible-verse")
@RequiredArgsConstructor
@Controller
public class BibleController {

    private final BibleVerseService bibleVerseService;

    @GetMapping("/{bibleVerseId}")
    public String bibleVerse(@PathVariable("bibleVerseId") Long id, Model model) {
        BibleVerseDetailResponse response = bibleVerseService.searchById(id);
        model.addAttribute("bibleVerse", response);
        return "card-editor";
    }

    @GetMapping("/search")
    public String searchByContent(@ModelAttribute("bibleSearchRequest") @Valid BibleSearchRequest request, BindingResult bindingResult, Pageable pageable, Model model) {

        String keyword = request.getKeyword();

        if (bindingResult.hasErrors()) {
            log.error("keyword 는 2글자 이상이어야함. {}", request.getKeyword());
            model.addAttribute("keyword", keyword);
            model.addAttribute("results", Page.empty());
            return "search-results";
        }

        Page<BibleVerseResponse> results = bibleVerseService.searchByContent(keyword, pageable);
        model.addAttribute("keyword", keyword);
        model.addAttribute("results", results);
        return "search-results";
    }
}
