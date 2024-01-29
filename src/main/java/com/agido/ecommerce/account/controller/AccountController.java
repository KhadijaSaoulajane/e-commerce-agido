package com.agido.ecommerce.account.controller;

import com.agido.ecommerce.account.model.Account;
import com.agido.ecommerce.account.repository.AccountRepository;
import com.agido.ecommerce.user.model.User;
import com.agido.ecommerce.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/accounts")
    @Transactional
    public ResponseEntity<List<Account>> getAllAccounts() {
        try {
            List<Account> accounts = accountRepository.findAllWithTransactions();

            return new ResponseEntity<>(accounts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/account/{user_id}")
    public ResponseEntity<Account> getCustomerAccount(@PathVariable("user_id") long user_id) {
        try {
            Optional<User> userOptional = userRepository.findById(user_id);
            if(userOptional.isPresent()){
                User user = userOptional.get();
                Account account = accountRepository.findAccountWithTransactions(user.getAccount().getAccount_id());
                return new ResponseEntity<>(account, HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
