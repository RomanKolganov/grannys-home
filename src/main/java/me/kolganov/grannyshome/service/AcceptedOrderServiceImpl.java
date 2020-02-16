package me.kolganov.grannyshome.service;

import lombok.RequiredArgsConstructor;
import me.kolganov.grannyshome.dao.AcceptedOrderRepository;
import me.kolganov.grannyshome.dao.AppUserRepository;
import me.kolganov.grannyshome.dao.OrderRepository;
import me.kolganov.grannyshome.domain.AcceptedOrder;
import me.kolganov.grannyshome.domain.AppUser;
import me.kolganov.grannyshome.domain.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AcceptedOrderServiceImpl implements AcceptedOrderService {
    private final OrderRepository orderRepository;
    private final AppUserRepository userDao;
    private final AcceptedOrderRepository acceptedOrderRepository;

    @Override
    public List<AcceptedOrder> getAllUserAcceptedOrders(String login) {
        return acceptedOrderRepository.findAllByUserLogin(login);
    }

    @Override
    public void create(AcceptedOrder acceptedOrder) {
        Optional<Order> order = orderRepository.findById(acceptedOrder.getOrder().getId());
        Optional<AppUser> user = userDao.findByLogin(acceptedOrder.getUser().getLogin());
        order.ifPresent(o -> {
            acceptedOrder.setOrder(o);
            user.ifPresent(u -> {
                acceptedOrder.setUser(u);
                acceptedOrderRepository.save(acceptedOrder);
            });
        });
    }

    @Override
    public void delete(long id) {
        acceptedOrderRepository.deleteById(id);
    }
}
