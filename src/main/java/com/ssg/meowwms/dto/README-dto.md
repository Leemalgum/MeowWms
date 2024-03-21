# DTO Package

## Overview
`dto` (Data Transfer Object) 패키지는 계층 간 데이터 교환을 위한 객체들을 정의합니다. 이들은 주로 컨트롤러와 서비스 계층 사이, 또는 서비스 계층과 클라이언트 사이에서 데이터를 전달하는 데 사용됩니다.

## Key Components
- **Request DTOs**: 클라이언트로부터 받은 요청 데이터를 담습니다.
- **Response DTOs**: 클라이언트에게 전달할 응답 데이터를 담습니다.

## Best Practices
- DTO는 로직을 포함하지 않고, 데이터 전송 목적으로만 사용하세요.
- 엔티티와 DTO를 명확히 분리하여, 서로 다른 계층 간의 의존성을 최소화하세요.
