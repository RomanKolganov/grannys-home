package me.kolganov.grannyshome.rest;

import lombok.RequiredArgsConstructor;
import me.kolganov.grannyshome.rest.dto.AcceptedOrderDto;
import me.kolganov.grannyshome.rest.dto.UserDto;
import me.kolganov.grannyshome.service.AcceptedOrderService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AcceptedOrderController {
    private final AcceptedOrderService acceptedOrderService;

    @PostMapping("/acceptedOrder")
    public void acceptOrder(@RequestBody AcceptedOrderDto acceptedOrderDto, Principal principal) {
        acceptedOrderDto.setUserDto(UserDto.builder().login(principal.getName()).build());
        acceptedOrderService.create(AcceptedOrderDto.toEntity(acceptedOrderDto));
    }

    @DeleteMapping("/acceptedOrder/{id}")
    public void declineOrder(@PathVariable("id") long id) {
        acceptedOrderService.delete(id);
    }

    @GetMapping("/acceptedOrder")
    public List<AcceptedOrderDto> getUserAcceptedOrders(Principal principal) {
        return acceptedOrderService.getAllUserAcceptedOrders(principal.getName()).stream()
                .map(AcceptedOrderDto::toDto).collect(Collectors.toList());
    }
}
