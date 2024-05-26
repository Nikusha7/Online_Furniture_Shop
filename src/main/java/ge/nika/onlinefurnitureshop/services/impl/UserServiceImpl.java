package ge.nika.onlinefurnitureshop.services.impl;

import ge.nika.onlinefurnitureshop.entities.User;
import ge.nika.onlinefurnitureshop.repositories.UserRepository;
import ge.nika.onlinefurnitureshop.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void registerUser(User user) {
        userRepository.save(user);
    }

}
