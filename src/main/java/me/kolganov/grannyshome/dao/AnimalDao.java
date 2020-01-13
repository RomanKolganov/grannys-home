package me.kolganov.grannyshome.dao;

import me.kolganov.grannyshome.domain.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalDao extends JpaRepository<Animal, Long> {
}
