package com.user_currency.dto;

import com.user_currency.ExchangeStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStatusDto {

    private ExchangeStatus status;

    public UpdateStatusDto(ExchangeStatus status) {
        this.status = status;
    }

    public UpdateStatusDto() {}

}