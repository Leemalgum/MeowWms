# Mapper Package

## Overview
`mapper` 패키지는 MyBatis 매퍼 인터페이스를 포함합니다. 이 인터페이스들은 MyBatis와 데이터베이스 간의 연결을 담당하며, SQL 쿼리를 메소드 호출로 추상화합니다. 이를 통해 데이터베이스의 CRUD 작업을 손쉽게 수행할 수 있습니다.

## Key Components
- **MyBatis Mapper Interfaces**: SQL 쿼리와 연결된 메소드를 정의합니다. MyBatis는 이 인터페이스를 구현하는 프록시 객체를 자동으로 생성하여, SQL 쿼리를 실행합니다.

## Best Practices
- 매퍼 인터페이스와 XML 파일은 명확하게 관리하세요. 가능하면 매퍼 인터페이스와 같은 이름의 XML 파일을 사용하는 것이 좋습니다.
- SQL 쿼리는 성능과 유지보수를 고려하여 작성하세요. 복잡한 쿼리는 가능하면 단순화하거나 분리하여 관리하는 것이 유리합니다.
