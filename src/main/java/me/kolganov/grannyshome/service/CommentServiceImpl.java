package me.kolganov.grannyshome.service;

import lombok.RequiredArgsConstructor;
import me.kolganov.grannyshome.dao.AppUserRepository;
import me.kolganov.grannyshome.dao.CommentRepository;
import me.kolganov.grannyshome.domain.AppUser;
import me.kolganov.grannyshome.domain.Comment;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final AppUserRepository userDao;

    @Override
    public void save(Comment comment) {
        Optional<AppUser> userTo = userDao.findById(comment.getUserTo().getId());
        Optional<AppUser> userFrom = userDao.findByLogin(comment.getUserFrom().getLogin());

        userTo.ifPresent(ut -> {
            comment.setUserTo(ut);
            userFrom.ifPresent(uf -> {
                comment.setCreationDate(new Timestamp(System.currentTimeMillis()));
                comment.setUserFrom(uf);
                commentRepository.save(comment);
            });
        });
    }

    @Override
    public void delete(long id, String login) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isPresent()) {
            if (login.equals(comment.get().getUserFrom().getLogin()))
                commentRepository.deleteById(comment.get().getId());
            else
                throw new AccessDeniedException("The user " + login + "have no permissions to delete comment from user " + comment.get().getUserFrom().getLogin());
        }
    }
}
