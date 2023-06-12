package com.carlosjr.googlesignin.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    public User getUserById(Long id){
        Optional<User> userOpt = userRepository.findById(id);
        return userOpt.get();
    }
    public void createUser(User user){
        userRepository.save(user);
    }

    public User getUserByUsername(String username) throws Exception {
        User userOpt = userRepository.findByUsername(username);
        if (userOpt != null)
            return userOpt;
        throw new Exception();
    }

}
