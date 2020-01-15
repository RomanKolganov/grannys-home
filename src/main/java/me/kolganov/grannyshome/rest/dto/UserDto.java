package me.kolganov.grannyshome.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.kolganov.grannyshome.domain.AppUser;

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

    public static UserDto toDto(AppUser user) {
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
                .login(userDto.getLogin())
                .password(userDto.getPassword())
                .build();
    }
}
