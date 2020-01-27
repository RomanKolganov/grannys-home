package me.kolganov.grannyshome.dao.jdbc;

import me.kolganov.grannyshome.domain.Message;

import java.util.List;

public interface MessageDaoJdbc {
    List<Message> findAllByUserIdOneAndUserIdTwo(long userIdTo, long userIdFrom);
}
