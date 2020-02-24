package me.kolganov.grannyshome.rest;

import lombok.RequiredArgsConstructor;
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

    @GetMapping("/order")
    public List<OrderDto> getAllOrders(Principal principal) {
        return orderService.getAll(principal.getName()).stream().map(OrderDto::toDto).collect(Collectors.toList());
    }

    @PostMapping("/order")
    public void createOrder(@RequestBody OrderDto orderDto, Principal principal) {
        orderDto.setUserDto(UserDto.builder().login(principal.getName()).build());
        orderService.save(OrderDto.toEntity(orderDto));
    }

    @PutMapping("order/{id}")
    public void updateOrder(@PathVariable("id") long id,
                            @RequestBody OrderDto orderDto) {
        orderDto.setId(id);
        orderService.update(OrderDto.toJustOrderEntity(orderDto));
    }

    @DeleteMapping("/order/{id}")
    public void deleteOrder(@PathVariable("id") long id) {
        orderService.delete(id);
    }
}
