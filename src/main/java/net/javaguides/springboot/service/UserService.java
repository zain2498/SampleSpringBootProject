package net.javaguides.springboot.service;

import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.exception.UserException;

import java.util.List;
import java.util.Optional;

public interface UserService {
     UserDto createUser(UserDto user) throws UserException;
     Optional<UserDto> findByUserId(Long userId) throws  UserException;
     List<UserDto> getAllUsers();
     UserDto updateUser(UserDto user) throws UserException;
     String deleteUser(Long userId) throws UserException;
}
