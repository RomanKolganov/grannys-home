package me.kolganov.grannyshome.service;

import lombok.RequiredArgsConstructor;
import me.kolganov.grannyshome.dao.AnimalDao;
import me.kolganov.grannyshome.dao.AppUserDao;
import me.kolganov.grannyshome.dao.DialogDao;
import me.kolganov.grannyshome.domain.Animal;
import me.kolganov.grannyshome.domain.AppUser;
import me.kolganov.grannyshome.domain.Dialog;
import me.kolganov.grannyshome.domain.Role;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final AppUserDao userDao;
    private final AnimalDao animalDao;
    private final DialogDao dialogDao;

    @Override
    public List<Dialog> getAllCurrentUserDialogs(String login) {
        return dialogDao.findAllByUserFromLogin(login);
    }

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
    public AppUser getById(long id) {
        return userDao.findById(id).orElseGet(AppUser::new);
    }

    @Override
    public boolean save(AppUser user) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Optional<AppUser> userFromDB = userDao.findByLogin(user.getLogin());

        if (userFromDB.isPresent())
            return false;

        user.setRoles(Collections.singletonList(Role.builder().id(1L).build()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        AppUser savedUser = userDao.save(user);
        System.out.println(savedUser);
        return true;
    }

    @Override
    public void update(AppUser user) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Optional<AppUser> oldUser = userDao.findById(user.getId());
        oldUser.ifPresent(u -> {
            u.setName(user.getName());
            u.setLogin(user.getLogin());
            if (user.getPassword() != null && !"".equals(user.getPassword()))
                u.setPassword(passwordEncoder.encode(user.getPassword()));
            userDao.save(u);
        });
    }
}
