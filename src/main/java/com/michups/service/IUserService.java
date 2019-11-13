package com.michups.service;

import com.michups.model.User;

import java.util.List;

public interface IUserService {

    List<User> findAll();
    User findByUsername(String username);
    User createUser(User user);
}
