package me.kolganov.grannyshome.service;

import me.kolganov.grannyshome.domain.Animal;

import java.util.List;

public interface AnimalService {
    List<Animal> getAll();
    Animal getOne(long id);
    void save(Animal animal);
    void update(Animal animal);
    void delete(long id);
}
