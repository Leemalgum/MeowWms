# Templates Package

## Overview
`templates` 디렉토리는 웹 애플리케이션에서 사용되는 동적 웹 페이지의 템플릿을 포함합니다. 이 폴더는 주로 HTML 파일을 저장하는 데 사용되며, Thymeleaf, FreeMarker, JSP와 같은 템플릿 엔진을 사용하여 런타임에 동적으로 웹 페이지를 생성합니다. Spring Boot 애플리케이션에서는 이 디렉토리의 템플릿 파일들을 자동으로 뷰 레이어로 인식하고 처리합니다.

## Key Components
- **HTML Templates**: 웹 페이지의 레이아웃과 구조를 정의하는 HTML 파일들입니다. 템플릿 엔진의 문법을 사용하여 데이터를 동적으로 표시하거나 조건에 따라 다른 내용을 보여 줄 수 있습니다.
- **Fragments**: 재사용 가능한 페이지 조각 또는 컴포넌트입니다. 예를 들어, 헤더, 푸터, 내비게이션 바와 같은 공통 요소들을 프래그먼트로 관리할 수 있습니다.

## Best Practices
- 템플릿 파일들은 가독성을 높이고 유지보수를 용이하게 하기 위해 적절히 구조화하세요.
- 공통적으로 사용되는 레이아웃 요소는 프래그먼트로 분리하여 코드 중복을 줄이세요.
- 템플릿 엔진의 기능을 최대한 활용하여 데이터 바인딩, 반복, 조건문 등을 효과적으로 사용하세요.
- 동적 컨텐츠를 생성할 때는 사용자 입력을 적절히 검증하고, 출력 데이터를 적절히 이스케이프하여 XSS 공격 등의 보안 위협을 방지하세요.

## Serving Templates in Spring Boot
Spring Boot에서는 `templates` 디렉토리 내의 파일들을 자동으로 템플릿으로 인식하며, 주로 Thymeleaf와 같은 템플릿 엔진을 사용하여 뷰를 렌더링합니다. `application.properties` 파일에서 템플릿 엔진의 설정을 조정할 수 있습니다.

## Example Directory Structure
```plaintext
/templates
    /fragments
        - header.html
        - footer.html
    /user
        - login.html
        - register.html
    - index.html
