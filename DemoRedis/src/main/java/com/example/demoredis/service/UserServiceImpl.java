package com.example.demoredis.service;

import com.example.demoredis.entity.User;
import com.example.demoredis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean saveUser(User user) {
        return userRepository.saveUser(user);
    }

    @Override
    public List<User> fetchAllUser() {
        return userRepository.fetchAllUser();
    }

    @Override
    public User fetchUserById(Long id) {
        return userRepository.fetchUserById(id);
    }

    @Override
    public boolean deleteUser(Long id) {
        return userRepository.deleteUser(id);
    }

    @Override
    public User updateUser(Long id, User user) {
        return userRepository.updateUser(id, user);
    }

    @Override
    public List<User> fetchUserByListNameOrLastName(String search) {
        return userRepository.fetchUserByFirstNameOrLastName(search);
    }
}
