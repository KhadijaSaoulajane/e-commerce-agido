package com.agido.ecommerce.account.model;

import com.agido.ecommerce.transaction.model.Transaction;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "account")
public class Account {

    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long account_id;
    private Double balance;

    @OneToMany(mappedBy = "account")
    @JsonManagedReference
    private List<Transaction> transactions = new ArrayList<>();

    public Account(Double balance){
        this.balance = balance;
    }

    public Account(){

    }


    public void setAccount_id(Long account_id) {
        this.account_id = account_id;
    }


    public Long getAccount_id() {
        return account_id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
