package me.kolganov.grannyshome.dao;

import me.kolganov.grannyshome.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
}
