package me.kolganov.grannyshome.service;

import me.kolganov.grannyshome.domain.Animal;
import me.kolganov.grannyshome.domain.AppUser;

import java.util.List;

public interface UserService {
    List<Animal> getAllCurrentUserAnimals(String login);
    AppUser getCurrentUser(String login);
    List<AppUser> getAll();
    AppUser getById(long id);
    boolean save(AppUser user);
    void update(AppUser user);
    void delete(long id);
}
