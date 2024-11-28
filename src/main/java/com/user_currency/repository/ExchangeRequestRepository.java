package com.user_currency.repository;

import com.user_currency.Entity.ExchangeRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExchangeRequestRepository extends JpaRepository<ExchangeRequest, Long> {

    //  특정 고객 환전 요청 조회
    List<ExchangeRequest> findByUserId(Long userId);

}
