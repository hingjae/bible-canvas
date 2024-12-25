CREATE TABLE bible_title (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    shorten_title VARCHAR(50) NOT NULL
);

CREATE TABLE bible_verse (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    bible_title_id BIGINT NOT NULL,
    chapter INT NOT NULL,
    verse INT NOT NULL,
    subtitle VARCHAR(255),
    content VARCHAR(1000) NOT NULL,
    INDEX idx_bible_title_id (bible_title_id),
    INDEX idx_chapter_verse (chapter, verse),
    INDEX idx_content (content(255)),
    CONSTRAINT fk_bible_verse_title
        FOREIGN KEY (bible_title_id)
        REFERENCES bible_title (id)
        ON DELETE CASCADE
);
