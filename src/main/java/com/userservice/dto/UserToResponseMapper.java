package com.userservice.dto;

import com.userservice.model.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserToResponseMapper implements Function<User,UserResponse> {
    @Override
    public UserResponse apply(User user) {
        return new UserResponse(
                user.getName(),
                user.getUsername(),
                user.getRoles()
        );
    }
}
