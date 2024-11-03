package com.example.BlogpostApp.service;

import com.example.BlogpostApp.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<User> getUsers();

    Optional<User> getUserById(long id);

    boolean createUser(User user);
}
