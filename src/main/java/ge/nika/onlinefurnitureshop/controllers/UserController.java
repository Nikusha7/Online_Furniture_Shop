package ge.nika.onlinefurnitureshop.controllers;

import ge.nika.onlinefurnitureshop.entities.User;
import ge.nika.onlinefurnitureshop.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/home")
    public String homePage() {
        System.out.println("home page was invoked!");
//        model.addAttribute("user", new User());
        return "index";
    }

    @GetMapping("/login")
    public String loginPage(Model model){
        System.out.println("login page was invoked!");
        model.addAttribute("user", new User());
        return "html/login/login";
    }


}
