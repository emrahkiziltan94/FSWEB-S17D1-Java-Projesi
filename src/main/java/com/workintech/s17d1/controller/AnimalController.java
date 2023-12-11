package com.workintech.s17d1.controller;

import com.workintech.s17d1.entity.Animal;
import com.workintech.s17d1.utils.ValidationUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/workintech/animal")
public class AnimalController {

    private Map<Integer, Animal> animals;


    @PostConstruct
    public void loadAll(){
        this.animals = new HashMap<>();
        this.animals.put(1,new Animal(1,"maymun"));
    }

    @GetMapping
    public List<Animal> getAnimals(){
        System.out.println("---animals list get triggered!---");
        return new ArrayList<>(this.animals.values());
    }

    @GetMapping(path = "/{id}")
    public Animal getAnimal(@PathVariable("id") Integer id){
        ValidationUtils.checkId(id);
        System.out.println("---animals  get by id triggered!---");
        return this.animals.get(id);
    }

    @PostMapping
    public void addAnimal(@RequestBody Animal animal){
        ValidationUtils.checkAnimal(animal);
        this.animals.put(animal.getId(),animal);
    }

    @PutMapping(path = "/{id}")
    public Animal updateAnimal(@PathVariable("id") Integer existingRecordId, @RequestBody Animal newAnimal){
        ValidationUtils.checkId(existingRecordId);
        ValidationUtils.checkAnimal(newAnimal);
        this.animals.replace(existingRecordId,newAnimal);
        return this.animals.get(existingRecordId);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteAnimal(@PathVariable("id") Integer id){
        ValidationUtils.checkId(id);
        this.animals.remove(id);
    }


}
