package me.kolganov.grannyshome.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.kolganov.grannyshome.domain.Animal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
public class AnimalDto {
    @JsonProperty("id") private Long id;
    @JsonProperty("name") private String name;
    @JsonProperty("quantity") private Integer quantity;

    public static AnimalDto toDto(Animal animal) {
        return AnimalDto.builder()
                .id(animal.getId())
                .name(animal.getName())
                .quantity(animal.getQuantity())
                .build();
    }

    public static Animal toEntity(AnimalDto animalDto) {
        return Animal.builder()
                .id(animalDto.getId())
                .name(animalDto.getName())
                .quantity(animalDto.getQuantity())
                .build();
    }
}
