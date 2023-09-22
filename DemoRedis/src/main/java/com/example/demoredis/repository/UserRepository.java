package com.example.demoredis.repository;

import com.example.demoredis.entity.User;

import java.util.List;

public interface UserRepository {
    boolean saveUser(User user);

    List<User> fetchAllUser();

    User fetchUserById(Long id);

    boolean deleteUser(Long id);

    User updateUser(Long id, User user);

    List<User> fetchUserByFirstNameOrLastName(String search);

}
