package com.finance.dashboard.service;


import com.finance.dashboard.constants.Status;
import com.finance.dashboard.entity.User;
import com.finance.dashboard.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        if (user.getStatus() != null) {
            user.setStatus(Status.ACTIVE);
        }
        return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

}
