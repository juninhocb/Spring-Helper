package com.example.springiojackson.account;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private Set<Account> accounts = new CopyOnWriteArraySet<>();

    @GetMapping
    public Collection<Account> findAll(){return this.accounts;}

    @GetMapping("/{id}")
    public ResponseEntity<Account> findById(@PathVariable Long id){

        Optional<Account> accountOpt = accounts
                .stream()
                .filter(a -> id.equals(a.getId()))
                .findFirst();

        return accountOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createAccount(@RequestBody Account account){
        System.out.println("Account: " + account.getFirstName());
        accounts.add(account);
    }


}
