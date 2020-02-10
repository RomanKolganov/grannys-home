package me.kolganov.grannyshome.dao;

import me.kolganov.grannyshome.dao.jdbc.AcceptedOrderDaoJdbcImpl;
import me.kolganov.grannyshome.domain.AcceptedOrder;
import me.kolganov.grannyshome.domain.AppUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("Репозиторий для работы с AcceptedOrder ")
class AcceptedOrderRepositoryJdbcImplTest {
    @Autowired
    private AcceptedOrderDaoJdbcImpl daoJdbc;
    @Autowired
    private AcceptedOrderRepository repository;
    @Autowired
    private AppUserRepository appUserRepository;

    @Test
    void test() {
        List<AcceptedOrder> acceptedOrders = daoJdbc.findByAcceptedUserLogin("acceptor");
        acceptedOrders.forEach(System.out::println);
    }

    @Test
    void test2() {
        Optional<AppUser> user = appUserRepository.findByLogin("acceptor");
        user.ifPresent(u -> repository.findAllByUser(u).forEach(System.out::println));
    }

    @Test
    void test3() {
        repository.findAllByUserLogin("dude").forEach(System.out::println);
    }
}
