package com.user_currency.controller;

import com.user_currency.Entity.ExchangeRequest;
import com.user_currency.service.ExchangeRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exchange")
public class ExchangeRequestController {

    private final ExchangeRequestService exchangeRequestService;

    /**
     * 1. 환전 요청
     * @param userId  유저 ID
     * @param toCurrencyId  환전 대상 통화 ID
     * @param amountInKrw  환전할 금액(Krw)
     * @return  환전 내역 반환 -> amountInKrw, amountAfterExchange, status
     */
    @PostMapping
    public ResponseEntity<ExchangeRequest> createExchangeRequest(
            @RequestParam Long userId,
            @RequestParam Long toCurrencyId,
            @RequestParam double amountInKrw) {
        return ResponseEntity.ok(exchangeRequestService.createExchangeRequest(userId, toCurrencyId, amountInKrw));
    }

    /**
     * 2. 특정 고객 환전 요청 조회
     * @param userId 유저 ID
     * @return : 특정 고객의 환전 요청 전체 내역 조회
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ExchangeRequest>> getExchangeRequestsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(exchangeRequestService.getExchangeRequestsByUserId(userId));
    }

    /**
     * 3. 환전 요청 상태 변경
     * @param id 환전 요청을 취소하는 사용자 ID
     * @return 환전 요청 상태 (기본)NORMAL -> CANCELED 변경
     */
    @PutMapping("/{id}/cancel")
    public ResponseEntity<ExchangeRequest> cancelExchangeRequest(@PathVariable Long id) {
        return ResponseEntity.ok(exchangeRequestService.cancelExchangeRequest(id));
    }

}