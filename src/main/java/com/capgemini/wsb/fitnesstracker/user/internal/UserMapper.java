package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.internal.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    //main mapper
    public UserDto toDto(User user) {
        return new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail());
    }

    public User toEntity(UserDto userDto) {
        return new User(
                userDto.firstName(),
                userDto.lastName(),
                userDto.birthdate(),
                userDto.email());
    }

    //simple mapper
    public UserSimpleDto toSimpleDto(User user) {
        return new UserSimpleDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName());
    }

    public User toEntity(UserSimpleDto userSimpleDto) {
        return new User(
                userSimpleDto.firstName(),
                userSimpleDto.lastName(),
                //birthday and email are requierd field so mapping them to null
                null,
                null);
    }

    //email mapper
    public UserEmailDto toEmailDto(User user) {
        return new UserEmailDto(
                user.getId(),
                user.getEmail());
    }

    public User toEmailEntity(UserEmailDto dto) {
        return new User(
                //firstName, lastName and birthdate are requierd field so mapping them to null
                null,
                null,
                null,
                dto.email());
    }

    User toUpdateEntity(UserDto userDto, User user) {
        if(userDto.firstName() != null) {
            user.setFirstName(userDto.firstName());
        }
        if(userDto.lastName() != null) {
            user.setLastName(userDto.lastName());
        }
        if(userDto.birthdate() != null) {
            user.setBirthdate(userDto.birthdate());
        }
        if(userDto.email() != null) {
            user.setEmail(userDto.email());
        }

        return user;
    }

}