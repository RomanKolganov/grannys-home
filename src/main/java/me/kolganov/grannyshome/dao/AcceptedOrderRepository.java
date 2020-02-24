package me.kolganov.grannyshome.dao;

import me.kolganov.grannyshome.domain.AcceptedOrder;
import me.kolganov.grannyshome.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AcceptedOrderRepository extends JpaRepository<AcceptedOrder, Long> {
    List<AcceptedOrder> findAllByUserLogin(String login);
    void deleteByIdAndUser(long id, AppUser user);
}
