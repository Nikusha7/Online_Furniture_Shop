package ge.nika.onlinefurnitureshop.services;

import ge.nika.onlinefurnitureshop.dtos.UserDTO;
import ge.nika.onlinefurnitureshop.entities.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<User> getAllUsers(Model model);
    public void loginUser(String error, String logout, Model model);
    public String registerUser(UserDTO userDTO, Model model, PasswordEncoder passwordEncoder);
    public Optional<User> findByEmail(String email);
    public void deleteUserById(Integer id);
}
