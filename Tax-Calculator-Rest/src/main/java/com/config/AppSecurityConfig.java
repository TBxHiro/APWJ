package com.config;

import com.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig {

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    /*@Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails userDetails = User.builder()
                .username("kawsur")
                .password("$2y$10$LqPYTOcJhvhV7oSWXG5L2OkVMdQ4F0IbBxCP1Eyh2eqD7n8qgRhU6")
                .authorities("ROLE_USER", "ROLE_ADMIN")
                .build();
        return new InMemoryUserDetailsManager(userDetails);
    }*/

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().disable()
                .httpBasic()
                .and()
                // configure the HttpSecurity to only be invoked when matching the provided ant pattern
                //.antMatcher("/**")
                .authorizeRequests()
                // login api is... any authenticated user
                .antMatchers("/register/**").permitAll()//.access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                // tax api restricted to... USER
                .antMatchers("/tax/**").access("hasRole('ROLE_USER')")
                // Authority api restricted to... ADMIN
                .antMatchers("/api/**").access("hasRole('ROLE_ADMIN')")
                // Admin api restricted to... ADMIN

                .antMatchers("/public/**").access("hasRole('ROLE_USER')")
                .anyRequest().authenticated();
               //.and()
               //.formLogin()
                //.loginPage("/login/success")
                //.defaultSuccessUrl("/public/login-success");
                // Login api restricted to... ADMIN or USER
                //.antMatchers("/login/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                //.and()
                //.rememberMe();

        //.formLogin()
        //.loginPage("/login")
        //.and()
        //.logout();
        return http.build();
    }
}
