package com.workintech.s17d1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import org.springframework.core.env.Environment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class ApplicationPropertiesKeyTests {

    @Autowired
    private Environment env;

    @Test
    @DisplayName("application properties istenilenler eklendi mi?")
    void serverPortIsSetTo8585() {

        String serverPort = env.getProperty("server.port");
        assertThat(serverPort).isEqualTo("8585");

        String courseName = env.getProperty("course.name");
        assertNotNull(courseName);

        String projectDeveloperFullname = env.getProperty("project.developer.fullname");
        assertNotNull(projectDeveloperFullname);
    }
}


