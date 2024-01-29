package com.agido.ecommerce.user.controller;

import com.agido.ecommerce.account.model.Account;
import com.agido.ecommerce.account.repository.AccountRepository;
import com.agido.ecommerce.user.model.User;
import com.agido.ecommerce.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountRepository accountRepository;

    @PostMapping("")
    public ResponseEntity<User> getUser(@RequestBody User userDao) {
        try {
            User user = userRepository
                    .findUserByEmailAndPassword(userDao.getEmail(),userDao.getPassword());
            if(user != null) return new ResponseEntity<>(user, HttpStatus.OK);
            else
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Boolean> register(@RequestBody User user) {
        try {
           User createdUser = userRepository.save(new User(user.getRole(), user.getEmail(),user.getPassword()));
            Account account = accountRepository.save(new Account(0.0));
            createdUser.setAccount(account);
            userRepository.save(createdUser);
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
