package com.example.onlinecasinobackend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;
    private float balance = 10;

    public User() {
        
    }

    public User(String username, String email, String password, float balance) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.balance = balance;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public float getBalance() {
        return balance;
    }

    public void addBalance(double amount) {
        balance += amount;
    }

    public void deductBalance(double amount) {
        balance -= amount;
    }
}

     

