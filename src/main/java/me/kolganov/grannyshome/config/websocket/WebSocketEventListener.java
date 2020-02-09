package me.kolganov.grannyshome.config.websocket;

import lombok.RequiredArgsConstructor;
import me.kolganov.grannyshome.rest.dto.MessageDto;
import me.kolganov.grannyshome.rest.dto.UserDto;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
public class WebSocketEventListener {
    private final SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if(username != null) {
            MessageDto messageDto = MessageDto.builder()
                    .userDto(UserDto.builder().name(username).build())
                    .build();
            messagingTemplate.convertAndSend("/topic/public", messageDto);
        }
    }
}
