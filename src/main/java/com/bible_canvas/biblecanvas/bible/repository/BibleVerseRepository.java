package com.bible_canvas.biblecanvas.bible.repository;

import com.bible_canvas.biblecanvas.bible.entity.BibleVerse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface BibleVerseRepository extends JpaRepository<BibleVerse, Long> {
    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE bible_verse AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();

    Page<BibleVerse> findByContentContaining(String keyword, Pageable pageable);
}
