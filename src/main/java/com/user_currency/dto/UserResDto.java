package com.user_currency.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResDto {

    private Long id;
    private String username;
    private String email;

    public UserResDto(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public UserResDto() {
    }

}
