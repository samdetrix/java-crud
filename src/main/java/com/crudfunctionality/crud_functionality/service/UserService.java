package com.crudfunctionality.crud_functionality.service;

import com.crudfunctionality.crud_functionality.entity.User;

import java.util.List;

public interface UserService {
    void addUser(User user);

    List<User> getAllUsers();

    User getUserById(Integer id);
}
