package net.javaguides.springboot.controller;

import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.exception.UserException;
import net.javaguides.springboot.mapper.UserMapper;
import net.javaguides.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) throws UserException {
        UserDto createdUser = userService.createUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId) throws UserException {
        UserDto userDto = null;
        Optional<UserDto> userOptional = userService.findByUserId(userId);
        if (userOptional.isPresent()) {
            User existingUser = UserMapper.mapToUser(userOptional.get());
            userDto = UserMapper.mapToUserDto(existingUser);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers(){
        List<UserDto> userDtos = userService.getAllUsers();
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId,@RequestBody UserDto userDto) throws UserException {
        userDto.setId(userId);
        UserDto updatedUser =userService.updateUser(userDto);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) throws UserException {
        userService.deleteUser(userId);
        return new ResponseEntity<>("User is deleted", HttpStatus.OK);
    }
}
