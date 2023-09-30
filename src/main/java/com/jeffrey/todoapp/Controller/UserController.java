package com.jeffrey.todoapp.Controller;

import com.jeffrey.todoapp.Dao.UserService;
import com.jeffrey.todoapp.Model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }



    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

//    @PostMapping("/api/v1/register")
//    public User registerUser(@RequestBody User user) {
//        return userService.addUser(user);
//    }





    @PutMapping("/{id}")
    public boolean updateUser(@PathVariable int id, @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser);
    }

    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }

    @PostMapping("/api/v1/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        // Check if the username already exists
        Optional<User> existingUser = userService.getUserByUsername(user.getUsername());

        if (existingUser.isPresent()) {
            // Username already exists, return a conflict response
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Username already exists. Please choose a different username.");
        }

        // Attempt to register the user
        User registeredUser = userService.addUser(user);

        if (registeredUser != null) {
            // User registered successfully
            return ResponseEntity.ok("User registered successfully.");
        } else {
            // Failed to register the user, return a bad request response
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Failed to register the user.");
        }
    }


}
