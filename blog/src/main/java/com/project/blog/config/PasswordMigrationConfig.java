package com.project.blog.config;

import com.project.blog.model.User;
import com.project.blog.repository.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordMigrationConfig {

    @Bean
    CommandLineRunner migratePasswords(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        return args -> {
            for (User user : userRepo.findAll()) {
                String rawPassword = user.getPassword();

                // Check if already encoded (BCrypt hashes start with $2a$ or $2b$)
                if (!rawPassword.startsWith("$2a$") && !rawPassword.startsWith("$2b$")) {
                    user.setPassword(passwordEncoder.encode(rawPassword));
                    userRepo.save(user);
                    System.out.println("Updated password for user: " + user.getEmail());
                }
            }
        };
    }
}
