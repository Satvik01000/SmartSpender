package com.learning.springboot.expensetrackerservice.Service.User;

import com.learning.springboot.expensetrackerservice.Models.User;

public interface UserService {

    void addUser(User user);

    public void deleteUser(String userName);
}
