package com.sparta.currency_exchange.dto;

import com.sparta.currency_exchange.entity.User;
import lombok.Getter;

@Getter
public class UserRequestDto {
    private String name;
    private String email;

    public User toEntity() {
        return new User(
                this.name,
                this.email
        );
    }
}
