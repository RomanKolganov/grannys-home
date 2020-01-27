package me.kolganov.grannyshome.service;

import me.kolganov.grannyshome.domain.Dialog;
import me.kolganov.grannyshome.domain.Message;

import java.util.List;

public interface MessageService {
    List<Message> getAllMessages(Dialog dialog);
    void save(Message message);
}
