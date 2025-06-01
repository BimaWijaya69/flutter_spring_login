package com.example.auth.service;

import com.example.auth.model.User;
import com.example.auth.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean register(String nama, String email, String password) {
        Optional<User> exist = userRepository.findByEmail(email);
        if (exist.isPresent()) {
            return false;
        }

        User user = new User(nama, email, password);
        userRepository.save(user);
        return true;
    }

    public boolean login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return user.get().getPassword().equals(password);
        }
        return false;
    }
}
