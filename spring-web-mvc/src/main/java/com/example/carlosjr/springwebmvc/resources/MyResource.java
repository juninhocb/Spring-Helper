package com.example.carlosjr.springwebmvc.resources;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Date;

@RestController
@RequestMapping("/api/v1")
public class MyResource {

    @GetMapping("/stub")
    public ResponseEntity<String> restfullEndpoint(){
        return new ResponseEntity<>("Welcome!", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/request-mapping")
    public String endpointByRequestMapping(){
        return "Welcome from request mapping";
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/void")
    public void getVoidMapping(){
        System.out.println("Void Action."); //can perform a service...
        //assert that response code status pattern is 200
    }

    @GetMapping("/change-status-code")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeStatusCode(){
        System.out.println("Void Action.");
    }

    @GetMapping("/stub2")
    public ResponseEntity<String> stub2(){
        return ResponseEntity.ok().body("Welcome from builder response entity.");
    }

    @PostMapping("/post")
    public ResponseEntity<Void> getPost(UriComponentsBuilder ucb){
        Long fakeId = 17L;

        URI uri = ucb
                .path("/api/v1/{id}")
                .buildAndExpand(fakeId)
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/put")
    public ResponseEntity<String> getPut(){
        HttpHeaders headers = new HttpHeaders();
        headers.setDate(new Date().toInstant());
        headers.set("Gang four", "Builder");

        return ResponseEntity.ok().headers(headers).build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> getDeleteWithCookie(
            @CookieValue(value = "JSESSIONID") String sessionId){

        return ResponseEntity.ok("Your session id is: " + sessionId);
    }

    @GetMapping("/{variable}")
    public ResponseEntity<String> getPathAndCookieVariables(
            @CookieValue(value = "test", required = false, defaultValue = "no-test") String cookieValue,
            @PathVariable(name = "variable") String pathVariable){

        return ResponseEntity.ok().body("Cookie: " + cookieValue + " Variable: " + pathVariable);
    }

    @GetMapping
    public ResponseEntity<String> getRequestParameters(@RequestParam(value = "petterson") String requestValue){
        return ResponseEntity.ok().body("Request param value: " + requestValue);
    }
    
    @PostMapping("/request-body")
    public ResponseEntity<String> getRequestBody(@RequestBody MyDTO myDTO){
        StringBuilder sb = new StringBuilder();
        sb.append("Creating my message! ");
        sb.append(myDTO.name());
        sb.append(" City is: ");
        sb.append(myDTO.city());
        return ResponseEntity.ok().body(sb.toString());
    }

    @GetMapping("/can-except")
    public ResponseEntity<String> canExcept(
            @RequestParam(name = "exception", defaultValue = "false", required = false) String exception) throws Exception {
        boolean isException = !exception.equals("false");
        if(isException) throw new Exception();
        return ResponseEntity.ok().body("No exception occurred");
    }

    @GetMapping("/header")
    public ResponseEntity<String> getRequiredHeader(@RequestHeader(name = "test") String header){
        return ResponseEntity.ok().body("The header is: " + header);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> GetErr(Exception ex){
        return new ResponseEntity<>("Get err: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

