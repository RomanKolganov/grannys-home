package me.kolganov.grannyshome.dao;

import me.kolganov.grannyshome.domain.AppUser;
import me.kolganov.grannyshome.domain.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

@DataJpaTest
@DisplayName("Репозиторий для работы с AcceptedOrder ")
class AcceptedOrderRepositoryTest {
    @Autowired
    private TestEntityManager em;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private AcceptedOrderRepository acceptedOrderRepository;

    @Test
    void test() {
        System.out.println(acceptedOrderRepository.findAll());
        Optional<Order> order = orderRepository.findById(1L);
        Optional<AppUser> user1 = appUserRepository.findById(1L);
        Optional<AppUser> user2 = appUserRepository.findById(2L);

        acceptedOrderRepository.deleteAllByOrderAndUserNot(order.get(), user1.get());
        System.out.println(acceptedOrderRepository.findAll());
    }
}
