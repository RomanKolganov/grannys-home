package me.kolganov.grannyshome.rest;

import lombok.RequiredArgsConstructor;
import me.kolganov.grannyshome.rest.dto.DialogDto;
import me.kolganov.grannyshome.rest.dto.MessageDto;
import me.kolganov.grannyshome.service.MessageService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final MessageService messageService;

    @MessageMapping("/chat.sendMessage/{dialog_id}")
    @SendTo("/topic/public/{dialog_id}")
    public MessageDto sendMessage(@Payload MessageDto messageDto,
                                  @DestinationVariable("dialog_id") long dialogId,
                                  Principal principal) {
        messageDto.getUserDtoFrom().setLogin(principal.getName());
        messageDto.setCreationDate(LocalDateTime.now());
        messageDto.setDialogDto(DialogDto.builder().id(dialogId).build());
        messageService.save(MessageDto.toEntity(messageDto));
        return messageDto;
    }
}
