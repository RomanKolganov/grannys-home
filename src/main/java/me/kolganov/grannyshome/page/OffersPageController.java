package me.kolganov.grannyshome.page;

import lombok.RequiredArgsConstructor;
import me.kolganov.grannyshome.domain.Animal;
import me.kolganov.grannyshome.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OffersPageController {
    private final UserService userService;

    @GetMapping("/offers")
    public String getPage(Model model, Principal principal) {
        model.addAttribute("animals", userService.getAllCurrentUserAnimals(principal.getName()));
        return "offers";
    }
}
