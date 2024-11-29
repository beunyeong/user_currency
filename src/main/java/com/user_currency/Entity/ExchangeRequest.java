package com.user_currency.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.user_currency.ExchangeStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "exchange_request")
public class ExchangeRequest extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)          // 데이터를 실제로 사용할 때 조회(지연로딩 방식)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference                          // 자식 관계로 설정
    private User user;                          // 고객 ID -> user와 N:1 관계 설정

    @ManyToOne(fetch = FetchType.LAZY)          // 데이터를 실제로 사용할 때 조회(지연로딩 방식)
    @JoinColumn(name = "to_currency_id", nullable = false)
    @JsonBackReference                              // 자식 관계로 설정
    private Currency currency;                  // 환전 대상 통화 ID -> currency와 N:1 관계 설정

    @Column(nullable = false)
    private double amountInKrw;                 // 환전 전 금액

    @Column(nullable = false)
    private double amountAfterExchange;         // 환전 후 금액

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExchangeStatus status;              // 환전 상태(NORMAL, CANCELED)

}
