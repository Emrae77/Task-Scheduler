package com.jeffrey.todoapp.Dao;

import com.jeffrey.todoapp.Model.User;
import com.jeffrey.todoapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    @Autowired
    UserRepository uRep;

    public List<User> getAllUsers(){
        return uRep.findAll();
    }
    public Optional<User> getUserById(int id){
        return uRep.findById(id);
    }
    public Optional<User> getUserByUsername(String username) {
        return uRep.findByUsername(username);
    }

        public User addUser(User user) {
            // Check if the username already exists in the database
            Optional<User> existingUser = getUserByUsername(user.getUsername());
            if (existingUser.isPresent()) {
                System.out.println("Username already exists: " + user.getUsername());                return null;
            }

            try {
                // Username is unique, save the user
                return uRep.save(user);
            } catch (Exception e) {
                e.printStackTrace(); // Add proper logging here
                return null;
            }
        }

    public boolean updateUser(int id, User updatedUser) {
        // First, check if the user with the provided ID exists in the repository
        Optional<User> existingUser = uRep.findById(id);

        if (existingUser.isPresent()) {
            // User with the given ID exists
            User userToUpdate = existingUser.get();

            // Update user properties with the data from updatedUser
            userToUpdate.setUsername(updatedUser.getUsername());
            userToUpdate.setPassword(updatedUser.getPassword());
            userToUpdate.setEmail(updatedUser.getEmail());

            // Save the updated user
            uRep.save(userToUpdate);

            return true; // Indicate a successful update
        } else {
            // User with the given ID does not exist
            return false; // Indicate that the update failed
        }
    }

    public boolean deleteUser(int id){
        Optional<User> user =  uRep.findById(id);
        if(user.isPresent()){
            User userToDelete = user.get();
            uRep.delete(userToDelete);
            System.out.println("User with ID " + id + " deleted.");
            return true; // Indicate a successful delete
        }
        else {
            System.out.println("User with ID " + id + " dos not exist.");
            return false; // Indicate that the delete operation failed
        }


    }


}



