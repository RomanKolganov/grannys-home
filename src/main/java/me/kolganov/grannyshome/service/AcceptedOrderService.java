package me.kolganov.grannyshome.service;

import me.kolganov.grannyshome.domain.AcceptedOrder;

import java.util.List;

public interface AcceptedOrderService {
    List<AcceptedOrder> getAllCurrentUserAcceptedOrders(String login);
    List<AcceptedOrder> getAllUserAcceptedOrders(String login);
    void create(AcceptedOrder acceptedOrder);
    void delete(long id);
    void delete(long id, long userId);
}
