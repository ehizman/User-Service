package com.ehizman.userservice.service;

import com.ehizman.userservice.data.User;
import com.ehizman.userservice.data.UserRepository;
import com.ehizman.userservice.shared.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto createUser(UserDto userDetails) {
        userDetails.setUserId(UUID.randomUUID().toString());
        User user = modelMapper.map(userDetails, User.class);
        user.setEncryptedPassword("encrypted password");
        return modelMapper.map(userRepository.save(user), UserDto.class);
    }
}
