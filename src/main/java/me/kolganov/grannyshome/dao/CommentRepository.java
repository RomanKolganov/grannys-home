package me.kolganov.grannyshome.dao;

import me.kolganov.grannyshome.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
