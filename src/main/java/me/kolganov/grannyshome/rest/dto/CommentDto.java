package me.kolganov.grannyshome.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import me.kolganov.grannyshome.domain.AppUser;
import me.kolganov.grannyshome.domain.Comment;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
public class CommentDto {
    @JsonProperty("id") private Long id;
    @JsonProperty("title") private String title;
    @JsonProperty("text") private String text;
    @JsonProperty("userFrom") private UserDto userDtoFrom;

    public static CommentDto toDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .title(comment.getTitle())
                .text(comment.getText())
                .userDtoFrom(UserDto.toNoCommentsDto(comment.getUserFrom()))
                .build();
    }

    public static Comment toEntity(CommentDto commentDto) {
        return Comment.builder()
                .id(commentDto.getId())
                .title(commentDto.getTitle())
                .text(commentDto.getText())
                .build();
    }
}
