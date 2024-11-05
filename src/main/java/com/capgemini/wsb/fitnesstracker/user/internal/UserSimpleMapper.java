package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class UserSimpleMapper {

    public UserSimpleDto toSimpleDto(User user) {
        return new UserSimpleDto(user.getId(),
                                 user.getFirstName(),
                                 user.getLastName());
    }

    public User toEntity(UserSimpleDto userSimpleDto) {
        return new User(userSimpleDto.firstName(),
                        userSimpleDto.lastName(),
                null,
                   null);
    }
}
