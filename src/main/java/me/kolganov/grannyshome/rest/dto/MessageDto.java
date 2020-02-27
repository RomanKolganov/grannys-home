package me.kolganov.grannyshome.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.kolganov.grannyshome.domain.Message;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageDto {
    @JsonProperty("id") private Long id;
    @JsonProperty("text") private String text;
    @JsonProperty("creation_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime creationDate;
    @JsonProperty("dialog") private DialogDto dialogDto;
    @JsonProperty("user") private UserDto userDto;

    public static MessageDto toDto(Message message) {
        return MessageDto.builder()
                .id(message.getId())
                .text(message.getText())
                .creationDate(message.getCreationDate().toLocalDateTime())
                .userDto(UserDto.toNoCommentsDto(message.getUser()))
                .dialogDto(DialogDto.toDto(message.getDialog()))
                .build();
    }

    public static Message toEntity(MessageDto messageDto) {
        return Message.builder()
                .text(messageDto.getText())
                .creationDate(Timestamp.valueOf(messageDto.getCreationDate()))
                .user(UserDto.toEntityForRegistration(messageDto.getUserDto()))
                .dialog(DialogDto.toEntity(messageDto.getDialogDto()))
                .build();
    }
}
