package me.kolganov.grannyshome.rest;

import lombok.RequiredArgsConstructor;
import me.kolganov.grannyshome.rest.dto.MessageDto;
import me.kolganov.grannyshome.service.MessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MessagesController {
    private final MessageService messageService;

    @GetMapping(value = "/message", produces = "application/json")
    public List<MessageDto> getMessages(@RequestParam("userIdTo") long userIdTo,
                                        @RequestParam("userIdFrom") long usedIdFrom) {
        return messageService.getAllMessages(userIdTo, usedIdFrom).stream()
                .map(MessageDto::toDto).collect(Collectors.toList());
    }
}
