package me.kolganov.grannyshome.service;

import lombok.RequiredArgsConstructor;
import me.kolganov.grannyshome.dao.AppUserRepository;
import me.kolganov.grannyshome.dao.DialogRepository;
import me.kolganov.grannyshome.dao.MessageRepository;
import me.kolganov.grannyshome.domain.AppUser;
import me.kolganov.grannyshome.domain.Dialog;
import me.kolganov.grannyshome.domain.Message;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final AppUserRepository userDao;
    private final DialogRepository dialogRepository;

    @Override
    public List<Message> getAllMessages(Dialog dialog) {
        Optional<AppUser> user = userDao.findByLogin(dialog.getUser().getLogin());
        if (user.isPresent()) {
            dialog.setUser(user.get());
            return messageRepository.findAllByDialog(dialog);
        }
        return null;
    }

    @Override
    public void save(Message message) {
        Optional<AppUser> userFrom = userDao.findByLogin(message.getUser().getLogin());
        Optional<Dialog> dialog = dialogRepository.findById(message.getDialog().getId());

        if (userFrom.isPresent() && dialog.isPresent()) {
            message.setUser(userFrom.get());
            message.setDialog(dialog.get());
            messageRepository.saveAndFlush(message);
        }
    }
}
