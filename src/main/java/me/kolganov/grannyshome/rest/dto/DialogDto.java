package me.kolganov.grannyshome.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.kolganov.grannyshome.domain.AppUser;
import me.kolganov.grannyshome.domain.Dialog;
import org.springframework.security.core.userdetails.User;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DialogDto {
    @JsonProperty("id") private Long id;
    @JsonProperty("userTo") private UserDto userDtoTo;
    @JsonProperty("user") private UserDto userDto;

    public static DialogDto toDto(Dialog dialog) {
        return DialogDto.builder()
                .id(dialog.getId())
                .userDtoTo(UserDto.toNoCommentsDto(dialog.getUserTo()))
                .build();
    }

    public static Dialog toNewEntity(DialogDto dialogDto) {
        return Dialog.builder()
                .userTo(AppUser.builder().id(dialogDto.getUserDtoTo().getId()).build())
                .user(AppUser.builder().login(dialogDto.getUserDto().getLogin()).build())
                .build();
    }

    public static Dialog toEntity(DialogDto dialogDto) {
        return Dialog.builder().id(dialogDto.getId()).build();
    }
}
