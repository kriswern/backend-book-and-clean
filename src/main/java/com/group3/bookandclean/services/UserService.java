package com.group3.bookandclean.services;

import com.group3.bookandclean.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    String getType(String username);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User>getUsers();
}
