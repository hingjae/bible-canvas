package com.bible_canvas.biblecanvas.bible.repository;

import com.bible_canvas.biblecanvas.bible.entity.BibleVerse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface BibleVerseRepository extends JpaRepository<BibleVerse, Long> {
    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE bible_verse AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();

    @Query("SELECT bv FROM BibleVerse bv " +
            "JOIN FETCH bv.bibleTitle " +  // BibleTitle과의 페치 조인
            "WHERE bv.content LIKE %:keyword%")
    Page<BibleVerse> findByContentContaining(String keyword, Pageable pageable);

    @Query("select bv from BibleVerse bv " +
            "join fetch bv.bibleTitle " +
            "where bv.id = :id")
    Optional<BibleVerse> findByIdWithBibleTitle(@Param("id") Long id);
}
