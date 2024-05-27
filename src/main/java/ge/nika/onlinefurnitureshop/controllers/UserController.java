package ge.nika.onlinefurnitureshop.controllers;

import ge.nika.onlinefurnitureshop.dtos.MyUserDTO;
import ge.nika.onlinefurnitureshop.entities.MyUser;
import ge.nika.onlinefurnitureshop.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
//        System.out.println("User tries to log in:\nemail:" + email + "\npassword:" + password);
        model.addAttribute("userDTO", new MyUserDTO());

        return "html/login/login";
    }

    @PostMapping("/perform_register")
    public String registerUser(@ModelAttribute("userDTO") MyUserDTO myUserDTO, Model model) {
        System.out.println("UserDTO to register: " + myUserDTO.toString());
        Optional<MyUser> checkUser = userService.findByEmail(myUserDTO.getEmail());
        if (checkUser.isPresent()) {
            System.out.println("USER ALREADY EXISTS, YOU CAN'T USE THAT EMAIL");
        } else {
            String role = "USER";
            MyUser myUser = MyUser.builder()
                    .firstName(myUserDTO.getFirstName())
                    .lastName(myUserDTO.getLastName())
                    .email(myUserDTO.getEmail())
                    .role(role)
                    .password(passwordEncoder.encode(myUserDTO.getPassword()))
                    .build();
            System.out.println("User entity ready to register: " + myUser.toString());
            model.addAttribute("user", myUser);

            userService.registerUser(myUser);
        }
        return "redirect:/home";
    }

//    @PostMapping("/perform_login")
//    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
//        System.out.println("User tries to log in:\nemail:" + email + "\npassword:" + password);
//        Optional<MyUser> user = userService.findByEmail(email);
//        if (user.isEmpty()) {
//            System.out.println("USER DOES NOT EXISTS");
//        } else {
//            System.out.println("User: " + user);
//        }
//        return "redirect:/home";
//    }

}
