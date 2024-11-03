package com.example.BlogpostApp.repository;

import com.example.BlogpostApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u")
    List<User> getUsers();

    @Query("SELECT u FROM User u WHERE u.id = :id")
    Optional<User> getUserById(long id);
}
