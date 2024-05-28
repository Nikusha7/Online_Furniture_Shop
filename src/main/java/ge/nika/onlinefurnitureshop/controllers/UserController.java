package ge.nika.onlinefurnitureshop.controllers;

import ge.nika.onlinefurnitureshop.dtos.MyUserDTO;
import ge.nika.onlinefurnitureshop.entities.MyUser;
import ge.nika.onlinefurnitureshop.entities.Role;
import ge.nika.onlinefurnitureshop.repositories.RoleRepository;
import ge.nika.onlinefurnitureshop.services.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Optional;

@Controller
@RequestMapping
public class UserController {
    private AuthenticationManager authenticationManager;
    private final UserService userService;
    private RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(AuthenticationManager authenticationManager, UserService userService,
                          RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.roleRepository = roleRepository;
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
            return "redirect:/home";
        } else {
            Role roles = roleRepository.findByName("USER").get();

            MyUser myUser = MyUser.builder()
                    .firstName(myUserDTO.getFirstName())
                    .lastName(myUserDTO.getLastName())
                    .email(myUserDTO.getEmail())
                    .roles(Collections.singletonList(roles))
                    .password(passwordEncoder.encode(myUserDTO.getPassword()))
                    .build();

            System.out.println("User entity ready to register: " + myUser.toString());

            userService.registerUser(myUser);
        }
        return "redirect:/home";
    }


}