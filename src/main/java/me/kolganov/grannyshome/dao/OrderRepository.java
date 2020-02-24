package me.kolganov.grannyshome.dao;

import me.kolganov.grannyshome.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByStatusAndIdNotIn(String status, List<Long> idList);
}
