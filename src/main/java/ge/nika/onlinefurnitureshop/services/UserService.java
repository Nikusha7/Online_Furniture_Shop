package ge.nika.onlinefurnitureshop.services;

import ge.nika.onlinefurnitureshop.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<User> getAllUsers();
    public void registerUser(User user);
    public Optional<User> findByEmail(String email);
}
