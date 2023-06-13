package com.carlosjr.googlesignin.user;

import com.carlosjr.googlesignin.user.enums.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    UserService userService;
    @PostMapping("/create")
    public ResponseEntity<Void> createUser(@RequestBody User user){
        user.setProvider(Provider.LOCAL);
        userService.createUser(user);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("find/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id){
        return ResponseEntity.ok().body(userService.getUserById(id));
    }
}
