package me.kolganov.grannyshome.rest;

import lombok.RequiredArgsConstructor;
import me.kolganov.grannyshome.domain.AppUser;
import me.kolganov.grannyshome.domain.Order;
import me.kolganov.grannyshome.rest.dto.AcceptedOrderDto;
import me.kolganov.grannyshome.rest.dto.OrderDto;
import me.kolganov.grannyshome.rest.dto.UserDto;
import me.kolganov.grannyshome.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/order/{page_number}/{size}/{field}")
    public List<OrderDto> getAllOrders(@PathVariable("page_number") int pageNumber,
                                       @PathVariable("size") int size,
                                       @PathVariable("field") String field) {
        return orderService.getAll(pageNumber, size, field).stream().map(OrderDto::toDto).collect(Collectors.toList());
    }

    @GetMapping("/order")
    public List<OrderDto> getAllOrders() {
        return orderService.getAll().stream().map(OrderDto::toDto).collect(Collectors.toList());
    }

    @GetMapping("/order/{id}")
    public OrderDto getOrderById(@PathVariable("id") long id) {
        return OrderDto.toDto(orderService.getById(id));
    }

    @PostMapping("/order")
    public void createOrder(@RequestBody OrderDto orderDto, Principal principal) {
        orderDto.setUserDto(UserDto.builder().login(principal.getName()).build());
        orderService.save(OrderDto.toEntity(orderDto));
    }

    @PostMapping("/order/accept")
    public void acceptOrder(@RequestBody AcceptedOrderDto acceptedOrderDto, Principal principal) {
        acceptedOrderDto.setUserDto(UserDto.builder().login(principal.getName()).build());
        orderService.save(AcceptedOrderDto.toEntity(acceptedOrderDto));
    }

    @PutMapping("/order/{id}")
    public void updateOrder(@PathVariable("id") long id,
                            @RequestBody OrderDto orderDto) {
        orderService.update(OrderDto.toEntity(orderDto));
    }

    @DeleteMapping("/order/{id}")
    public void deleteOrder(@PathVariable("id") long id) {
        orderService.delete(id);
    }
}
