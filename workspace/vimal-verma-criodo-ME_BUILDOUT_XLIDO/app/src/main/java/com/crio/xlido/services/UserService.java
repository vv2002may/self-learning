package com.crio.xlido.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import com.crio.xlido.entities.User;
import com.crio.xlido.repositories.IUserRepository;

public class UserService {

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isPresent()){
            throw new RuntimeException("Email already exists: " + email);
        }
        User user = new User(email,password);
        return userRepository.save(user);
    }
}
