package com.jeffrey.todoapp.Repository;

import com.jeffrey.todoapp.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<com.jeffrey.todoapp.Model.User, Integer> {
    Optional<User> findByUsername(String username);
}
