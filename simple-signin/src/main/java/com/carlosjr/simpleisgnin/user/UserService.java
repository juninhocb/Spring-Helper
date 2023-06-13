package com.carlosjr.simpleisgnin.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserRepository userRepository;
    public User getUserById(Long id){
        Optional<User> userOpt = userRepository.findById(id);
        return userOpt.get();
    }
    public void createUser(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public User getUserByUsername(String username) throws Exception {
        User userOpt = userRepository.findByUsername(username);
        if (userOpt != null)
            return userOpt;
        throw new Exception();
    }

}
