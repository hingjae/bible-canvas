package com.bible_canvas.biblecanvas;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MySQLContainer;

@SpringBootTest
@ActiveProfiles("test")
public abstract class IntegrationTest {

    static final String MYSQL_IMAGE = "mysql:8";
    static final MySQLContainer MYSQL_IMAGE_CONTAINER;

    static {
        MYSQL_IMAGE_CONTAINER = new MySQLContainer(MYSQL_IMAGE);
        MYSQL_IMAGE_CONTAINER.start();
    }
}
