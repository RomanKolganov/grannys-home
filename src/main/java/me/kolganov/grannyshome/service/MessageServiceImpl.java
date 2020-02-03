package me.kolganov.grannyshome.service;

import lombok.RequiredArgsConstructor;
import me.kolganov.grannyshome.dao.AppUserDao;
import me.kolganov.grannyshome.dao.DialogDao;
import me.kolganov.grannyshome.dao.MessageDao;
import me.kolganov.grannyshome.domain.AppUser;
import me.kolganov.grannyshome.domain.Dialog;
import me.kolganov.grannyshome.domain.Message;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageDao messageDao;
    private final AppUserDao userDao;
    private final DialogDao dialogDao;

    @Override
    public List<Message> getAllMessages(Dialog dialog) {
        Optional<AppUser> user = userDao.findByLogin(dialog.getUserFrom().getLogin());
        if (user.isPresent()) {
            dialog.setUserFrom(user.get());
            return messageDao.findAllByDialog(dialog);
        }
        return null;
    }

    @Override
    public void save(Message message) {
        Optional<AppUser> userTo = userDao.findById(message.getUserTo().getId());
        Optional<AppUser> userFrom = userDao.findByLogin(message.getUserFrom().getLogin());
        Optional<Dialog> dialog = dialogDao.findById(message.getDialog().getId());

        if (userTo.isPresent() && userFrom.isPresent() && dialog.isPresent()) {
            message.setUserTo(userTo.get());
            message.setUserFrom(userFrom.get());
            message.setDialog(dialog.get());
            messageDao.saveAndFlush(message);
        }
    }
}
