package com.carlosjr.googlesignin.client;

import com.carlosjr.googlesignin.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ClientController {

    @Autowired
    UserDetailsService userDetailsService;

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String testController(){
        System.out.println("Test success");
        return "index";
    }

    @PostMapping("/login_success_handler")
    public String loginSuccessHandler(){
        System.out.println("Was logged in");
        return "index";
    }
    @PostMapping("/login_failure_handler")
    public String loginFailureHandler(){
        System.out.println("Not successfully logged in");
        return "login";
    }

}
