package me.kolganov.grannyshome.rest;

import lombok.RequiredArgsConstructor;
import me.kolganov.grannyshome.rest.dto.CommentDto;
import me.kolganov.grannyshome.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/comment")
    public List<CommentDto> getAllComments() {
        return commentService.getAll().stream().map(CommentDto::toDto).collect(Collectors.toList());
    }

    @GetMapping("/comment/{id}")
    public CommentDto getCommentById(@PathVariable("id") long id) {
        return CommentDto.toDto(commentService.getById(id));
    }

    @PostMapping("/comment")
    public void createComment(@RequestBody CommentDto commentDto) {
        commentService.save(CommentDto.toEntity(commentDto));
    }

    @PutMapping("/comment/{id}")
    public void updateComment(@PathVariable("id") long id,
                              @RequestBody CommentDto commentDto) {
        commentService.update(CommentDto.toEntity(commentDto));
    }

    @DeleteMapping("/comment/{id}")
    public void deleteComment(@PathVariable("id") long id) {
        commentService.delete(id);
    }
}
