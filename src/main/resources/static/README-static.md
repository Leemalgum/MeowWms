# Static Package

## Overview
`static` 디렉토리는 웹 애플리케이션에서 사용되는 정적 리소스를 포함합니다. 이 폴더는 CSS, JavaScript, 이미지 파일 등과 같은 클라이언트 사이드 자원들을 저장하는 데 사용됩니다. Spring Boot 애플리케이션에서 `static` 디렉토리의 내용은 웹 루트(`/`) 아래에 자동으로 매핑됩니다.

## Key Components
- **CSS Files**: 웹 애플리케이션의 스타일을 정의하는 Cascading Style Sheets 파일들입니다.
- **JavaScript Files**: 클라이언트 사이드 동작을 구현하는 JavaScript 파일들입니다.
- **Images**: 웹 애플리케이션에서 사용되는 이미지 파일들입니다.

## Best Practices
- 파일 구조는 명확하고 일관되게 유지하세요. 예를 들어, `css`, `js`, `img`와 같은 하위 디렉토리를 사용하여 각 리소스 유형별로 파일을 분류할 수 있습니다.
- 정적 리소스의 버전 관리를 통해 캐싱 문제를 방지하세요. 파일명 뒤에 버전 번호나 해시를 추가하는 방법을 고려해 보세요.
- 웹 성능 최적화를 위해, 필요한 경우 CSS와 JavaScript 파일을 압축하고 최소화(minify)하세요.
- 보안 측면을 고려하여, 사용하지 않는 리소스는 제거하고, 적절한 액세스 제어를 적용하세요.

## Serving Static Resources in Spring Boot
Spring Boot에서는 `static`, `public`, `resources`, `META-INF/resources` 디렉토리 내의 정적 리소스를 자동으로 서비스합니다. `application.properties` 파일을 통해 정적 리소스의 위치를 변경하거나 추가할 수 있습니다.

## Example Directory Structure
```plaintext
/static
    /css
        - style.css
    /js
        - script.js
    /img
        - logo.png
