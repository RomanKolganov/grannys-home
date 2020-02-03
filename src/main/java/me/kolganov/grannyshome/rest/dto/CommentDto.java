package me.kolganov.grannyshome.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import me.kolganov.grannyshome.domain.AppUser;
import me.kolganov.grannyshome.domain.Comment;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentDto {
    @JsonProperty("id") private Long id;
    @JsonProperty("text") private String text;
    @JsonProperty("creation_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime creationDate;
    @JsonProperty("userFrom") private UserDto userDtoFrom;
    @JsonProperty("userTo") private UserDto userDtoTo;

    public static CommentDto toDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .text(comment.getText())
                .creationDate(comment.getCreationDate().toLocalDateTime())
                .userDtoFrom(UserDto.toNoCommentsDto(comment.getUserFrom()))
                .build();
    }

    public static Comment toEntity(CommentDto commentDto) {
        return Comment.builder()
                .text(commentDto.getText())
                .userTo(UserDto.toEntity(commentDto.userDtoTo))
                .build();
    }
}
