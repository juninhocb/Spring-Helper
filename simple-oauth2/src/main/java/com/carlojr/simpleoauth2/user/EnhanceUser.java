package com.carlojr.simpleoauth2.user;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;


public class EnhanceUser extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = 1L;
    private User user;
    public EnhanceUser(User user){
        super(user.getUsername(),user.getPassword(), Arrays.asList(new SimpleGrantedAuthority("read"), new SimpleGrantedAuthority("write")));
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }
}