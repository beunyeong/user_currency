package com.user_currency.dto;

import com.user_currency.ExchangeStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExchangeResDto {

        private Long id;

        private Double amountInKrw;              //  환전 전 금액

        private double amountAfterExchange;      // 환전 후 금액

        private String status;                   // 환전 상태


        public ExchangeResDto(Long id, Double amountInKrw, Double amountAfterExchange, ExchangeStatus status) {
                this.id = id;
                this.amountInKrw = amountInKrw;
                this.amountAfterExchange = amountAfterExchange;
                this.status = status.toString();
        }

}
