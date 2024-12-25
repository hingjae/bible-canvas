package com.bible_canvas.biblecanvas;

import com.bible_canvas.biblecanvas.init.TestInitData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MySQLContainer;

@Import(TestInitData.class)
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public abstract class IntegrationTest {

    static final String MYSQL_IMAGE = "mysql:8";
    static final MySQLContainer MYSQL_IMAGE_CONTAINER;

    @Autowired
    protected MockMvc mockMvc;

    static {
        MYSQL_IMAGE_CONTAINER = new MySQLContainer(MYSQL_IMAGE);
        MYSQL_IMAGE_CONTAINER.start();
    }
}
