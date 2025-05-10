package net.javaguides.springboot.service.impl;

import net.javaguides.springboot.exception.EmailAlreadyExistException;
import net.javaguides.springboot.mapper.UserMapper;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) throws ResourceNotFoundException {

        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if (optionalUser.isPresent()) {
            throw new EmailAlreadyExistException("Email Already Exist ");
        }
        //convert userDto to User
        User user = UserMapper.mapToUser(userDto);
        //then save the user object into db
        User savedUser = userRepository.save(user);
        //then convert the user to userDto to send the response back to client
        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public UserDto findByUserId(Long userId) throws ResourceNotFoundException {
        User existingUser = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );
        return UserMapper.mapToUserDto(existingUser);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto userDto) throws ResourceNotFoundException {
        User existingUser = userRepository.findById(userDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userDto.getId())
        );
        existingUser.setFirstName(userDto.getFirstName());
        existingUser.setLastName(userDto.getLastName());
        existingUser.setEmail(userDto.getEmail());
        User savedUser = userRepository.save(existingUser);
        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public String deleteUser(Long userId) throws ResourceNotFoundException {
        User existingUser = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );
        userRepository.delete(existingUser);
        return "User is successfully deleted";
    }
}


