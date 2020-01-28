package me.kolganov.grannyshome.dao;

import me.kolganov.grannyshome.domain.Dialog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DialogDao extends JpaRepository<Dialog, Long> {
    List<Dialog> findAllByUserFromLogin(String login);
}
