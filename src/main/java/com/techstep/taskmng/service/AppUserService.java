package com.techstep.taskmng.service;

import com.techstep.taskmng.dto.RegisterRequest;
import com.techstep.taskmng.model.AppUser;
import com.techstep.taskmng.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean usernameExists(String username) {
        return appUserRepository.findByUsername(username).isPresent();
    }

    public List<AppUser> findAll(){
        return appUserRepository.findAll();
    }

    public void register(RegisterRequest request) {
        AppUser user = new AppUser();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));  // store the hash, never plain text
        user.setRole("USER");                                             // role is decided by the server, not the form
        appUserRepository.save(user);
    }
}
