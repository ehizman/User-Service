package com.ehizman.userservice.controller;

import com.ehizman.userservice.service.UserService;
import com.ehizman.userservice.shared.UserDto;
import com.ehizman.userservice.ui.model.CreateUserRequestModel;
import com.ehizman.userservice.ui.model.UserResponseModel;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private Environment environment;

    @GetMapping(value = "/status-check")
    public String  statusCheck(){
        return "Working";
    }

    @PostMapping("/")
    public ResponseEntity<UserResponseModel> createUser(@RequestBody @Valid CreateUserRequestModel userDetails){
        log.info("Environment --> {}", environment.getProperty("gateway.ip"));
        UserDto userDto= userService.createUser( modelMapper.map(userDetails, UserDto.class));
        log.info("UserDto --> {}", userDto);
        UserResponseModel userResponse = modelMapper.map(userDto, UserResponseModel.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }
}