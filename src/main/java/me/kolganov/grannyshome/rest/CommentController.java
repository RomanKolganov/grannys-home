package me.kolganov.grannyshome.rest;

import lombok.RequiredArgsConstructor;
import me.kolganov.grannyshome.domain.AppUser;
import me.kolganov.grannyshome.domain.Comment;
import me.kolganov.grannyshome.rest.dto.CommentDto;
import me.kolganov.grannyshome.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment")
    public void createComment(@RequestBody CommentDto commentDto, Principal principal) {
        Comment comment = CommentDto.toEntity(commentDto);
        comment.setUserFrom(AppUser.builder().login(principal.getName()).build());
        commentService.save(comment);
    }

    @DeleteMapping("/comment/{id}")
    public void deleteComment(@PathVariable("id") long id, Principal principal) {
        commentService.delete(id, principal);
    }
}
