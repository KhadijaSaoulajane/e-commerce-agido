package com.agido.ecommerce.transaction.model;

import com.agido.ecommerce.account.model.Account;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.sql.Date;


@Entity
@Table(name = "transaction")
public class Transaction {
    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long transaction_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="account_id")
    @JsonBackReference

    private Account account;
    @Column
    private Date created_at;
    @Column
    private boolean isApproved;
    @Column
    private String type ;
    @Column
    private Double amount;


    public Transaction(Account account) {

        this.account = account;
    }

    public Transaction() {

    }

    public Transaction(Account account, Date created_at, boolean isApproved, Double amount, String type) {
        this.account = account;
        this.created_at = created_at;
        this.isApproved = isApproved;
        this.type = type;
        this.amount = amount;
    }

    public Long getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(Long transaction_id) {
        this.transaction_id = transaction_id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }
}
