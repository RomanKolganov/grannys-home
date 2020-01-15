package me.kolganov.grannyshome.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.kolganov.grannyshome.domain.Animal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalDto {
    private Long id;
    private String name;
    private Integer quantity;

    public static AnimalDto toDto(Animal animal) {
        return new AnimalDto(animal.getId(), animal.getName(), animal.getQuantity());
    }

    public static Animal toEntity(AnimalDto animalDto) {
        return Animal.builder()
                .id(animalDto.getId())
                .name(animalDto.getName())
                .quantity(animalDto.getQuantity())
                .build();
    }
}
