package net.javaguides.springboot.service;

import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.exception.ResourceNotFoundException;

import java.util.List;

public interface UserService {
     UserDto createUser(UserDto user) throws ResourceNotFoundException;
     UserDto findByUserId(Long userId) throws ResourceNotFoundException;
     List<UserDto> getAllUsers();
     UserDto updateUser(UserDto user) throws ResourceNotFoundException;
     String deleteUser(Long userId) throws ResourceNotFoundException;
}
