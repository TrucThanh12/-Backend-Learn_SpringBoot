package com.example.demoredis.repository;

import com.example.demoredis.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryImpl implements UserRepository{
    @Autowired
    private RedisTemplate redisTemplate;

    private static final String KEY = "USER";
    @Override
    public boolean saveUser(User user) {
        try{
            redisTemplate.opsForHash().put(KEY,user.getId().toString(), user);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<User> fetchAllUser() {
        List<User> users;
        users = redisTemplate.opsForHash().values(KEY);
        return users;
    }

    @Override
    public User fetchUserById(Long id) {
        User user;
        user = (User) redisTemplate.opsForHash().get(KEY, id.toString());
        return user;
    }

    @Override
    public boolean deleteUser(Long id) {
        try{
            redisTemplate.opsForHash().delete(KEY, id.toString());
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User updateUser(Long id, User user) {
        try{
            User result = fetchUserById(id);
            if(result!=null){
                result.setFirstName(user.getFirstName());
                result.setLastName(user.getLastName());
                result.setEmail(user.getEmail());
                result.setAge(user.getAge());

                redisTemplate.opsForHash().put(KEY, user.getId().toString(), user);
                return result;
            }

        }catch (Exception e){
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public List<User> fetchUserByFirstNameOrLastName(String search) {
        List<User> allUsers = fetchAllUser();
        return allUsers.stream().filter(user -> user.getFirstName().contains(search)|| user.getLastName().contains(search)).collect(Collectors.toList());
    }

}
