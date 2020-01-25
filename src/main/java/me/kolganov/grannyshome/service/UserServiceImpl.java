package me.kolganov.grannyshome.service;

import lombok.RequiredArgsConstructor;
import me.kolganov.grannyshome.dao.AppUserDao;
import me.kolganov.grannyshome.domain.Animal;
import me.kolganov.grannyshome.domain.AppUser;
import me.kolganov.grannyshome.rest.dto.AnimalDto;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final AppUserDao userDao;

    @Override
    public List<Animal> getAllCurrentUserAnimals(Principal principal) {
        Optional<AppUser> user = userDao.findByLogin(principal.getName());
        return user.map(AppUser::getAnimals).orElse(null);
    }

    @Override
    public AppUser getCurrentUser(Principal principal) {
        Optional<AppUser> user = userDao.findByLogin(principal.getName());
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
    public void save(AppUser user) {
        userDao.save(user);
    }

    @Override
    public void update(AppUser user) {
        Optional<AppUser> oldUser = userDao.findById(user.getId());
        oldUser.ifPresent(u -> {
            u.setName(user.getName());
            u.setLogin(user.getLogin());
            userDao.save(u);
        });
    }

    @Override
    public void delete(long id) {
        userDao.deleteById(id);
    }
}
