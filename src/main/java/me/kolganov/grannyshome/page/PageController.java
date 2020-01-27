package me.kolganov.grannyshome.page;

import lombok.RequiredArgsConstructor;
import me.kolganov.grannyshome.domain.AppUser;
import me.kolganov.grannyshome.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class PageController {
    private final UserService userService;

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/acceptedOffers")
    public String getAcceptedOffersPage() {
        return "acceptedOffers";
    }

    @GetMapping("/my_profile")
    public String getMyProfilePage() {
        return "myProfile";
    }

    @GetMapping("/offers")
    public String getOffersPage(Model model, Principal principal) {
        model.addAttribute("animals", userService.getAllCurrentUserAnimals(principal.getName()));
        model.addAttribute("user", principal.getName());
        return "offers";
    }

    @GetMapping("/profile")
    public String getProfilePage(@RequestParam("id") long id, Model model) {
        model.addAttribute("id", id);
        return "profile";
    }

    @GetMapping("/chat")
    public String getChatRoomPage(@RequestParam("dialog_id") long dialogId,
                                  @RequestParam("id") long id, Model model, Principal principal) {
        AppUser user = userService.getCurrentUser(principal.getName());
        model.addAttribute("dialogId", dialogId);
        model.addAttribute("userIdTo", id);
        model.addAttribute("userIdFrom", user.getId());
        return "chatRoom";
    }
}
