package com.sparta.currency_exchange.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CurrencyExchange> currencyExchange;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User() {}
}