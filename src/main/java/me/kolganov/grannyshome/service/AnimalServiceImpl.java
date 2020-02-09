package me.kolganov.grannyshome.service;

import lombok.RequiredArgsConstructor;
import me.kolganov.grannyshome.dao.AnimalRepository;
import me.kolganov.grannyshome.dao.AppUserRepository;
import me.kolganov.grannyshome.domain.Animal;
import me.kolganov.grannyshome.domain.AppUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService {
    private final AnimalRepository animalRepository;
    private final AppUserRepository userDao;

    @Override
    public List<Animal> getAll() {
        return animalRepository.findAll();
    }

    @Override
    public Animal getOne(long id) {
        return animalRepository.findById(id).orElse(new Animal());
    }

    @Override
    public void save(Animal animal) {
        Optional<AppUser> user = userDao.findByLogin(animal.getUser().getLogin());
        user.ifPresent(u -> {
            animal.setUser(u);
            animalRepository.save(animal);
        });
    }

    @Override
    public void update(Animal animal) {
        Optional<Animal> oldAnimal = animalRepository.findById(animal.getId());
        oldAnimal.ifPresent(a -> {
            a.setName(animal.getName());
            a.setType(animal.getType());
            animalRepository.save(a);
        });
    }

    @Override
    public void delete(long id) {
        animalRepository.deleteById(id);
    }
}
