package me.kolganov.grannyshome.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.kolganov.grannyshome.domain.Animal;
import me.kolganov.grannyshome.domain.AppUser;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
public class AnimalDto {
    @JsonProperty("id") private Long id;
    @JsonProperty("name") private String name;
    @JsonProperty("user") private UserDto userDto;

    public static AnimalDto toDto(Animal animal) {
        return AnimalDto.builder()
                .id(animal.getId())
                .name(animal.getName())
                .build();
    }

    public static Animal toEntity(AnimalDto animalDto) {
        return Animal.builder()
                .id(animalDto.getId())
                .name(animalDto.getName())
                .build();
    }

    public static Animal toEntityWithUser(AnimalDto animalDto) {
        return Animal.builder()
                .id(animalDto.getId())
                .name(animalDto.getName())
                .user(AppUser.builder().login(animalDto.getUserDto().getLogin()).build())
                .build();
    }
}
