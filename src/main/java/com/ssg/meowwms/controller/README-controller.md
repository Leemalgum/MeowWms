# Controller Package

## Overview
`controller` 패키지는 사용자의 요청을 처리하고 응답을 관리하는 컨트롤러 클래스들을 포함합니다. RESTful API 대신 다른 형태의 웹 애플리케이션 컨트롤러를 구현할 예정입니다.

## Key Components
- **Action Controllers**: 사용자의 액션에 따라 적절한 서비스 로직을 호출하고 결과를 뷰에 전달하는 컨트롤러들입니다.

## Best Practices
- 컨트롤러는 가능한 가볍게 유지하고, 모든 비즈니스 로직은 서비스 계층에 위임하세요.
- 사용자 요청을 받고 응답을 준비하는 로직에 집중하며, 요청 데이터 검증에도 주의를 기울이세요.
