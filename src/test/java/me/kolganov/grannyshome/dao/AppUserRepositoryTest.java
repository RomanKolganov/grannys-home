package me.kolganov.grannyshome.dao;

import me.kolganov.grannyshome.domain.AppUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Репозиторий для работы с AppUser ")
class AppUserRepositoryTest {
    @Autowired
    private TestEntityManager em;
    @Autowired
    private AppUserRepository appUserRepository;

    @Test
    @DisplayName("должен сохранять и получать сущность")
    void saveAndFindTest() {
        AppUser appUser = new AppUser();
        appUser.setName("Геральт из Ривии");
        appUser.setLogin("Butcher");
        appUser.setPassword("PlotvaTheBestHorseEver");
        appUserRepository.save(appUser);

        AppUser actualAppUser = em.find(AppUser.class, appUser.getId());
        assertThat(actualAppUser).isNotNull()
                .matches(s -> s.getName().equals(appUser.getName()))
                .matches(s -> s.getLogin().equals(appUser.getLogin()))
                .matches(s -> s.getPassword().equals(appUser.getPassword()));
    }
}
