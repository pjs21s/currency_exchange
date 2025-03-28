package com.sparta.currency_exchange.controller;

import com.sparta.currency_exchange.dto.CurrencyRequestDto;
import com.sparta.currency_exchange.dto.CurrencyResponseDto;
import com.sparta.currency_exchange.service.CurrencyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currencies")
@RequiredArgsConstructor
public class CurrencyController {
    private final CurrencyService currencyService;

    @GetMapping
    public ResponseEntity<List<CurrencyResponseDto>> findCurrencies() {
        return ResponseEntity.ok().body(currencyService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CurrencyResponseDto> findCurrency(@PathVariable Long id) {
        return ResponseEntity.ok().body(currencyService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CurrencyResponseDto> createCurrency(@Valid @RequestBody CurrencyRequestDto currencyRequestDto) {
        return ResponseEntity.ok().body(currencyService.save(currencyRequestDto));
    }
}
