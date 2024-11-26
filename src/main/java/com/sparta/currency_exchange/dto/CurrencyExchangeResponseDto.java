package com.sparta.currency_exchange.dto;

import com.sparta.currency_exchange.entity.CurrencyExchange;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class CurrencyExchangeResponseDto {
    private Long userId;
    private Long toCurrencyId;

    private BigDecimal amountInKrw;
    private BigDecimal amountAfterExchange;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CurrencyExchangeResponseDto(CurrencyExchange savedCurrencyExchange) {
        this.userId = savedCurrencyExchange.getUser().getId();
        this.toCurrencyId = savedCurrencyExchange.getToCurrency().getId();
        this.amountInKrw = savedCurrencyExchange.getAmountInKrw();
        this.amountAfterExchange = savedCurrencyExchange.getAmountAfterExchange();
        this.status = savedCurrencyExchange.getStatus();
        this.createdAt = savedCurrencyExchange.getCreatedAt();
        this.modifiedAt = savedCurrencyExchange.getModifiedAt();
    }

    public CurrencyExchangeResponseDto(Long userId, Long toCurrencyId, BigDecimal amountInKrw, BigDecimal amountAfterExchange, String status, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.userId = userId;
        this.toCurrencyId = toCurrencyId;
        this.amountInKrw = amountInKrw;
        this.amountAfterExchange = amountAfterExchange;
        this.status = status;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static CurrencyExchangeResponseDto toDto(CurrencyExchange currencyExchange) {
        return new CurrencyExchangeResponseDto(
                currencyExchange.getUser().getId(),
                currencyExchange.getToCurrency().getId(),
                currencyExchange.getAmountInKrw(),
                currencyExchange.getAmountAfterExchange(),
                currencyExchange.getStatus(),
                currencyExchange.getCreatedAt(),
                currencyExchange.getModifiedAt()
        );
    }
}
