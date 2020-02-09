package me.kolganov.grannyshome.service;

import lombok.RequiredArgsConstructor;
import me.kolganov.grannyshome.dao.AnimalRepository;
import me.kolganov.grannyshome.dao.AppUserRepository;
import me.kolganov.grannyshome.dao.DialogRepository;
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
    private final AppUserRepository userDao;
    private final AnimalRepository animalRepository;
    private final DialogRepository dialogRepository;

    @Override
    public List<Dialog> getAllCurrentUserDialogs(String login) {
        Optional<AppUser> user = userDao.findByLogin(login);
        return user.map(appUser -> dialogRepository.findAllByUserOrUserTo(appUser, appUser)).orElse(null);
    }

    @Override
    public List<Animal> getAllCurrentUserAnimals(String login) {
        return animalRepository.findByUserLogin(login);
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
        user.setName(user.getLogin());
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
