package com.group3.bookandclean.services;

import com.group3.bookandclean.entity.User;
import com.group3.bookandclean.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void register(User user) {
        userRepository.save(user);
    }
}
