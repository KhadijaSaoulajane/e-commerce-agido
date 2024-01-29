package com.agido.ecommerce.transaction.controller;

import com.agido.ecommerce.account.model.Account;
import com.agido.ecommerce.account.repository.AccountRepository;
import com.agido.ecommerce.transaction.model.Transaction;
import com.agido.ecommerce.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountRepository accountRepository;


    @GetMapping("/approve/{id}")
    public ResponseEntity<Boolean> approveTransaction(@PathVariable("id") long id) {
        Optional<Transaction> transactionData = transactionRepository.findById(id);

        if (transactionData.isPresent()) {
            Transaction transaction = transactionData.get();
            Double amount = transaction.getAmount();
            Account account = transaction.getAccount();
            if(account.getBalance() - amount > 0.0){
                account.setBalance(account.getBalance()-amount);
                transaction.setApproved(true);
                transactionRepository.save(transaction);
                accountRepository.save(account);
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<Account> createTransaction(@RequestBody Transaction transaction) {
        try {
            Optional<Account> accountOptional = accountRepository.findById(transaction.getAccount().getAccount_id());
            if(accountOptional.isPresent()){
                Account account = accountOptional.get();
                Transaction createdTransaction = transactionRepository.save(new Transaction(account, new Date(System.currentTimeMillis()),false,transaction.getAmount(),transaction.getType()));
                if ("D".equals(transaction.getType())){
                    account.setBalance(account.getBalance() + transaction.getAmount());
                }
                accountRepository.save(account);
                transactionRepository.save(createdTransaction);
                return new ResponseEntity<>(accountRepository.findAccountWithTransactions(account.getAccount_id()), HttpStatus.CREATED);

            }
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
