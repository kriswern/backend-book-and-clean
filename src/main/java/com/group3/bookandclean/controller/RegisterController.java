package com.group3.bookandclean.controller;

import com.group3.bookandclean.entity.Cleaner;
import com.group3.bookandclean.entity.Customer;
import com.group3.bookandclean.entity.User;
import com.group3.bookandclean.repository.CleanerRepository;
import com.group3.bookandclean.repository.CustomerRepository;
import com.group3.bookandclean.repository.UserRepository;
import com.group3.bookandclean.request.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class RegisterController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CleanerRepository cleanerRepository;

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerForm) {
        User user = userRepository.findByEmail(registerForm.getEmail());
        if (user != null) {
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        }
        user = User.builder()
                .email(registerForm.getEmail())
                .password(registerForm.getPassword())
                .type(registerForm.getType())
                .build();
        userRepository.save(user);

        if (registerForm.getType().equals("Cleaner")){
            Cleaner cleaner = Cleaner.builder()
                    .name(registerForm.getName())
                    .address(registerForm.getAddress())
                    .user(user)
                    .build();
            cleanerRepository.save(cleaner);

        }else {
            Customer customer = Customer.builder()
                    .name(registerForm.getName())
                    .address(registerForm.getAddress())
                    .user(user)
                    .build();
            customerRepository.save(customer);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}



