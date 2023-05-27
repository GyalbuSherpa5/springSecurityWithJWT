package com.userservice.service;

import com.userservice.dto.UserResponse;
import com.userservice.model.Role;
import com.userservice.model.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);

    void saveRole(Role role);

    void addRoleToUser(String roleName, String userName);

    List<UserResponse> getAllUsers();

    UserResponse getUserByName(String name);

}
