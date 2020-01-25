package me.kolganov.grannyshome.service;

import lombok.RequiredArgsConstructor;
import me.kolganov.grannyshome.dao.AnimalDao;
import me.kolganov.grannyshome.dao.AppUserDao;
import me.kolganov.grannyshome.domain.Animal;
import me.kolganov.grannyshome.domain.AppUser;
import me.kolganov.grannyshome.domain.Role;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final AppUserDao userDao;
    private final AnimalDao animalDao;

    @Override
    public List<Animal> getAllCurrentUserAnimals(String login) {
        return animalDao.findByUserLogin(login);
    }

    @Override
    public AppUser getCurrentUser(String login) {
        Optional<AppUser> user = userDao.findByLogin(login);
        return user.orElse(null);
    }

    @Override
    public List<AppUser> getAll() {
        return userDao.findAll();
    }

    @Override
    public AppUser getById(long id) {
        return userDao.findById(id).orElseGet(AppUser::new);
    }

    @Override
    public boolean save(AppUser user) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        Optional<AppUser> userFromDB = userDao.findByLogin(user.getLogin());

        if (userFromDB.isPresent())
            return false;

        user.setRoles(Collections.singletonList(Role.builder().id(1L).role("USER").build()));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.save(user);
        return true;
    }

    @Override
    public void update(AppUser user) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        Optional<AppUser> oldUser = userDao.findById(user.getId());
        oldUser.ifPresent(u -> {
            u.setName(user.getName());
            u.setLogin(user.getLogin());
            u.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userDao.save(u);
        });
    }

    @Override
    public void delete(long id) {
        userDao.deleteById(id);
    }
}
