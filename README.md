[API 명세서](https://www.notion.so/53adffc2b6574c47a3d46b4488f12cdb?pvs=21)

## 1. 유저 생성

- 유저 생성 예시
  - method: POST
  - URL: /users
  - 요청 body

      ```json
      {
          "username": "baek",
          "email": "email1111@email.com"
      }
      ```
 
  - 응답 body (201 Created)

      ```json
      {
          "createdAt": "2024-11-28T22:56:03.301862",
          "modifiedAt": "2024-11-28T22:56:03.301884",
          "id": 2,
          "username": "baek",
          "email": "email1111@email.com"
      }
      ```

  - 응답 error (400 Bad Request) - 중복 이메일

      ```json
      {
          "errorMessage": "이미 존재하는 이메일입니다.",
          "errorCode": "BAD_REQUEST"
      }
      ```


## 2. 환전 요청

- 환전 요청 예시
  - method: POST
  - URL: /exchange?userId=1&toCurrencyId=1&amountInKrw=200000
  - 응답 body (200 OK)

      ```json
      {
      "createdAt": "2024-11-28T23:35:33.716577",
      "modifiedAt": "2024-11-28T23:35:33.716603",
      "id": 1,
      "amountInKrw": 200000.0,
      "amountAfterExchange": 139.86,
      "status": "NORMAL"
      }
      ```

  - 응답 error (404 Not Found) - 존재 하지 않는 통화(USD)

      ```json
      {
          "errorMessage": "존재 하지 않는 통화 입니다.",
          "errorCode": "NOT_FOUND"
      }
      ```

  - 응답 error (404 Not Found) - 존재 하지 않는 사용자

      ```json
      {
          "errorMessage": "존재하지 않는 사용자입니다.",
          "errorCode": "NOT_FOUND"
      }
      ```


## 3.  특정 고객 환전 요청 조회

- 환전 요청 조회 예시
  - method: GET
  - URL: /exchange/user/{userId}
  - 응답 body(200 OK) - 조회되는 내역이 여러개인 경우

      ```json
      [
          {
              "createdAt": "2024-11-28T23:09:31.424224",
              "modifiedAt": "2024-11-28T23:09:31.424245",
              "id": 1,
              "amountInKrw": 10000,
              "amountAfterExchange": 6.99,
              "status": "NORMAL"
          },
          {
              "createdAt": "2024-11-28T23:09:48.591507",
              "modifiedAt": "2024-11-28T23:09:48.591529",
              "id": 2,
              "amountInKrw": 10000,
              "amountAfterExchange": 6.99,
              "status": "NORMAL"
          },
          {
              "createdAt": "2024-11-28T23:09:56.467782",
              "modifiedAt": "2024-11-28T23:09:56.467807",
              "id": 3,
              "amountInKrw": 200000,
              "amountAfterExchange": 139.86,
              "status": "NORMAL"
          }
      ]
      ```

  - 응답 body(200 OK) - 조회되는 내역이 없는 경우

      ```json
      []
      ```



## 4.  환전 요청 상태 변경(***NORMAL, CANCELED)***

- 환전 요청 상태 변경 예시
  - method: PUT
  - URL: /exchange/{id}/cancel
  - 응답 body (200 OK)

      ```json
      {
          "createdAt": "2024-11-28T23:09:31.424224",
          "modifiedAt": "2024-11-28T23:11:50.460609",
          "id": 1,
          "amountInKrw": 10000,
          "amountAfterExchange": 6.99,
          "status": "CANCELED"
      }
      ```

  - 응답 error (404 Not Found) - 존재 하지 않는 환전 요청

      ```json
      {
          "errorMessage": "존재하지 않는 환전 요청입니다.",
          "errorCode": "NOT_FOUND"
      }
      ```


## 5.  유저 및 환전 내역 삭제

- 유저 및 환전 내역 삭제 예시
  - method: DELETE
  - URL: /users/{id}
  - 응답 body (204 No Content)

      ```json
      
      ```

  - 응답 error (400 Bad Request) - 존재 하지 않는 사용자

      ```json
      {
          "errorMessage": "존재하지 않는 사용자 입니다.",
          "errorCode": "BAD_REQUEST"
      }
      ```


ERD 설계

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/841312d8-b691-488b-828a-50dcb40acbf6/dd4ba8c6-8bcd-4899-9f27-2ace9615ccfe/image.png)

- user 테이블 구조


    | 컬럼명 | 설명 | 예시 |
    | --- | --- | --- |
    | id | 고객 고유 식별자 | 1 |
    | email | 고객 이메일 | test@naver.com |
    | name | 고객 이름 | park |
    | created_at | 생성일자 | 2024-11-18 16:42:03.000000 |
    | modified_at | 수정일자 | 2024-11-18 16:42:03.000000 |
- currency 테이블 구조


    | **컬럼명** | **설명** | **예시** |
    | --- | --- | --- |
    | id | 통화 고유 식별자 | 1 |
    | exchange_rate | 환율 | 1430.00 |
    | currency_name | 통화 이름 | USD |
    | symbol | 표시 | $ |
    | created_at | 생성일자 | 2024-11-18 16:42:03.000000 |
    | modified_at | 수정일자 | 2024-11-18 16:42:03.000000 |
- exchange_request 테이블 구조


    | **컬럼명** | **설명** | **예시** |
    | --- | --- | --- |
    | id | 통화 고유 식별자 | 1 |
    | user_id | 고객 고유 식별자
    (User 테이블 ID) | 1 |
    | to_currency_id | 환전 대상 통화 식별자
    (Currency 테이블 ID) | 1 |
    | amount_in_krw | 환전 전 금액
    (원화 기준) | 10000 |
    | amount_after_exchange | 환전 후 금액 | 6.99 |
    | status | 상태 | normal |
    | created_at | 생성일자 | 2024-11-18 16:42:03.000000 |
    | modified_at | 수정일자 | 2024-11-18 16:42:03.000000 |

SQL 생성문

```sql
-- User 테이블 생성
CREATE TABLE user (
                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                    username VARCHAR(255) NOT NULL,
                    email VARCHAR(255) NOT NULL UNIQUE,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Currency 테이블 생성
CREATE TABLE currency (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        exchange_rate DOUBLE NOT NULL,
                        currency_name VARCHAR(255) NOT NULL,
                        symbol VARCHAR(10) NOT NULL,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- ExchangeRequest 테이블 생성
CREATE TABLE exchange_request (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                user_id BIGINT NOT NULL,
                                to_currency_id BIGINT NOT NULL,
                                amount_in_krw DOUBLE NOT NULL,
                                amount_after_exchange DOUBLE,
                                status ENUM('NORMAL', 'CANCELED') NOT NULL
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
                                FOREIGN KEY (to_currency_id) REFERENCES currency(id)
);
```