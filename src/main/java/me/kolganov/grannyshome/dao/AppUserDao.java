package me.kolganov.grannyshome.dao;

import me.kolganov.grannyshome.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserDao extends JpaRepository<AppUser, Long> {
}
