package com.example.BlogpostApp.controller;

import com.example.BlogpostApp.model.User;
import com.example.BlogpostApp.responses.CreateUserResponse;
import com.example.BlogpostApp.service.IUserService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody User user) {
        try {
            boolean created = userService.createUser(user);
            if (created) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(new CreateUserResponse("User created successfully"));
            } else {
                return ResponseEntity.badRequest()
                        .body(new CreateUserResponse("User creation failed"));
            }
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new CreateUserResponse("Username already exists."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CreateUserResponse("An error occurred while creating the user."));
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id) {
        Optional<User> user = userService.getUserById(id);

        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
