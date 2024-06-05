package ge.nika.onlinefurnitureshop.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final MyUserDetailService userDetailService;

    public SecurityConfiguration(MyUserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/home", "/products/**","/single_product/**", "/cart/**").permitAll()
                        .requestMatchers("/perform_register").permitAll()
                        .requestMatchers("/css/**", "/js/**", "/images/**", "/icons/**", "/logos/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/perform_login")
                        .defaultSuccessUrl("/home")
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll);

        return http.build();
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
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}