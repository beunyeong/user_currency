package com.user_currency.repository;

import com.user_currency.Entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CurrencyRepository extends JpaRepository<Currency, Long> {

}
