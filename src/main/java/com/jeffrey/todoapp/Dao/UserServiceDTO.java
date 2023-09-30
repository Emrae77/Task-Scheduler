package com.jeffrey.todoapp.Dao;

import com.jeffrey.todoapp.Model.User;
import com.jeffrey.todoapp.Model.UserDTO;
import com.jeffrey.todoapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceDTO implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Not Found:" + username));
        return user.map(UserDTO::new).get();
    }
}


//        // Try to find a user by their username in the database
//        Optional<User> userOptional = userRepository.findByUserName(username);
//
//// If a user is found, create a MyUserDetails object from the user's details
//// If not found, throw an exception indicating that the user was not found
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            return new MyUserDetails(user);
//        } else {
//            throw new UsernameNotFoundException("User not found: " + username);
//        }
//
//    }


