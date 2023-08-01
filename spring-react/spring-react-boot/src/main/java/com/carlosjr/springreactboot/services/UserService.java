package com.carlosjr.springreactboot.services;

import com.carlosjr.springreactboot.models.UserDto;

public interface UserService {

    boolean validateUser(UserDto userDto);

}
