package com.bible_canvas.biblecanvas.bible.repository;

import com.bible_canvas.biblecanvas.bible.entity.BibleTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface BibleTitleRepository extends JpaRepository<BibleTitle, Long> {
    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE bible_title AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();

    Optional<BibleTitle> findByShortenTitle(String shortenTitle);
}
