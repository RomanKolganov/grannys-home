package me.kolganov.grannyshome.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.kolganov.grannyshome.domain.AcceptedOrder;
import me.kolganov.grannyshome.domain.AppUser;
import me.kolganov.grannyshome.domain.Order;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AcceptedOrderDto {
    @JsonProperty("id") private Long id;
    @JsonProperty("user") private UserDto userDto;
    @JsonProperty("order") private OrderDto orderDto;

    public static AcceptedOrder toEntity(AcceptedOrderDto acceptedOrderDto) {
        return AcceptedOrder.builder()
                .user(AppUser.builder()
                        .login(acceptedOrderDto.getUserDto().getLogin())
                        .build())
                .order(Order.builder().id(acceptedOrderDto.getOrderDto().getId()).build())
                .build();
    }

    public static AcceptedOrderDto toDto(AcceptedOrder acceptedOrder) {
        return AcceptedOrderDto.builder()
                .id(acceptedOrder.getId())
                .userDto(UserDto.toNoCommentsDto(acceptedOrder.getUser()))
                .orderDto(OrderDto.toDto(acceptedOrder.getOrder()))
                .build();
    }
}
