package com.group3.bookandclean.controller;

import com.group3.bookandclean.entity.Cleaner;
import com.group3.bookandclean.entity.Customer;
import com.group3.bookandclean.entity.User;
import com.group3.bookandclean.repository.CleanerRepository;
import com.group3.bookandclean.repository.CustomerRepository;
import com.group3.bookandclean.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
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
    public boolean register(@RequestBody RegisterForm registerForm) {
        User user = User.builder()
                .email(registerForm.getEmail())
                .password(registerForm.getPassword())
                .type(registerForm.getType())
                .build();
        userRepository.save(user);

        if (registerForm.type.equals("Cleaner")){
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
        return true;
    }
}

@Data
class RegisterForm {
    String name;
    String email;
    String password;
    String address;
    String type;
}

