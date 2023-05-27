package com.userservice.controller;

import com.userservice.dto.RoleToUserForm;
import com.userservice.dto.UserResponse;
import com.userservice.model.Role;
import com.userservice.model.User;
import com.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;

    @PostMapping("/users")
    public String storeUser(@RequestBody User user) {
        userService.saveUser(user);
        return "user saved successfully";
    }

    @PostMapping("/roles")
    public String role(@RequestBody Role role) {

        userService.saveRole(role);
        return "role saved successfully";
    }

    @PostMapping("/addToUser")
    public String addRoleToUser(@RequestBody RoleToUserForm form) {
        userService.addRoleToUser(form.getRoleName(), form.getUserName());
        return "role added to the user";
    }

    @GetMapping("/users")
    public List<UserResponse> getAllUser() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{name}")
    public UserResponse getUserByName(@PathVariable String name) {
        return userService.getUserByName(name);
    }
}