package me.kolganov.grannyshome.dao;

import me.kolganov.grannyshome.dao.jdbc.MessageDaoJdbcImpl;
import me.kolganov.grannyshome.domain.AppUser;
import me.kolganov.grannyshome.domain.Dialog;
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
        Dialog dialog = Dialog.builder()
                .id(1L)
                .userTo(AppUser.builder().id(1L).build())
                .userFrom(AppUser.builder().id(4L).build())
                .build();
        List<Message> messages = messageDaoJdbc.findAllByDialog(dialog);
        messages.forEach(System.out::println);
    }
}
