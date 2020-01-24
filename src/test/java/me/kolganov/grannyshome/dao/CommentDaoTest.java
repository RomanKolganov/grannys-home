package me.kolganov.grannyshome.dao;

import me.kolganov.grannyshome.domain.AppUser;
import me.kolganov.grannyshome.domain.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Репозиторий для работы с Comment ")
class CommentDaoTest {
    @Autowired
    private TestEntityManager em;
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private AppUserDao appUserDao;

    @Test
    @DisplayName("должен сохранять и получать сущность")
    void saveAndFindTest() {
        AppUser appUser = new AppUser();
        appUser.setName("Геральт из Ривии");
        appUser.setLogin("Butcher");
        appUser.setPassword("PlotvaTheBestHorseEver");
        appUserDao.save(appUser);

        Comment comment = new Comment();
        comment.setTitle("Хорошо ладит с лошадьми");
        comment.setText("Заставляет шевелиться любую лошадь");
        comment.setUserTo(appUser);
        commentDao.save(comment);

        Comment actualComment = em.find(Comment.class, comment.getId());
        assertThat(actualComment).isNotNull()
                .matches(s -> s.getTitle().equals(comment.getTitle()))
                .matches(s -> s.getText().equals(comment.getText()))
                .matches(s -> s.getUserTo().getId().equals(appUser.getId()));
    }
}
