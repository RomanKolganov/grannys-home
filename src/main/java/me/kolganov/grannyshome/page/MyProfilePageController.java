package me.kolganov.grannyshome.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyProfilePageController {
    @GetMapping("/my_profile")
    public String getPage() {
        return "myProfile";
    }
}
