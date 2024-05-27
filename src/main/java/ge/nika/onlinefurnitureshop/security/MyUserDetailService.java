package ge.nika.onlinefurnitureshop.security;

import ge.nika.onlinefurnitureshop.entities.MyUser;
import ge.nika.onlinefurnitureshop.entities.Role;
import ge.nika.onlinefurnitureshop.services.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MyUserDetailService implements UserDetailsService {
    private final UserService userService;

    public MyUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<MyUser> user = userService.findByEmail(email);
        if (user.isPresent()) {
            MyUser userObj = user.get();
            return new User(userObj.getEmail(), userObj.getPassword(), mapRolesToAuthorities(userObj.getRoles()));
        } else {
            throw new UsernameNotFoundException("Invalid email or password" + email);
        }
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_"+role.getName())).collect(Collectors.toList());
    }

}
