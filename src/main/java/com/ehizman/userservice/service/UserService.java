package com.ehizman.userservice.service;

import com.ehizman.userservice.shared.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDetails);
}
