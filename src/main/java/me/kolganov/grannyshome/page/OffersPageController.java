package me.kolganov.grannyshome.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OffersPageController {
    @GetMapping("/offers")
    public String getPage() {
        return "offers";
    }
}
