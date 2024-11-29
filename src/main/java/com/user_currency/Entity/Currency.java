package com.user_currency.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "currency")
public class Currency extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double exchangeRate;            // 원화 환율(1 USD = 1430 KRW)

    @Column(nullable = false)
    private String currencyName;

    @Column(nullable = false)
    private String symbol;


    // ExchangeRequest(1)와 Currency(N) 간의 관계(1:N) 설정
    @OneToMany(mappedBy = "currency", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference                  // 부모 관계로 설정
    private List<ExchangeRequest> exchangeRequests = new ArrayList<>();


    public Currency(Long id, Double exchangeRate, String currencyName, String symbol) {
        this.id = id;
        this.exchangeRate = exchangeRate;
        this.currencyName = currencyName;
        this.symbol = symbol;
    }

    public Currency() {}

}
