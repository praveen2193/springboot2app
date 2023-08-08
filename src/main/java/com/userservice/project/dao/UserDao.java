package com.userservice.project.dao;

import com.userservice.project.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    public User createUser(User user);

    public List<User> getAllUser();

    public User getUser(String userId);

    public void deleteById(String userId);

    public void deleteAll();

    public User updateUser(User user,String userId);


}
