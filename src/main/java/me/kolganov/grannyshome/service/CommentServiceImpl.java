package me.kolganov.grannyshome.service;

import lombok.RequiredArgsConstructor;
import me.kolganov.grannyshome.dao.AppUserDao;
import me.kolganov.grannyshome.dao.CommentDao;
import me.kolganov.grannyshome.domain.AppUser;
import me.kolganov.grannyshome.domain.Comment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentDao commentDao;
    private final AppUserDao userDao;

    @Override
    public List<Comment> getAll() {
        return commentDao.findAll();
    }

    @Override
    public Comment getById(long id) {
        return commentDao.findById(id).orElseGet(Comment::new);
    }

    @Override
    public void save(Comment comment) {
        Optional<AppUser> user = userDao.findById(comment.getUser().getId());
        user.ifPresent(u -> {
            comment.setUser(u);
            commentDao.save(comment);
        });
    }

    @Override
    public void update(Comment comment) {
        Optional<Comment> oldComment = commentDao.findById(comment.getId());
        oldComment.ifPresent(c -> {
            c.setTitle(comment.getTitle());
            c.setText(comment.getText());
            commentDao.save(c);
        });
    }

    @Override
    public void delete(long id) {
        commentDao.deleteById(id);
    }
}
