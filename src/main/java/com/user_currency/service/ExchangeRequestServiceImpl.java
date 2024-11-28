package com.user_currency.service;

import com.user_currency.Entity.Currency;
import com.user_currency.Entity.ExchangeRequest;
import com.user_currency.Entity.User;
import com.user_currency.ExchangeStatus;
import com.user_currency.repository.CurrencyRepository;
import com.user_currency.repository.ExchangeRequestRepository;
import com.user_currency.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ExchangeRequestServiceImpl implements ExchangeRequestService {


    private final ExchangeRequestRepository exchangeRequestRepository;
    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;

    // 1. 환전 요청
    @Override
    public ExchangeRequest createExchangeRequest(Long userId, Long CurrencyId, double amountInKrw){

        // 사용자와 통화 데이터 확인
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        Currency currency = currencyRepository.findById(CurrencyId)
                .orElseThrow(()-> new IllegalArgumentException("존재 하지 않는 통화 입니다."));

        // 환율 기반으로 환전 후 금액 계산 (소수점 둘째 자리 반올림)
        double exchangeRate = currency.getExchangeRate();
        double amountAfterExchange = Math.round((amountInKrw / exchangeRate) * 100) / 100.0;


        ExchangeRequest request = new ExchangeRequest();
        request.setUser(user);
        request.setCurrency(currency);
        request.setAmountInKrw(amountInKrw);
        request.setAmountAfterExchange(amountAfterExchange);
        request.setStatus(ExchangeStatus.NORMAL);

        return exchangeRequestRepository.save(request);
    }

    // 2. 특정 고객 환전 요청 조회
    @Override
    public List<ExchangeRequest> getExchangeRequestsByUserId(Long userId) {
        return exchangeRequestRepository.findByUserId(userId);
    }

    // 3. 환전 요청 상태 변경
    @Override
    public ExchangeRequest cancelExchangeRequest(Long exchangeRequestId) {
        ExchangeRequest request = exchangeRequestRepository.findById(exchangeRequestId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 환전 요청입니다."));
        request.setStatus(ExchangeStatus.CANCELED);

        return exchangeRequestRepository.save(request);

    }

}

