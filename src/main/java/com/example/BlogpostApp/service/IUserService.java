package com.example.BlogpostApp.service;

import com.example.BlogpostApp.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    Optional<User> findByUsername(String username);

    User createUser(User user);

    User updateUser(Long id, User user);

    void deleteUser(Long id);
}
