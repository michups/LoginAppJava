package com.michups.service;

import com.michups.model.User;
import com.michups.repository.UserRepository;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        var users = (List<User>) userRepository.findAll();
        return users;
    }

    @Override
    public User findByUsername(String username) {
        Optional<User> users = userRepository.findByUsername(username)
                .stream()
                .findFirst();
        if (users.isPresent()) {
            return users.get();
        }

        return null;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

}
