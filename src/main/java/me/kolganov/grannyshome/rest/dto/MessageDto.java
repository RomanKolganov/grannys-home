package me.kolganov.grannyshome.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.kolganov.grannyshome.domain.Dialog;
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
    @JsonProperty("dialog_id") private long dialogId;
    @JsonProperty("userFrom") private UserDto userDtoFrom;
    @JsonProperty("userTo") private UserDto userDtoTo;

    public static MessageDto toDto(Message message) {
        return MessageDto.builder()
                .id(message.getId())
                .text(message.getText())
                .creationDate(message.getCreationDate().toLocalDateTime())
                .userDtoFrom(UserDto.toNoCommentsDto(message.getUserFrom()))
                .userDtoTo(UserDto.toNoCommentsDto(message.getUserTo()))
                .build();
    }

    public static Message toEntity(MessageDto messageDto) {
        return Message.builder()
                .text(messageDto.getText())
                .creationDate(Timestamp.valueOf(messageDto.getCreationDate()))
                .dialog(Dialog.builder().id(messageDto.getDialogId()).build())
                .userTo(UserDto.toEntity(messageDto.getUserDtoTo()))
                .userFrom(UserDto.toEntityForRegistration(messageDto.getUserDtoFrom()))
                .build();
    }
}
