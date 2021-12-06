package com.group3.bookandclean.services;

import com.group3.bookandclean.entity.Role;
import com.group3.bookandclean.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User>getUsers(); //I regel inte bra eftersom den hämtar hela databasen
}
