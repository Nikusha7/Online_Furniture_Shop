package ge.nika.onlinefurnitureshop.services.impl;

import ge.nika.onlinefurnitureshop.dtos.UserDTO;
import ge.nika.onlinefurnitureshop.entities.User;
import ge.nika.onlinefurnitureshop.entities.Role;
import ge.nika.onlinefurnitureshop.repositories.RoleRepository;
import ge.nika.onlinefurnitureshop.repositories.UserRepository;
import ge.nika.onlinefurnitureshop.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<User> getAllUsers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return users;
    }

    @Override
    public void loginUser(String error, String logout, Model model) {
        model.addAttribute("userDTO", new UserDTO());
        if (error != null) {
            model.addAttribute("errorLogin", "Invalid username or password!");
        }
        if (logout != null) {
            model.addAttribute("logoutMessage", "You have been logged out successfully!");
        }
    }

    @Override
    public String registerUser(UserDTO userDTO, Model model, PasswordEncoder passwordEncoder) {

        Optional<User> checkUser = userRepository.findByEmail(userDTO.getEmail());

        if (checkUser.isPresent()) {
            model.addAttribute("errorRegistration", "Email is already in use!");
            return "html/login/login";
        } else {
            Role roles = roleRepository.findByName("USER").get();

            User user = User.builder()
                    .firstName(userDTO.getFirstName())
                    .lastName(userDTO.getLastName())
                    .email(userDTO.getEmail())
                    .roles(Collections.singletonList(roles))
                    .password(passwordEncoder.encode(userDTO.getPassword()))
                    .build();
            userRepository.save(user);

        }

        return "redirect:/home";
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    @Transactional
    public void deleteUserById(Integer id) {
        // Delete user roles associated with the user
        userRepository.deleteUserRolesByUserId(id);
        // Then delete the user
        userRepository.deleteById(id);
    }
}