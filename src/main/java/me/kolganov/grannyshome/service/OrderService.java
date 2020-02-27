package me.kolganov.grannyshome.service;

import me.kolganov.grannyshome.domain.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAll(String login);
    void save(Order order);
    void delete(long id);
}
