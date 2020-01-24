package me.kolganov.grannyshome.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfilePageController {
    @GetMapping("/profile")
    public String getPage(@RequestParam("id") long id, Model model) {
        model.addAttribute("id", id);
        return "profile";
    }
}
