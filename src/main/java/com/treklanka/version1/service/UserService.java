package com.treklanka.version1.service;

import com.treklanka.version1.model.User;
import com.treklanka.version1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    public User addUser(User user){
        return userRepository.save(user);
    }

    public User updateUser(Long id, User newUserData){
        return userRepository.findById(id).map(user -> {
            user.setUsername(newUserData.getUsername());
            user.setEmail(newUserData.getEmail());
            user.setPasswordHash(newUserData.getPasswordHash());
            return userRepository.save(user);
        }).orElse(null);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
