package me.kolganov.grannyshome.service;

import lombok.RequiredArgsConstructor;
import me.kolganov.grannyshome.dao.AnimalDao;
import me.kolganov.grannyshome.dao.AppUserDao;
import me.kolganov.grannyshome.domain.Animal;
import me.kolganov.grannyshome.domain.AppUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService {
    private final AnimalDao animalDao;
    private final AppUserDao userDao;

    @Override
    public List<Animal> getAll() {
        return animalDao.findAll();
    }

    @Override
    public Animal getById(long id) {
        return animalDao.findById(id).orElseGet(Animal::new);
    }

    @Override
    public void save(Animal animal) {
        Optional<AppUser> user = userDao.findByLogin(animal.getUser().getLogin());
        user.ifPresent(u -> {
            animal.setUser(u);
            animalDao.save(animal);
        });
    }

    @Override
    public void update(Animal animal) {
        Optional<Animal> oldAnimal = animalDao.findById(animal.getId());
        oldAnimal.ifPresent(a -> {
            a.setName(animal.getName());
            animalDao.save(a);
        });
    }

    @Override
    public void delete(long id) {
        animalDao.deleteById(id);
    }
}
