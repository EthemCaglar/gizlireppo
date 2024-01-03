package dev.patika.Vet.App.business.abs;

import dev.patika.Vet.App.entity.Animal;

import java.util.List;

public interface IAnimalService {
    Animal getByID(int id);
    Animal save(Animal animal);
    String delete(int id);
    Animal update(Animal animal);
    List<Animal> findAll();
}