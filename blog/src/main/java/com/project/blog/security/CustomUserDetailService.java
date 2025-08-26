package com.project.blog.security;

import com.project.blog.exceptions.ResourceNotFoundException;
import com.project.blog.model.User;
import com.project.blog.repository.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepo userRepo;

    public CustomUserDetailService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // Convert your User entity into Spring Security's UserDetails
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword()) // must already be BCrypt encoded
                .authorities(user.getRoles().stream()
                        .map(role -> "ROLE_" + role.getName()) // Prefix with ROLE_
                        .toArray(String[]::new))
                .build();
    }
}
