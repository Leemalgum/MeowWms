# Config Package

## Overview
이 `config` 패키지는 Spring Boot 애플리케이션의 전반적인 구성(configuration)을 담당합니다. 애플리케이션의 실행 환경, 보안 설정, 데이터베이스 연결 등과 같은 중요한 설정들을 이 곳에서 관리합니다.

## Key Components
- **Security Configuration**: 보안 관련 설정을 담당합니다. 예를 들면, Spring Security를 이용한 인증 및 권한 부여 설정이 여기에 포함됩니다.
- **Database Configuration**: 데이터베이스 연결 및 JPA 설정을 관리합니다. DataSource 설정과 같은 데이터베이스 연결 정보도 이 곳에서 구성합니다.
- **Custom Configuration**: 특정 비즈니스 로직에 맞는 사용자 정의 설정을 포함할 수 있습니다.

## Best Practices
- 환경별(application.properties, application-dev.properties 등) 설정 파일을 적극 활용하세요.
- 보안 관련 설정은 항상 최신의 권장 사항을 따르세요.
