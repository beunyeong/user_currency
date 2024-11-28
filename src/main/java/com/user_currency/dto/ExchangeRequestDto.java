package com.user_currency.dto;

import com.user_currency.ExchangeStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExchangeRequestDto {


    private Long userId;

    private Long toCurrencyId;           // 환전하려는 통화의 ID

    private Double amountInKrw;         // 환전할 금액

    private ExchangeStatus status;

    public ExchangeRequestDto(Long userId, Long toCurrencyId, Double amountInKrw, ExchangeStatus status) {
        this.userId = userId;
        this.toCurrencyId = toCurrencyId;
        this.amountInKrw = amountInKrw;
        this.status = status;
    }

    public ExchangeRequestDto() {}

}
