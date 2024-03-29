package com.workintech.s17d1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workintech.s17d1.controller.AnimalController;
import com.workintech.s17d1.entity.Animal;
import com.workintech.s17d1.utils.ValidationUtils;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AnimalController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
 class MainTest {
    @Autowired
    private Environment env;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ValidationUtils validationUtils; 

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

    @Test
    @Order(1)
     void shouldReturnAllAnimals() throws Exception {
        mockMvc.perform(get("/workintech/animal"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").isNotEmpty());
    }

    @Test
    @Order(2)
    void shouldReturnAnimalById() throws Exception {

        mockMvc.perform(get("/workintech/animal/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("maymun"));
    }


    @Test
    @Order(3)
     void shouldAddNewAnimal() throws Exception {
        Animal newAnimal = new Animal(2, "kedi");
        mockMvc.perform(post("/workintech/animal")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newAnimal)))
                .andExpect(status().isOk());

    }

    @Test
    @Order(4)
     void shouldUpdateAnimal() throws Exception {
        Animal updatedAnimal = new Animal(1, "köpek");
        mockMvc.perform(put("/workintech/animal/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedAnimal)))
                .andExpect(status().isOk());


    }

    @Test
    @Order(5)
     void shouldDeleteAnimal() throws Exception {
        mockMvc.perform(delete("/workintech/animal/1"))
                .andExpect(status().isOk());

        
    }

    @Test
    void testAnimalCreation() {
        // Arrange
        Integer expectedId = 1;
        String expectedName = "Lion";

        // Act
        Animal animal = new Animal(expectedId, expectedName);

        // Assert
        assertEquals(expectedId, animal.getId(), "The ID should match the expected value.");
        assertEquals(expectedName, animal.getName(), "The name should match the expected value.");
    }

}
