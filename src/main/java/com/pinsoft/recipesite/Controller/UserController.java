package com.pinsoft.recipesite.Controller;

import com.pinsoft.recipesite.DTO.*;
import com.pinsoft.recipesite.Entity.User;
import com.pinsoft.recipesite.Service.AuthenticationService;
import com.pinsoft.recipesite.Service.UserService;
import jakarta.annotation.security.PermitAll;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationService authService;

    @PostMapping("/register")
    @PermitAll
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/authenticate")
    @PermitAll
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @GetMapping("/users")
    @PermitAll
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    @PermitAll
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> userOptional = userService.getUserById(id);
        if (userOptional.isPresent()) {
            return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/users/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @PutMapping("/users/status/{id}")
    public ResponseEntity<String> updateUserStatus(
            @PathVariable Long id, @RequestBody StatusRequest statusRequest) {
        Optional<User> userOptional = userService.getUserById(id);
        if (userOptional.isPresent()) {
            String action = statusRequest.getAction();
            switch (action) {
                case "activate":
                    userService.activateUser(id);
                    return ResponseEntity.ok("User activated successfully");
                case "deactivate":
                    userService.deactivateUser(id);
                    return ResponseEntity.ok("User deactivated successfully");
                default:
                    return ResponseEntity.badRequest().body("Invalid action");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @PutMapping("/users/update/{id}")
    public ResponseEntity<String> updateUser(@RequestBody UserUpdateDto userUpdateDto) {
        try {
            userService.updateUser(userUpdateDto);
            return ResponseEntity.ok("User updated successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}