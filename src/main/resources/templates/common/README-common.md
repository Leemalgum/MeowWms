# Common Package

## Overview
`common` 디렉토리는 애플리케이션 내에서 공통으로 사용되는 템플릿, 프래그먼트, 레이아웃 등을 포함합니다. 이 폴더의 목적은 코드의 재사용성을 높이고, 유지보수를 용이하게 하는 데 있습니다. 공통 요소의 중앙 집중식 관리를 통해, 애플리케이션 전반에 걸쳐 일관된 디자인과 구조를 유지할 수 있습니다.

## Key Components
- **Fragments**: 재사용 가능한 템플릿 조각들(예: 헤더, 푸터)을 담고 있습니다. 이를 통해 페이지 전반에 걸쳐 공통적으로 사용되는 UI 구성 요소를 효율적으로 관리할 수 있습니다.
- **Layouts**: 애플리케이션의 전체적인 레이아웃 구조를 정의하는 템플릿 파일들을 포함합니다. 페이지의 기본적인 구조를 여기서 관리합니다.
- **Utilities**: 템플릿 개발에 있어 공통적으로 사용될 수 있는 유틸리티 함수나 클래스를 정의합니다.

## Best Practices
- 공통 요소는 최대한 재사용 가능하도록 구현하세요.
- 프래그먼트와 레이아웃은 명확하고 일관된 명명 규칙을 따르는 것이 좋습니다.
- 변경 가능성이 높은 요소는 가능한 분리하여 관리하여, 나중에 변경이 필요할 때 전체 구조에 미치는 영향을 최소화하세요.

## Example Directory Structure
```plaintext
/common
    /fragments
        - header.html
        - footer.html
    /layouts
        - defaultLayout.html
    /utilities
        - formatting.html
