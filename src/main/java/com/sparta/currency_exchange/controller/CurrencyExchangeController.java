package com.sparta.currency_exchange.controller;


import com.sparta.currency_exchange.dto.CurrencyExchangeRequestDto;
import com.sparta.currency_exchange.dto.CurrencyExchangeResponseDto;
import com.sparta.currency_exchange.service.CurrencyExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currency-exchange")
@RequiredArgsConstructor
public class CurrencyExchangeController {

    private final CurrencyExchangeService currencyExchangeService;

    @PostMapping
    public ResponseEntity<CurrencyExchangeResponseDto> exchangeCurrency(@RequestBody CurrencyExchangeRequestDto currencyExchangeRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(currencyExchangeService.exchangeRequest(currencyExchangeRequestDto));
    }

    @GetMapping
    public ResponseEntity<List<CurrencyExchangeResponseDto>> getExchangeCurrency(@RequestParam Long userId) {
        return ResponseEntity.ok().body(currencyExchangeService.findAll(userId));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CurrencyExchangeResponseDto> updateExchangeCurrency(@PathVariable Long id, @RequestBody String status) {
        return ResponseEntity.ok().body(currencyExchangeService.updateExchangeRequest(id, status));
    }
}
