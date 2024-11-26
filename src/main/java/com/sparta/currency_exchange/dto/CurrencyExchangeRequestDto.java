package com.sparta.currency_exchange.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CurrencyExchangeRequestDto {
    private Long userId;
    private Long toCurrencyId;

    private BigDecimal amountInKrw;
    private String status;
}
