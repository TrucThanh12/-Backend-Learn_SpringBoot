package com.example.demoredis.service;

import com.example.demoredis.entity.User;

import java.util.List;

public interface UserService {
    boolean saveUser(User user);

    List<User> fetchAllUser();

    User fetchUserById(Long id);

    boolean deleteUser(Long id);

    User updateUser(Long id, User user);

    List<User> fetchUserByListNameOrLastName(String search);
}
