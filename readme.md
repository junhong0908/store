# Store API (Spring Boot)


판매자/구매자 역할을 분리한 간단한 쇼핑몰 API입니다.
Spring Boot + JPA 기반으로 상품 등록(CRUD의 시작) 기능을 구현하고,
MVC 테스트를 통해 동작을 검증했습니다.

이 프로젝트는 빠른 기능 구현(Product 우선) 을 목표로 하는 바이브 코딩 방식으로 진행되었습니다.

---
## 📌 프로젝트 목표


Spring Boot 3.5.x + Java 21 환경에 익숙해지기

JPA 기반 도메인 설계 및 CRUD 구현

Security 환경에서 MVC 테스트 작성 경험

“과하지 않은 설계”로 확장 가능한 구조 만들기

---

## 🛠 기술 스택
구분	기술
Language	Java 21
Framework	Spring Boot 3.5.1
Web	Spring MVC
ORM	Spring Data JPA
Security	Spring Security
Validation	Bean Validation
Database	MySQL 8
Test	JUnit 5, Mockito, Spring MVC Test
Build	Gradle
```
📂 패키지 구조
com.example.store
├─ product
│   ├─ controller
│   │   └─ SellerProductController
│   ├─ domain
│   │   ├─ Product
│   │   └─ ProductStatus
│   ├─ dto
│   │   ├─ ProductCreateRequest
│   │   └─ ProductResponse
│   ├─ repository
│   │   └─ ProductRepository
│   └─ service
│       └─ ProductService
└─ global
└─ security
└─ SecurityConfig
```

도메인 기준 패키지 구조를 사용하여
기능 확장 시 영향을 최소화하도록 설계했습니다.

---
## ✨ 구현 기능
판매자 상품 등록

POST /seller/products

판매자가 상품을 등록할 수 있습니다.

입력 값 검증(@Valid)을 적용했습니다.

요청 예시

{
"sellerId": 1,
"name": "Keyboard",
"description": "mechanical keyboard",
"price": 59000,
"stock": 10
}


응답 예시

{
"id": 1,
"sellerId": 1,
"name": "Keyboard",
"description": "mechanical keyboard",
"price": 59000,
"stock": 10,
"status": "ACTIVE"
}

---
## 🧩 도메인 설계 포인트
Product 엔티티

생성/수정 시간은 @PrePersist, @PreUpdate로 자동 관리

상태 값은 enum(ProductStatus)로 명확히 표현

setter를 열지 않고 도메인 메서드로만 변경

public void update(...) {
...
}

---
## 🧪 테스트 전략
### MVC 테스트

@WebMvcTest 기반으로 Controller 계층 테스트

@MockitoBean 사용 (Spring Boot 3.5.x 권장 방식)

Security 필터는 테스트에서 비활성화

@WebMvcTest(SellerProductController.class)
@AutoConfigureMockMvc(addFilters = false)

### 테스트 케이스

정상 요청 시 201 Created

Validation 실패 시 400 Bad Request

---
## 🔐 Security 처리 방식

Spring Security 기본 설정으로 인해 모든 요청은 기본적으로 보호됨

MVP 단계에서는 개발 편의를 위해 일부 엔드포인트를 임시로 허용

### 추후 계획:

로그인 기능 추가

sellerId를 Request Body가 아닌 인증 정보에서 추출

---
# ⚡ 개발 방식 (Vibe Coding)

이 프로젝트는 다음 원칙으로 진행했습니다.

❌ 과한 추상화/설계 지양

✅ Product 기능부터 먼저 완성

✅ 동작하는 코드 → 테스트 → 정리

✅ “필요해질 때 리팩토링”

변경 가능성은 의식하되,
요구가 생기기 전까지는 단순한 구조를 유지했습니다.

---
# 📈 향후 계획

구매자 상품 조회 API (GET /products)

상품 수정 / 삭제 기능

인증 기반 판매자 권한 분리

MyBatis/JDBC 기반 조회 성능 최적화

통합 테스트(Testcontainers) 도입

---
# 🧠 회고

Spring Boot 기본 설정과 실제 동작 흐름을 다시 정리할 수 있었음

JPA + Security 환경에서 MVC 테스트 구성 경험

“기능 우선 → 테스트 → 구조 정리” 흐름의 중요성을 체감

원하면 다음으로: