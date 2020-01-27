package me.kolganov.grannyshome.dao;

import me.kolganov.grannyshome.dao.jdbc.AcceptedOrderDaoJdbc;
import me.kolganov.grannyshome.domain.AcceptedOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcceptedOrderDao extends JpaRepository<AcceptedOrder, Long>, AcceptedOrderDaoJdbc {
}
