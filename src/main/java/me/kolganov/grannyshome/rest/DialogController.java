package me.kolganov.grannyshome.rest;

import lombok.RequiredArgsConstructor;
import me.kolganov.grannyshome.domain.Dialog;
import me.kolganov.grannyshome.rest.dto.DialogDto;
import me.kolganov.grannyshome.rest.dto.UserDto;
import me.kolganov.grannyshome.service.DialogService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class DialogController {
    private final DialogService dialogService;

    @PostMapping("/dialog")
    public DialogDto createDialog(@RequestBody DialogDto dialogDto, Principal principal) {
        dialogDto.setUserDtoFrom(UserDto.builder().login(principal.getName()).build());
        Dialog dialog = dialogService.create(DialogDto.toEntity(dialogDto));
        return DialogDto.toDto(dialog);
    }
}
