package com.QuizMe.service;

import com.QuizMe.model.User;

import java.util.List;

public interface UserService {
    public User createUser(User user);
    public User getUser(Long id);
    public List<User> getUsers();
    public void updateUser(User user);
}
