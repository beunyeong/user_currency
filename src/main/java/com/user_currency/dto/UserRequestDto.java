package com.user_currency.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {

    private String username;

    private String email;


    public UserRequestDto(String username, String email) {
        this.username = username;
        this.email = email;
    }

}
