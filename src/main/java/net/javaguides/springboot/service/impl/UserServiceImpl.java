package net.javaguides.springboot.service.impl;

import net.javaguides.springboot.mapper.UserMapper;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.exception.UserException;
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
    public UserDto createUser(UserDto userDto) throws UserException {
        //convert userDto to User
        User user = UserMapper.mapToUser(userDto);
        //then save the user object into db
        User savedUser = userRepository.save(user);
        //then convert the user to userDto to send the response back to client
        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public Optional<UserDto> findByUserId(Long userId) throws UserException {
        User existingUser = userRepository.findById(userId).get();
        return Optional.of(UserMapper.mapToUserDto(existingUser));
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto userDto) throws UserException {
        User existingUser = userRepository.findById(userDto.getId()).get();
        existingUser.setFirstName(userDto.getFirstName());
        existingUser.setLastName(userDto.getLastName());
        existingUser.setEmail(userDto.getEmail());
        User savedUser =  userRepository.save(existingUser);
        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public String deleteUser(Long userId) throws UserException {
        User existingUser = userRepository.findById(userId).get();
        userRepository.delete(existingUser);
        return "User is successfully deleted";
    }
}


