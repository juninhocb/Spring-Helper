package com.carlosjr.springreactboot.resources;



import com.carlosjr.springreactboot.models.UserDto;
import com.carlosjr.springreactboot.services.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sign-in")
@RequiredArgsConstructor
@Slf4j
public class SignInResource {

    private final UserServiceImpl userService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String signIn(@RequestBody UserDto userDto){

        boolean isValidate = userService.validateUser(userDto);

        if (isValidate){
            return "Success";
        }

        return "Fail auth";
    }


}
