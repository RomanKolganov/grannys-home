package me.kolganov.grannyshome.service;

import lombok.RequiredArgsConstructor;
import me.kolganov.grannyshome.dao.AcceptedOrderRepository;
import me.kolganov.grannyshome.dao.AnimalRepository;
import me.kolganov.grannyshome.dao.AppUserRepository;
import me.kolganov.grannyshome.dao.OrderRepository;
import me.kolganov.grannyshome.domain.AcceptedOrder;
import me.kolganov.grannyshome.domain.Animal;
import me.kolganov.grannyshome.domain.AppUser;
import me.kolganov.grannyshome.domain.Order;
import me.kolganov.grannyshome.domain.enumeration.OrderStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final AppUserRepository userDao;
    private final AnimalRepository animalRepository;
    private final AcceptedOrderRepository acceptedOrderRepository;

    @Override
    public List<Order> getAll(String login) {
        List<AcceptedOrder> acceptedOrders = acceptedOrderRepository.findAllByUserLogin(login);
        if (acceptedOrders.size() == 0) {
            List<Order> orders = orderRepository.findAll();
            orders.removeIf(o -> !OrderStatus.NEW.name().equals(o.getStatus()));
            return orders;
        }

        List<Long> idList = new ArrayList<>();
        acceptedOrders.forEach(a -> idList.add(a.getOrder().getId()));
        List<Order> orders = orderRepository.findAllByIdNotIn(idList);
        orders.removeIf(o -> !OrderStatus.NEW.name().equals(o.getStatus()));
        return orders;
    }

    @Override
    public void save(Order order) {
        Optional<AppUser> user = userDao.findByLogin(order.getUser().getLogin());
        Optional<Animal> animal = animalRepository.findById(order.getAnimal().getId());

        user.ifPresent(u -> {
            order.setUser(u);
            order.setStatus(OrderStatus.NEW.name());
            animal.ifPresent(a -> {
                order.setAnimal(a);
                orderRepository.save(order);
            });
        });
    }

    @Override
    public void delete(long id) {
        orderRepository.deleteById(id);
    }
}
