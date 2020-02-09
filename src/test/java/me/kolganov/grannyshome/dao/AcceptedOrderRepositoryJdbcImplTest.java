package me.kolganov.grannyshome.dao;

import me.kolganov.grannyshome.dao.jdbc.AcceptedOrderDaoJdbcImpl;
import me.kolganov.grannyshome.domain.AcceptedOrder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@DisplayName("Репозиторий для работы с AcceptedOrder ")
class AcceptedOrderRepositoryJdbcImplTest {
    @Autowired
    private AcceptedOrderDaoJdbcImpl daoJdbc;

    @Test
    void test() {
        List<AcceptedOrder> acceptedOrders = daoJdbc.findByAcceptedUserLogin("acceptor");
        acceptedOrders.forEach(System.out::println);
    }
}
