package me.kolganov.grannyshome.service;

import me.kolganov.grannyshome.domain.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAll();
    Comment getById(long id);
    void save(Comment comment);
    void update(Comment comment);
    void delete(long id);
}
