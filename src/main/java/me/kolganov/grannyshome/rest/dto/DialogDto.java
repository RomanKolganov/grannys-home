package me.kolganov.grannyshome.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.kolganov.grannyshome.domain.Dialog;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DialogDto {
    @JsonProperty("id") private Long id;
    @JsonProperty("userTo") private UserDto userDtoTo;

    public static DialogDto toDto(Dialog dialog) {
        return DialogDto.builder()
                .id(dialog.getId())
                .userDtoTo(UserDto.toNoCommentsDto(dialog.getUserTo()))
                .build();
    }
}
