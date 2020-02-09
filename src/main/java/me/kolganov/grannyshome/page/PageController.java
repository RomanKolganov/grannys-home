package me.kolganov.grannyshome.page;

import lombok.RequiredArgsConstructor;
import me.kolganov.grannyshome.domain.AppUser;
import me.kolganov.grannyshome.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class PageController {
    private final UserService userService;

    @GetMapping({"/", "/index"})
    public String getIndexPage(Model model, Principal principal) {
        if (principal == null)
            model.addAttribute("isLogout", true);
        return "index";
    }

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
        model.addAttribute("user", user);
        return "chatRoom";
    }

    @GetMapping("/chats")
    public String getChatsPage() {
        return "chats";
    }

    @GetMapping("/pet")
    public String getPetPage(@RequestParam("id") long id, Model model) {
        model.addAttribute("id", id);
        return "pet";
    }
}
