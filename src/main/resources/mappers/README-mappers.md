# Mapper XML Package

## Overview
`mapper/xml` 디렉토리는 MyBatis의 SQL 매퍼 파일들을 포함합니다. 이 XML 파일들은 데이터베이스 테이블과 자바 객체 간의 SQL 쿼리 매핑을 정의합니다. `mapper` 패키지 내의 인터페이스와 함께 사용되어, 실제 데이터베이스 연산을 수행합니다.

## Key Components
- **XML Mapper Files**: 각 데이터베이스 테이블 또는 엔티티에 대응되는 SQL 쿼리들을 담고 있는 XML 파일입니다. SELECT, INSERT, UPDATE, DELETE 등의 CRUD 연산을 정의합니다.

## Best Practices
- 파일 이름은 관련 있는 Java Mapper 인터페이스 이름과 일치시키는 것이 좋습니다. 이는 SQL 매퍼 파일과 자바 인터페이스 사이의 연관성을 명확히 하고, 파일을 찾기 쉽게 만듭니다.
- SQL 쿼리는 가능한 간결하게 유지하며, 성능 최적화를 고려하여 작성하세요.
- XML Mapper 파일 내에서 SQL 문을 작성할 때는 가독성을 고려하여 적절히 줄바꿈과 들여쓰기를 사용하세요.
- 동적 SQL을 사용하는 경우, MyBatis의 제공 기능(예: `<if>`, `<choose>`, `<foreach>`)을 적절히 활용하여, 코드의 복잡성을 줄이고 유지보수성을 향상시키세요.

## Naming Convention
- 파일명: [TableName]Mapper.xml (예: UserMapper.xml)
- id 속성: 인터페이스 메소드 이름과 일치시킵니다.

## Example
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.example.project.mapper.UserMapper">
    <select id="selectUser" resultType="com.example.project.domain.User">
        SELECT * FROM user WHERE id = #{id}
    </select>
</mapper>
