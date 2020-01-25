package me.kolganov.grannyshome.service;

import me.kolganov.grannyshome.domain.Animal;
import me.kolganov.grannyshome.domain.AppUser;
import me.kolganov.grannyshome.rest.dto.AnimalDto;

import java.security.Principal;
import java.util.List;

public interface UserService {
    List<Animal> getAllCurrentUserAnimals(String login);
    AppUser getCurrentUser(String login);
    List<AppUser> getAll();
    AppUser getById(long id);
    void save(AppUser user);
    void update(AppUser user);
    void delete(long id);
}
