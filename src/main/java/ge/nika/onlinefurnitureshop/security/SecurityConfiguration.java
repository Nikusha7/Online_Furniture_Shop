package ge.nika.onlinefurnitureshop.security;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final MyUserDetailService userDetailService;

    public SecurityConfiguration(MyUserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/home","/perform_register").permitAll() //allow all users to access /home page
                        .requestMatchers("/css/**", "/js/**", "/images/**", "/icons/**", "/logos/**").permitAll() // Allow access to static resources
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Allow only users with ADMIN role to access /admin/**
//                        .requestMatchers("/user/**").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/home")
                        .permitAll())
                .logout(LogoutConfigurer::permitAll);

        return httpSecurity.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails normalUser =
//                User.builder()
//                        .username("user")
//                        .password("$2a$12$19cf1uLHxaoB5Moi6krrGuSZAxnwX8LIo/kvBuKhgth9uHkRUcH2y") //4321 encrypted
//                        .roles("USER")
//                        .build();
//        UserDetails adminUser =
//                User.builder()
//                        .username("admin")
//                        .password("$2a$12$kzDeA7Isb8smmfPHWACP0OagSGfypJzc42hQ5mOAyvkO1CwFCL4lq") // 1234
//                        .roles("ADMIN", "USER") //admin should access everything so it will have both roles
//                        .build();
//
//        return new InMemoryUserDetailsManager(normalUser, adminUser);
//    }

    @Bean
    public UserDetailsService userDetailsService(){
        return userDetailService;
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}