package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.stereotype.Component;

@Component
class UserEmailMapper {

        UserEmailDto toDto(User user) {
            return new UserEmailDto(user.getId(), user.getEmail());
        }

        User toEntity(UserEmailDto dto) {
            return new User(null,null,null, dto.email());
        }
}
