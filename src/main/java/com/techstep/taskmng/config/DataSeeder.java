package com.techstep.taskmng.config;

import com.techstep.taskmng.model.AppUser;
import com.techstep.taskmng.repository.AppUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataSeeder {
    @Bean
    CommandLineRunner seedUsers(AppUserRepository repo, PasswordEncoder encoder) {
        return args -> {
            if (repo.findByUsername("zahraa").isEmpty()) {
                AppUser u = new AppUser();
                u.setUsername("zahraa");
                u.setPassword(encoder.encode("1234"));
                u.setRole("USER");
                repo.save(u);
            }
            if (repo.findByUsername("admin").isEmpty()) {
                AppUser a = new AppUser();
                a.setUsername("admin");
                a.setPassword(encoder.encode("admin"));
                a.setRole("ADMIN");
                repo.save(a);
            }
        };
    }
}