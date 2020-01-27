package me.kolganov.grannyshome.dao;

import me.kolganov.grannyshome.dao.jdbc.MessageDaoJdbc;
import me.kolganov.grannyshome.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageDao extends JpaRepository<Message, Long>, MessageDaoJdbc {
}
