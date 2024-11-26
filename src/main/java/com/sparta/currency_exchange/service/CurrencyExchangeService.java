package com.sparta.currency_exchange.service;

import com.sparta.currency_exchange.dto.CurrencyExchangeRequestDto;
import com.sparta.currency_exchange.dto.CurrencyExchangeResponseDto;
import com.sparta.currency_exchange.entity.Currency;
import com.sparta.currency_exchange.entity.CurrencyExchange;
import com.sparta.currency_exchange.entity.User;
import com.sparta.currency_exchange.repository.CurrencyExchangeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyExchangeService {
    private final CurrencyExchangeRepository currencyExchangeRepository;
    private final UserService userService;
    private final CurrencyService currencyService;

    final int ROUND_NUMBER = 2;

    @Transactional
    public CurrencyExchangeResponseDto exchangeRequest(CurrencyExchangeRequestDto requestDto) {

        User user = userService.findUserById(requestDto.getUserId());
        Currency toCurrencyById = currencyService.findCurrencyById(requestDto.getToCurrencyId());

        BigDecimal exchangedAmount = calculateExchangedAmount(requestDto.getAmountInKrw(), toCurrencyById);

        CurrencyExchange currencyExchange = new CurrencyExchange(user, toCurrencyById, requestDto.getAmountInKrw(), exchangedAmount, requestDto.getStatus());
        CurrencyExchange savedCurrencyExchange = currencyExchangeRepository.save(currencyExchange);
        return new CurrencyExchangeResponseDto(savedCurrencyExchange);
    }

    @Transactional
    public CurrencyExchangeResponseDto updateExchangeRequest(Long id, String status) {
        CurrencyExchange currencyExchange = findById(id);

        currencyExchange.update(status);

        return CurrencyExchangeResponseDto.toDto(currencyExchange);
    }

    public CurrencyExchange findById(Long id) {
        return currencyExchangeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("환전 요청 기록을 찾을 수 없습니다."));
    }

    public List<CurrencyExchangeResponseDto> findAll(Long userId) {
        List<CurrencyExchange> currencyExchanges = currencyExchangeRepository.findAllByUserId(userId);

        return currencyExchanges.stream().map(CurrencyExchangeResponseDto::toDto).toList();
    }

    private BigDecimal calculateExchangedAmount(BigDecimal amount, Currency toCurrencyById) {
        return amount.divide(toCurrencyById.getExchangeRate(), ROUND_NUMBER, RoundingMode.HALF_UP);
    }
}
