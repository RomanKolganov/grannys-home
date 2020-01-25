package me.kolganov.grannyshome.dao;

import me.kolganov.grannyshome.domain.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimalDao extends JpaRepository<Animal, Long> {
    List<Animal> findByUserLogin(String login);
}
