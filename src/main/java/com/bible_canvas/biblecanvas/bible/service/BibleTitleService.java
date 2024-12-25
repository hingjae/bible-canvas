package com.bible_canvas.biblecanvas.bible.service;

import com.bible_canvas.biblecanvas.bible.entity.BibleTitle;
import com.bible_canvas.biblecanvas.bible.repository.BibleTitleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BibleTitleService {
    private final BibleTitleRepository bibleTitleRepository;

    @Cacheable(cacheNames = "bibleTitleCache", key = "#shortenTitle")
    public BibleTitle findByShortenTitle(String shortenTitle) {
        return bibleTitleRepository.findByShortenTitle(shortenTitle)
                .orElseThrow(EntityNotFoundException::new);
    }

    public BibleTitle findById(Long id) {
        return bibleTitleRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}
