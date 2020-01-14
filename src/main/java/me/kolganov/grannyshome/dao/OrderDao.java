package me.kolganov.grannyshome.dao;

import me.kolganov.grannyshome.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<Order, Long> {
}
