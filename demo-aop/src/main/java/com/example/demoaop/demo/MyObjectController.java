package com.example.demoaop.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/object")
public class MyObjectController {

    @GetMapping("/{id}")
    public ResponseEntity<MyObject> getObjectById(@PathVariable Integer id) {
        if (new Random().nextBoolean()){
            throw new NullPointerException("Is not a null pointer");
        }
        return new ResponseEntity<>(MyObject.builder().id(id).name("Got it!").isAny(true).build(), HttpStatus.OK);
    }

    @AnnotatedAspect
    @GetMapping("/str")
    public ResponseEntity<String> getString(){
        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }

    @AnnotatedAspect(someValue = 3)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> errHandler(RuntimeException re) {
       return new ResponseEntity<>(re.getMessage(), HttpStatus.BAD_REQUEST);
    }
}


