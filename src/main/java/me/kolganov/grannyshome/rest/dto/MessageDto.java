package me.kolganov.grannyshome.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.kolganov.grannyshome.domain.Message;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageDto {
    @JsonProperty("id") private Long id;
    @JsonProperty("text") private String text;
    @JsonProperty("userFrom") private UserDto userDtoFrom;
    @JsonProperty("userTo") private UserDto userDtoTo;

    public static MessageDto toDto(Message message) {
        return MessageDto.builder()
                .id(message.getId())
                .text(message.getText())
                .userDtoFrom(UserDto.toNoCommentsDto(message.getUserFrom()))
                .userDtoTo(UserDto.toNoCommentsDto(message.getUserTo()))
                .build();
    }
}
