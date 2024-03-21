package com.workintech.s17d1.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.workintech.s17d1.entity.Animal;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.web.servlet.MockMvc;
import com.workintech.s17d1.utils.ValidationUtils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.*;


@WebMvcTest(AnimalController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AnimalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ValidationUtils validationUtils; // Assuming ValidationUtils is a component


    @Test
    @Order(1)
    public void shouldReturnAllAnimals() throws Exception {
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
    public void shouldAddNewAnimal() throws Exception {
        Animal newAnimal = new Animal(2, "kedi");
        mockMvc.perform(post("/workintech/animal")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newAnimal)))
                .andExpect(status().isOk());

    }

    @Test
    @Order(4)
    public void shouldUpdateAnimal() throws Exception {
        Animal updatedAnimal = new Animal(1, "köpek");
        mockMvc.perform(put("/workintech/animal/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedAnimal)))
                .andExpect(status().isOk());


    }

    @Test
    @Order(5)
    public void shouldDeleteAnimal() throws Exception {
        mockMvc.perform(delete("/workintech/animal/1"))
                .andExpect(status().isOk());

        // Optionally, verify interactions or state if applicable
    }




}
