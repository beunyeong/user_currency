package com.user_currency.service;

import com.user_currency.Entity.User;


public interface UserService {

    // 1. 유저 생성
    User createUser(String username, String email);


    // 2. 유저 및 환전 내역 삭제
    void deleteUserById(Long id);

}
