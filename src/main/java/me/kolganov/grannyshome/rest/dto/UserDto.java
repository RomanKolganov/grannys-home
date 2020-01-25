package me.kolganov.grannyshome.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import me.kolganov.grannyshome.domain.AppUser;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
public class UserDto {
    @JsonProperty("id") private Long id;
    @JsonProperty("name") private String name;
    @JsonProperty("login") private String login;
    @JsonProperty("password") private String password;
    @JsonProperty("comments") private List<CommentDto> commentDtoList;
    @JsonProperty("animals") private List<AnimalDto> animalDtoList;

    public static UserDto toDto(AppUser user) {
        List<CommentDto> commentDtoList = new ArrayList<>();
        user.getCommentsTo().forEach(c -> commentDtoList.add(CommentDto.toDto(c)));

        List<AnimalDto> animalDtoList = new ArrayList<>();
        user.getAnimals().forEach(a -> animalDtoList.add(AnimalDto.toDto(a)));

        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .login(user.getLogin())
                .password(user.getPassword())
                .commentDtoList(commentDtoList)
                .animalDtoList(animalDtoList)
                .build();
    }

    public static UserDto toNoCommentsDto(AppUser user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .login(user.getLogin())
                .password(user.getPassword())
                .build();
    }

    public static AppUser toEntity(UserDto userDto) {
        return AppUser.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .password(userDto.getPassword())
                .login(userDto.getLogin())
                .build();
    }

    public static AppUser toEntityForRegistration(UserDto userDto) {
        return AppUser.builder()
                .login(userDto.getLogin())
                .password(userDto.getPassword())
                .build();
    }
}
