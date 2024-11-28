package com.user_currency;

import com.user_currency.Entity.Currency;
import com.user_currency.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final CurrencyRepository currencyRepository;

    @Override
    public void run(String... args) throws Exception {
        if (currencyRepository.count() == 0) { // 데이터가 없는 경우만 초기화
            currencyRepository.save(new Currency(null, 1430.0, "USD", "$"));
            System.out.println("통화 기본 데이터 설정: USD");
        } else {
            System.out.println("기본 통화가 이미 설정 되었습니다.(기본:USD)");
        }
    }

}
