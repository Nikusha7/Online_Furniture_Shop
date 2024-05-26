package ge.nika.onlinefurnitureshop.controllers;

import ge.nika.onlinefurnitureshop.dtos.UserDTO;
import ge.nika.onlinefurnitureshop.entities.User;
import ge.nika.onlinefurnitureshop.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/home")
    public String homePage() {
        System.out.println("home page was invoked!");
//        model.addAttribute("user", new User());
        return "index";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        System.out.println("login page was invoked!");
        model.addAttribute("userDTO", new UserDTO());
        return "html/login/login";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("userDTO") UserDTO userDTO, Model model) {
        System.out.println("UserDTO to register: "+userDTO.toString());
        String role = "USER";
        User user = User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .role(role)
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .build();
        System.out.println("User entity ready to register: "+user.toString());
        model.addAttribute("user", user);

        userService.registerUser(user);

        return "redirect:/home";
    }

    @PostMapping("/login-user")
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        System.out.println("User tries to log in with email: "+email+", and pass: "+password);

        return "redirect:/home";
    }
}
