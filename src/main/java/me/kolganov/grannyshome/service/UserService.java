package me.kolganov.grannyshome.service;

import me.kolganov.grannyshome.domain.AppUser;

import java.util.List;

public interface UserService {
    List<AppUser> getAll();
    AppUser getById(long id);
    void save(AppUser user);
    void update(AppUser user);
    void delete(long id);
}
