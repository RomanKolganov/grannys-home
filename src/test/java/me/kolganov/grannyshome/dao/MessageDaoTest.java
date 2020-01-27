package me.kolganov.grannyshome.dao;

import me.kolganov.grannyshome.dao.jdbc.MessageDaoJdbcImpl;
import me.kolganov.grannyshome.domain.Message;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@DisplayName("Репозиторий для работы с Message ")
class MessageDaoTest {
    @Autowired
    private MessageDaoJdbcImpl messageDaoJdbc;

    @Test
    void test() {
        List<Message> messages = messageDaoJdbc.findAllByUserIdOneAndUserIdTwo(1L, 4L);
        messages.forEach(System.out::println);
    }
}
