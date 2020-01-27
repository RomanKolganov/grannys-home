package me.kolganov.grannyshome.dao;

import me.kolganov.grannyshome.domain.Dialog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DialogDao extends JpaRepository<Dialog, Long> {
}
