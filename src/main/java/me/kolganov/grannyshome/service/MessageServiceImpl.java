package me.kolganov.grannyshome.service;

import lombok.RequiredArgsConstructor;
import me.kolganov.grannyshome.dao.AppUserDao;
import me.kolganov.grannyshome.dao.MessageDao;
import me.kolganov.grannyshome.domain.AppUser;
import me.kolganov.grannyshome.domain.Dialog;
import me.kolganov.grannyshome.domain.Message;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageDao messageDao;
    private final AppUserDao userDao;

    @Override
    public List<Message> getAllMessages(Dialog dialog) {
        return messageDao.findAllByDialog(dialog);
    }

    @Override
    public void save(Message message) {
        Optional<AppUser> userTo = userDao.findById(message.getUserTo().getId());
        Optional<AppUser> userFrom = userDao.findById(message.getUserFrom().getId());

        if (userTo.isPresent() && userFrom.isPresent()) {
            message.setUserTo(userTo.get());
            message.setUserFrom(userFrom.get());
            messageDao.saveAndFlush(message);
        }
    }
}
