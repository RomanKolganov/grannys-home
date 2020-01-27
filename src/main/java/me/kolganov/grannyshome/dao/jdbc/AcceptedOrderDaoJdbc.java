package me.kolganov.grannyshome.dao.jdbc;

import me.kolganov.grannyshome.domain.AcceptedOrder;

import java.util.List;

public interface AcceptedOrderDaoJdbc {
    List<AcceptedOrder> findByAcceptedUserLogin(String login);
    void deleteById(long id);
}
