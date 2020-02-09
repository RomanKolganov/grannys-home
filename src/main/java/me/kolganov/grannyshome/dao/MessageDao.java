package me.kolganov.grannyshome.dao;

import me.kolganov.grannyshome.domain.Dialog;
import me.kolganov.grannyshome.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageDao extends JpaRepository<Message, Long>{
    List<Message> findAllByDialog(Dialog dialog);
}
