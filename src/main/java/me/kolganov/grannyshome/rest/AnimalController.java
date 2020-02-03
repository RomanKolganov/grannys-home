package me.kolganov.grannyshome.rest;

import lombok.RequiredArgsConstructor;
import me.kolganov.grannyshome.rest.dto.AnimalDto;
import me.kolganov.grannyshome.rest.dto.UserDto;
import me.kolganov.grannyshome.service.AnimalService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AnimalController {
    private final AnimalService animalService;

    @GetMapping("/animal")
    public List<AnimalDto> getAllAnimals() {
        return animalService.getAll().stream().map(AnimalDto::toDto).collect(Collectors.toList());
    }

    @GetMapping("/animal/{id}")
    public AnimalDto getAnimal(@PathVariable("id") long id) {
        return AnimalDto.toDto(animalService.getOne(id));
    }

    @PostMapping("/animal")
    public void createAnimal(@RequestBody AnimalDto animalDto, Principal principal) {
        animalDto.setUserDto(UserDto.builder().login(principal.getName()).build());
        animalService.save(AnimalDto.toEntityWithUser(animalDto));
    }

    @PutMapping("/animal")
    public void updateAnimal(@RequestBody AnimalDto animalDto) {
        animalService.update(AnimalDto.toEntity(animalDto));
    }

    @DeleteMapping("/animal/{id}")
    public void deleteAnimal(@PathVariable("id") long id) {
        animalService.delete(id);
    }
}
