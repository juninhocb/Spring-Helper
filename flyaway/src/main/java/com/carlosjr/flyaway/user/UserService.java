package com.carlosjr.flyaway.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository _userRepository;

    public User findById(long id){
        Optional<User> user = _userRepository.findById(id);
        return user.orElseThrow(()-> new InvalidParameterException());
    }
    public void create(User user){
        _userRepository.save(user);
    }

}
