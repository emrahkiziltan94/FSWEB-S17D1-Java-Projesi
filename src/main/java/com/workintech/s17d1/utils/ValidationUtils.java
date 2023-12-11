package com.workintech.s17d1.utils;

import com.workintech.s17d1.entity.Animal;

public class ValidationUtils {

    public static void checkId(Integer id) {
        if (id == null)
            System.out.println("id null olamaz");
    }
    public static void checkAnimal(Animal animal){
        if (animal.getId() == null)
            System.out.println("id null olamaz");
        if(animal.getName().isEmpty())
            System.out.println("name null yada empty olamaz!");
    }
}
