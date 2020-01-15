package me.kolganov.grannyshome.service;

import me.kolganov.grannyshome.domain.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAll();
    Order getById(long id);
    void save(Order order);
    void update(Order order);
    void delete(long id);
}
