package me.kolganov.grannyshome.dao;

import me.kolganov.grannyshome.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserDao extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByLogin(String login);
}
