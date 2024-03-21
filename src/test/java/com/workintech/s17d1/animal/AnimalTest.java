package com.workintech.s17d1.animal;


import com.workintech.s17d1.entity.Animal;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnimalTest {

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
