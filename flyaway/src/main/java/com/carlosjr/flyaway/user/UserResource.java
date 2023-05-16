package com.carlosjr.flyaway.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/user")
public class UserResource  {

    @Autowired
    UserService _userService;

    @GetMapping(value="/find/{id}")
    public ResponseEntity<User> findById(@PathVariable long id){
        User user = _userService.findById(id);
        return ResponseEntity.ok().body(user);
    }
    @PostMapping(value="/create")
    public ResponseEntity<Void> create(@RequestBody User user){
        _userService.create(user);
        return ResponseEntity.ok().build();
    }

}
