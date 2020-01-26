package me.kolganov.grannyshome.service;

import lombok.RequiredArgsConstructor;
import me.kolganov.grannyshome.dao.AcceptedOrderDao;
import me.kolganov.grannyshome.dao.AppUserDao;
import me.kolganov.grannyshome.dao.OrderDao;
import me.kolganov.grannyshome.domain.AcceptedOrder;
import me.kolganov.grannyshome.domain.AppUser;
import me.kolganov.grannyshome.domain.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AcceptedOrderServiceImpl implements AcceptedOrderService {
    private final OrderDao orderDao;
    private final AppUserDao userDao;
    private final AcceptedOrderDao acceptedOrderDao;

    @Override
    public List<AcceptedOrder> getAllUserAcceptedOrders(String login) {
        return acceptedOrderDao.findByAcceptedUserLogin(login);
    }

    @Override
    public void create(AcceptedOrder acceptedOrder) {
        Optional<Order> order = orderDao.findById(acceptedOrder.getOrder().getId());
        Optional<AppUser> user = userDao.findByLogin(acceptedOrder.getAcceptedUser().getLogin());
        order.ifPresent(o -> {
            acceptedOrder.setOrder(o);
            user.ifPresent(u -> {
                acceptedOrder.setAcceptedUser(u);
                acceptedOrderDao.save(acceptedOrder);
            });
        });
    }

    @Override
    public void delete(long id) {
        acceptedOrderDao.deleteById(id);
    }
}
