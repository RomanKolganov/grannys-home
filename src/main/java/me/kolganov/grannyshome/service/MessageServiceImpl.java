package me.kolganov.grannyshome.service;

import lombok.RequiredArgsConstructor;
import me.kolganov.grannyshome.dao.MessageDao;
import me.kolganov.grannyshome.domain.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageDao messageDao;

    @Override
    public List<Message> getAllMessages(long userIdTo, long userIdFrom) {
        return messageDao.findAllByUserIdOneAndUserIdTwo(userIdTo, userIdFrom);
    }
}
