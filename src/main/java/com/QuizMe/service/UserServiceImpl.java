package com.QuizMe.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.QuizMe.model.User;
import com.QuizMe.repository.UserRepo;
import com.QuizMe.service.UserService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepo userRepo;

    @Override
    public User createUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public User getUser(Long id) {
        return userRepo.getUser(id);
    }


    @Override
    public void updateUser(User user) {
        userRepo.updateUser(user.getId(), user.getUsername());
    }

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }

}
