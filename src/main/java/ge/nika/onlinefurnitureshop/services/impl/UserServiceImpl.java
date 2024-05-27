package ge.nika.onlinefurnitureshop.services.impl;

import ge.nika.onlinefurnitureshop.entities.MyUser;
import ge.nika.onlinefurnitureshop.repositories.UserRepository;
import ge.nika.onlinefurnitureshop.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<MyUser> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void registerUser(MyUser myUser) {
        userRepository.save(myUser);
    }

    @Override
    public Optional<MyUser> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}