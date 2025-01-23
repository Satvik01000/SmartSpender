package com.learning.springboot.expensetrackerservice.Controller;

import com.learning.springboot.expensetrackerservice.Models.User;
import com.learning.springboot.expensetrackerservice.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody User user) {
        userService.signUp(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        return userService.logIn(user);
    }

    @DeleteMapping("/{username}")
    public void deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
    }
}