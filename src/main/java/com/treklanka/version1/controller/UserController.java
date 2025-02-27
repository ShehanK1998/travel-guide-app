package com.treklanka.version1.controller;

import com.treklanka.version1.model.User;
import com.treklanka.version1.repository.UserRepository;
import com.treklanka.version1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    @Autowired
    private UserService userService;
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        Optional<User> user = userService.getUserById(id);
        return  user.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }
    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String passwordHash = loginRequest.getPasswordHash();

        if (username.equals("admin") && passwordHash.equals("admin")) {
            return ResponseEntity.ok("admin");
        }
        Optional<User> optionalUser = userRepository.findByUsername(username);

        // Check if user exists
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getPasswordHash().equals(passwordHash)) {
                return ResponseEntity.ok("user"); // Redirect to Home
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }


    class LoginRequest{
        private String username;
        private String passwordHash;

        public String getUsername(){return username;}
        public void setUsername(String username){this.username = username;}

        public String getPasswordHash() {return passwordHash;}

        public void setPasswordHash(String password) {this.passwordHash = password;}
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User newUserData){
        User updateUser = userService.updateUser(id, newUserData);
        return updateUser != null ? ResponseEntity.ok(updateUser): ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
