package me.kolganov.grannyshome.service;

import lombok.RequiredArgsConstructor;
import me.kolganov.grannyshome.dao.AcceptedOrderRepository;
import me.kolganov.grannyshome.dao.AppUserRepository;
import me.kolganov.grannyshome.dao.OrderRepository;
import me.kolganov.grannyshome.domain.AcceptedOrder;
import me.kolganov.grannyshome.domain.AppUser;
import me.kolganov.grannyshome.domain.Order;
import me.kolganov.grannyshome.domain.enumeration.OrderStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AcceptedOrderServiceImpl implements AcceptedOrderService {
    private final OrderRepository orderRepository;
    private final AppUserRepository userRepository;
    private final AcceptedOrderRepository acceptedOrderRepository;

    @Override
    public List<AcceptedOrder> getAllCurrentUserAcceptedOrders(String login) {
        return acceptedOrderRepository.findAllByUserLogin(login);
    }

    @Override
    public List<AcceptedOrder> getAllUserAcceptedOrders(String login) {
        Optional<AppUser> user = userRepository.findByLogin(login);
        List<AcceptedOrder> acceptedOrders = new ArrayList<>();

        if (user.isPresent()) {
            List<Order> orders = user.get().getCreatedOrders();
            orders.forEach(o -> acceptedOrders.addAll(o.getAcceptedOrders()));
            return acceptedOrders;
        }
        return null;
    }

    @Override
    public void create(AcceptedOrder acceptedOrder) {
        Optional<Order> order = orderRepository.findById(acceptedOrder.getOrder().getId());
        Optional<AppUser> user = userRepository.findByLogin(acceptedOrder.getUser().getLogin());
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

    @Transactional
    @Override
    public void delete(long id, long userId) {
        Optional<AppUser> user = userRepository.findById(userId);
        user.ifPresent(u -> acceptedOrderRepository.deleteByIdAndUser(id, u));
    }

    @Transactional
    @Override
    public void confirm(long orderId, long userId) {
        Optional<Order> orderFromDB = orderRepository.findById(orderId);
        orderFromDB.ifPresent(o -> {
            Optional<AppUser> user = userRepository.findById(userId);
            user.ifPresent(u -> {
                acceptedOrderRepository.deleteAllByOrderAndUserNot(o, u);
                o.setStatus(OrderStatus.CONFIRMED.name());
                orderRepository.save(o);
            });
        });
    }
}
