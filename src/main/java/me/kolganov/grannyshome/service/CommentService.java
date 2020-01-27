package me.kolganov.grannyshome.service;

import me.kolganov.grannyshome.domain.Comment;

public interface CommentService {
    void save(Comment comment);
    void delete(long id, String login);
}
