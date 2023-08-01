package com.carlosjr.springreactboot.services;

import com.carlosjr.springreactboot.models.UserDto;
import com.carlosjr.springreactboot.models.UserLogin;
import com.carlosjr.springreactboot.repositories.UserLoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserLoginRepository userLoginRepository;

    @Override
    public boolean validateUser(UserDto userDto) {

        Optional<UserLogin> personOpt = userLoginRepository
                .findUserByUsername(userDto.username());

        if (personOpt.isPresent()){
            return true;
        }
        return false;
    }
}
