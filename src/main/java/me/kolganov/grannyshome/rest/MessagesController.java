package me.kolganov.grannyshome.rest;

import lombok.RequiredArgsConstructor;
import me.kolganov.grannyshome.domain.AppUser;
import me.kolganov.grannyshome.domain.Dialog;
import me.kolganov.grannyshome.rest.dto.MessageDto;
import me.kolganov.grannyshome.service.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MessagesController {
    private final MessageService messageService;

    @GetMapping(value = "/message/{dialog_id}", produces = "application/json")
    public List<MessageDto> getMessages(@PathVariable("dialog_id") long dialogId,
                                        @RequestParam("userIdTo") long userIdTo,
                                        @RequestParam("userIdFrom") long usedIdFrom) {
        Dialog dialog = Dialog.builder()
                .id(dialogId)
                .userTo(AppUser.builder().id(userIdTo).build())
                .userFrom(AppUser.builder().id(usedIdFrom).build())
                .build();
        return messageService.getAllMessages(dialog).stream()
                .map(MessageDto::toDto).collect(Collectors.toList());
    }

    @PostMapping("/message")
    public void createMessage(@RequestBody MessageDto messageDto) {
        messageService.save(MessageDto.toEntity(messageDto));
    }
}
