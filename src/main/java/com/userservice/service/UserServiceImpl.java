package com.userservice.service;

import com.userservice.dto.UserResponse;
import com.userservice.dto.UserToResponseMapper;
import com.userservice.exception.UserNotFoundException;
import com.userservice.model.Role;
import com.userservice.model.User;
import com.userservice.repository.RoleRepository;
import com.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserToResponseMapper userToResponseMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(User user) {
        log.info("saving new user {} to the database", user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void saveRole(Role role) {
        log.info("saving new role {} to the database", role.getName());
        roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String roleName, String userName) {
        log.info("Adding role {} to user {}", roleName, userName);
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new UserNotFoundException("User doesn't exist"));
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);

    }

    @Override
    public List<UserResponse> getAllUsers() {
        log.info("fetching all users");
        return userRepository.findAll()
                .stream()
                .map(userToResponseMapper)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse getUserByName(String name) {
        log.info("fetching user {}", name);
        return userRepository.findByUsername(name)
                .map(userToResponseMapper)
                .orElseThrow(() -> new UserNotFoundException("This user doesn't exist"));
    }
}
