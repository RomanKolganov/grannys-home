package me.kolganov.grannyshome.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.kolganov.grannyshome.domain.AppUser;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String login;
    private String password;

    public static UserDto toDto(AppUser user) {
        return new UserDto(user.getId(), user.getName(), user.getLogin(), user.getPassword());
    }

    public static AppUser toEntity(UserDto userDto) {
        return new AppUser(userDto.getId(), userDto.getName(), userDto.getLogin(), userDto.getPassword());
    }
}
