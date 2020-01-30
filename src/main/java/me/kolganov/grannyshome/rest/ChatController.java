package me.kolganov.grannyshome.rest;

import lombok.RequiredArgsConstructor;
import me.kolganov.grannyshome.rest.dto.MessageDto;
import me.kolganov.grannyshome.rest.dto.UserDto;
import me.kolganov.grannyshome.service.MessageService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final MessageService messageService;

    @MessageMapping("/chat.sendMessage/{dialog_id}")
    @SendTo("/topic/public/{dialog_id}")
    public MessageDto sendMessage(@Payload MessageDto messageDto, @DestinationVariable("dialog_id") String dialogId) {
        System.out.println(dialogId);
        messageService.save(MessageDto.toEntity(messageDto));
        return messageDto;
    }
}
