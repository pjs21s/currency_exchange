package com.sparta.currency_exchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CurrencyExchangeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyExchangeApplication.class, args);
    }

}
