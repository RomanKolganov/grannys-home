package me.kolganov.grannyshome.service;

import lombok.RequiredArgsConstructor;
import me.kolganov.grannyshome.dao.AcceptedOrderDao;
import me.kolganov.grannyshome.dao.AnimalDao;
import me.kolganov.grannyshome.dao.AppUserDao;
import me.kolganov.grannyshome.dao.OrderDao;
import me.kolganov.grannyshome.domain.AcceptedOrder;
import me.kolganov.grannyshome.domain.Animal;
import me.kolganov.grannyshome.domain.AppUser;
import me.kolganov.grannyshome.domain.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;
    private final AppUserDao userDao;
    private final AnimalDao animalDao;
    private final AcceptedOrderDao acceptedOrderDao;

    @Override
    public List<Order> getAll(String login) {
        List<AcceptedOrder> acceptedOrders = acceptedOrderDao.findByAcceptedUserLogin(login);
        if (acceptedOrders.size() == 0)
            return orderDao.findAll();

        List<Long> idList = new ArrayList<>();
        acceptedOrders.forEach(a -> idList.add(a.getOrder().getId()));
        return orderDao.findAllByIdNotIn(idList);
    }

    @Override
    public Order getById(long id) {
        return orderDao.findById(id).orElseGet(Order::new);
    }

    @Override
    public void save(Order order) {
        Optional<AppUser> user = userDao.findByLogin(order.getUser().getLogin());
        Optional<Animal> animal = animalDao.findById(order.getAnimal().getId());

        user.ifPresent(u -> {
            order.setUser(u);
            animal.ifPresent(a -> {
                order.setAnimal(a);
                orderDao.save(order);
            });
        });
    }

    @Override
    public void update(Order order) {
        Optional<Order> oldOrder = orderDao.findById(order.getId());
        oldOrder.ifPresent(o -> {
            o.setTitle(order.getTitle());
            o.setDescription(order.getDescription());
            orderDao.save(o);
        });
    }

    @Override
    public void delete(long id) {
        orderDao.deleteById(id);
    }
}
