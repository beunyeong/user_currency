package com.user_currency.service;

import com.user_currency.Entity.User;
import com.user_currency.dto.UserRequestDto;
import com.user_currency.dto.UserResDto;
import com.user_currency.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    // 1. 유저 생성
    @Override
    public User createUser(String username, String email) {
        if(userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);

        return userRepository.save(user);
    }


    // 2. 유저 및 환전 내역 삭제
    @Override
    public void deleteUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자 입니다."));
        userRepository.delete(user);

    }
}

