package me.kolganov.grannyshome.service;

import lombok.RequiredArgsConstructor;
import me.kolganov.grannyshome.dao.AnimalDao;
import me.kolganov.grannyshome.dao.AppUserDao;
import me.kolganov.grannyshome.dao.OrderDao;
import me.kolganov.grannyshome.domain.Animal;
import me.kolganov.grannyshome.domain.AppUser;
import me.kolganov.grannyshome.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;
    private final AppUserDao userDao;
    private final AnimalDao animalDao;

    @Override
    public List<Order> getAll(int pageNumber, int size, String field) {
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(field));
        Page<Order> orders = orderDao.findAll(pageable);
        return orders.getContent();
    }

    @Override
    public List<Order> getAll() {
        return orderDao.findAll();
    }

    @Override
    public Order getById(long id) {
        return orderDao.findById(id).orElseGet(Order::new);
    }

    @Override
    public void save(Order order) {
        Optional<AppUser> user = userDao.findById(order.getUser().getId());
        Optional<Animal> animal = animalDao.findById(order.getAnimal().getId());

        if (user.isPresent() && animal.isPresent()) {
            order.setUser(user.get());
            order.setAnimal(animal.get());
            order.setDateCreation(new Date(System.currentTimeMillis()));
            orderDao.save(order);
        }
    }

    @Override
    public void update(Order order) {
        Optional<Order> oldOrder = orderDao.findById(order.getId());
        oldOrder.ifPresent(o -> {
            o.setTitle(order.getTitle());
            o.setDescription(order.getDescription());
            o.setDateCreation(order.getDateCreation());
            orderDao.save(o);
        });
    }

    @Override
    public void delete(long id) {
        orderDao.deleteById(id);
    }
}
