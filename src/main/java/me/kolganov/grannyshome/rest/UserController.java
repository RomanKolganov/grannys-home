package me.kolganov.grannyshome.rest;

import lombok.RequiredArgsConstructor;
import me.kolganov.grannyshome.rest.dto.AnimalDto;
import me.kolganov.grannyshome.rest.dto.DialogDto;
import me.kolganov.grannyshome.rest.dto.UserDto;
import me.kolganov.grannyshome.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/user/dialogs", produces = "application/json")
    public List<DialogDto> getAllUserDialogs(Principal principal) {
        return userService.getAllCurrentUserDialogs(principal.getName()).stream()
                .map(DialogDto::toDto).collect(Collectors.toList());
    }

    @GetMapping(value = "/user/animals", produces = "application/json")
    public List<AnimalDto> getAllUserAnimals(Principal principal) {
        return userService.getAllCurrentUserAnimals(principal.getName()).stream()
                .map(AnimalDto::toDto).collect(Collectors.toList());
    }

    @GetMapping(value = "/user/current", produces = "application/json")
    public UserDto getCurrentUser(Principal principal) {
        return UserDto.toNoCommentsDto(userService.getCurrentUser(principal.getName()));
    }

    @GetMapping(value = "/user/{id}", produces = "application/json")
    public UserDto getUserById(@PathVariable("id") long id) {
        return UserDto.toDto(userService.getById(id));
    }

    @PostMapping("/registration")
    public boolean createUser(@RequestBody UserDto userDto) {
        return userService.save(UserDto.toEntityForRegistration(userDto));
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody UserDto userDto) {
        userService.update(UserDto.toEntity(userDto));
    }
}
