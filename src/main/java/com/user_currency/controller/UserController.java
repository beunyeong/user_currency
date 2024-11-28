package com.user_currency.controller;

import com.user_currency.Entity.User;
import com.user_currency.dto.UserRequestDto;
import com.user_currency.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userService;

    /**
     * 1. 유저 생성
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserRequestDto userRequestDto) {
        User createdUser = userService.createUser(userRequestDto.getUsername(), userRequestDto.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    /**
     * 2. 유저 및 환전 내역 삭제
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

}
