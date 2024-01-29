package com.agido.ecommerce.user.model;

import com.agido.ecommerce.account.model.Account;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "APP_USER")
public class User {
    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long user_id;
    @Column(name="role")
    private String role;
    @Column(name="email")
    @JsonProperty("email")
    private String email;
    @Column(name="password")
    @JsonProperty("password")
    private String password;
    @OneToOne
    @JoinColumn(name="account_id")
    private Account account;

    public User(String role, String email, String password) {
        this.role = role;
        this.email = email;
        this.password = password;
    }

    public User() {

    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
