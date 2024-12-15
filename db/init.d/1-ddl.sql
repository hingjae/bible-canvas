CREATE TABLE bible_verse (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    shorten_title VARCHAR(50) NOT NULL,
    chapter INT NOT NULL,
    verse INT NOT NULL,
    subtitle VARCHAR(255),
    content VARCHAR(1000) NOT NULL,
    INDEX idx_shorten_title (shorten_title),
    INDEX idx_chapter_verse (chapter, verse),
    INDEX idx_content (content(255))
);
