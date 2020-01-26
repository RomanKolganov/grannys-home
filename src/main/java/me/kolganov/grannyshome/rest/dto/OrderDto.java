package me.kolganov.grannyshome.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.kolganov.grannyshome.domain.Order;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
public class OrderDto {
    @JsonProperty("id") private Long id;
    @JsonProperty("title") private String title;
    @JsonProperty("description") private String description;
    @JsonProperty("animal") private AnimalDto animalDto;
    @JsonProperty("user") private UserDto userDto;

    public static OrderDto toDto(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .title(order.getTitle())
                .description(order.getDescription())
                .animalDto(AnimalDto.toDto(order.getAnimal()))
                .userDto(UserDto.toNoCommentsDto(order.getUser()))
                .build();
    }

    public static Order toEntity(OrderDto orderDto) {
        return Order.builder()
                .id(orderDto.getId())
                .title(orderDto.getTitle())
                .description(orderDto.getDescription())
                .animal(AnimalDto.toEntity(orderDto.getAnimalDto()))
                .user(UserDto.toEntity(orderDto.getUserDto()))
                .build();

    }

    public static Order toJustOrderEntity(OrderDto orderDto) {
        return Order.builder().id(orderDto.getId()).build();
    }
}
