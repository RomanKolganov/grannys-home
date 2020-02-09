package me.kolganov.grannyshome.dao;

import me.kolganov.grannyshome.domain.Animal;
import me.kolganov.grannyshome.domain.AppUser;
import me.kolganov.grannyshome.domain.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Репозиторий для работы с Order ")
class OrderRepositoryTest {
    @Autowired
    private TestEntityManager em;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private AnimalRepository animalRepository;

    @Test
    @DisplayName("должен сохранять и получать сущность")
    void saveAndFindTest() {
        AppUser appUser = new AppUser();
        appUser.setName("Геральт из Ривии");
        appUser.setLogin("Butcher");
        appUser.setPassword("PlotvaTheBestHorseEver");
        appUserRepository.save(appUser);

        Animal animal = new Animal();
        animal.setName("Плотва");
        animalRepository.save(animal);

        Order order = new Order();
        order.setTitle("Отдам лошадь на недельку. Можно кататься!!!");
        order.setDescription("Самовывоз! С меня корм. Обязательно 2 раза в день выгуливать. Кататься осторожно.");
        order.setUser(appUser);
        order.setAnimal(animal);
        orderRepository.save(order);

        Order actualOrder = em.find(Order.class, order.getId());
        assertThat(actualOrder).isNotNull()
                .matches(s -> s.getTitle().equals(actualOrder.getTitle()))
                .matches(s -> s.getDescription().equals(actualOrder.getDescription()))
                .matches(s -> s.getUser().getId().equals(actualOrder.getUser().getId()))
                .matches(s -> s.getAnimal().getId().equals(actualOrder.getAnimal().getId()));
    }

    private Date parseDate(String date) {
        try {
            DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            return new Date(format.parse(date).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
