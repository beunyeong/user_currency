package com.user_currency.service;

import com.user_currency.Entity.ExchangeRequest;
import java.util.List;


public interface ExchangeRequestService {


    /**
     * 1. 환전 요청
   */
    ExchangeRequest createExchangeRequest(Long userId, Long CurrencyId, double amountInKrw);

    /**
     * 2. 특정 고객 환전 요청 조회
     */
    List<ExchangeRequest> getExchangeRequestsByUserId(Long userId);

    /**
     * 3. 환전 요청 상태 변경
     */
    ExchangeRequest cancelExchangeRequest(Long exchangeRequestId);

}




