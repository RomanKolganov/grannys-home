package me.kolganov.grannyshome.service;

import me.kolganov.grannyshome.domain.Animal;
import me.kolganov.grannyshome.domain.AppUser;
import me.kolganov.grannyshome.domain.Dialog;

import java.util.List;

public interface UserService {
    List<Dialog> getAllCurrentUserDialogs(String login);
    List<Animal> getAllCurrentUserAnimals(String login);
    AppUser getCurrentUser(String login);
    AppUser getById(long id);
    boolean save(AppUser user);
    void update(AppUser user);
}
