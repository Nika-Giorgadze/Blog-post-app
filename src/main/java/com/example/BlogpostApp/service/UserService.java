package com.example.BlogpostApp.service;

import com.example.BlogpostApp.model.User;
import com.example.BlogpostApp.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private IUserRepository iUserRepository;

    public UserService(IUserRepository IUserRepository) {
        this.iUserRepository = IUserRepository;
    }

    @Override
    public List<User> getUsers() {
        return iUserRepository.getUsers();
    }

    @Override
    public Optional<User> getUserById(long id) {
        return iUserRepository.getUserById(id);
    }

    @Override
    public boolean createUser(User user) {
        iUserRepository.save(user);

        return true;
    }
}
