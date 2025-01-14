package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    //get only id and full name (all users)
    @GetMapping("/simple")
    public List<UserSimpleDto> getAllUsersSimple() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toSimpleDto)
                .toList();
    }

    //get user by id
    @GetMapping("/{Id}")
    public UserDto getUser(@PathVariable Long Id) {
        return userService.getUser(Id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException(Id));
    }

    //get user by ID/fullName/Date of birth/ e-mail
    @GetMapping("/search")
    public List<UserDto> searchUser(
            @RequestParam(required = false) long id,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) LocalDate dateOfBirth,
            @RequestParam(required = false) String email
            ){
        return userService.GetUsersByParameter(id, firstName, lastName,  dateOfBirth, email)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }



    @GetMapping("/email")
    public List<UserEmailDto> getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email)
                .stream()
                .map(userMapper::toEmailDto)
                .toList();
    }

    @GetMapping("/email-part")
    public List<UserEmailDto> getUserByEmailPartIgnoreCase(@RequestParam String email) {
        return userService.findByEmailContainingIgnoreCase(email)
                .stream()
                .map(userMapper::toEmailDto)
                .toList();
    }

    @GetMapping("/older/{time}")
    public List<UserDto> getUsersOlderThan(@PathVariable LocalDate time) {
        return userService.getUsersOlderThan(time)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);

        User createdUser = userService.createUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.toDto(createdUser));
    }

    @PutMapping("/{user}")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        User updatedUser = userService.updateUser(userMapper.toEntity(userDto));
        //optionally we can use getUserById(userDto.id) here but it's unnecessary load for db
        return userMapper.toDto(updatedUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}