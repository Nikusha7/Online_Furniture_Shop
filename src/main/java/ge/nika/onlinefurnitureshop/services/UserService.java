package ge.nika.onlinefurnitureshop.services;

import ge.nika.onlinefurnitureshop.entities.MyUser;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<MyUser> getAllUsers();
    public void registerUser(MyUser myUser);
    public Optional<MyUser> findByEmail(String email);
}
