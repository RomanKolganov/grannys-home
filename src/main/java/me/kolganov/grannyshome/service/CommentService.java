package me.kolganov.grannyshome.service;

import me.kolganov.grannyshome.domain.Comment;

import java.security.Principal;
import java.util.List;

public interface CommentService {
    void save(Comment comment);
    void delete(long id, Principal principal);
}
