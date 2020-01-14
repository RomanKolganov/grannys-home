package me.kolganov.grannyshome.dao;

import me.kolganov.grannyshome.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<AppUser, Long> {
}
