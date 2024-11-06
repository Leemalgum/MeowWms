# Tomcat is Meow
<p align="center">
  <img src="https://github.com/Leemalgum/MeowWms/blob/main/images/logo.png?raw=true" width="800">
  <br>
  <strong>WMS : Warehouse Management System</strong>
</p>
  
- **프로젝트 명 :** WMS
- **프로젝트 기간 :** 2024.03.19 ~ 2024.03.31
- **팀 노션 :** [노션 바로가기](https://www.notion.so/7b4f051ea28f41cdb6d3196ee5743bb4)
## 프로젝트 주제 및 선정 배경
- WMS(Warehouse Management System)의 중요 기능과 역할을 깊이 이해하고, 이를 바탕으로 실제 환경에서의 창고 관리 프로세스를 효율화할 수 있는 웹 기반 솔루션을 개발하는 것을 목표로 한 프로젝트입니다.
## 프로젝트 주요 내용
### 주요 기능
- 회원 관리
- 창고 및 재고 관리
- 입출고 관리
- 재무 관리
- 고객센터
### 나의 상세 구현 기능
- **회원 정보 관리** : <br>
  회원 정보를 등록, 조회, 수정, 삭제할 수 있습니다. 회원 가입시 정규식 검사와 비밀번호를 2번 입력받아 입력값에 대한 검증을 진행합니다. <br>
  관리자일 경우 회원 전체 조회와 상세 정보 조회가 가능하며 권한과 가입상태를 변경할 수 있습니다. <br>
- **로그인** <br>
    회원 가입시 등록한 이름과 이메일로 아이디를 찾을 수 있습니다.<br>
    로그인시 해당 회원의 권한을 검사하여 관리자와 일반 회원의 접근 화면을 구분하였습니다. <br>
## ERD
<p align="center">
  <img src="https://github.com/Leemalgum/MeowWms/blob/main/images/ERD.png?raw=true" width="800">
  
</p>

## Wireframe
<p align="center" >
  로그인 화면<br>
  <img src="https://github.com/Leemalgum/MeowWms/blob/main/images/signin.png?raw=true" width="800"> <br>
  회원가입 화면<br>
  <img src="https://github.com/Leemalgum/MeowWms/blob/main/images/signup.png?raw=true" width="800"> <br>
  일반 회원의 기본 화면<br>
  <img src="https://github.com/Leemalgum/MeowWms/blob/main/images/user-page.png?raw=true" width="800"> <br>
  일반 회원의 정보 수정 화면<br>
  <img src="https://github.com/Leemalgum/MeowWms/blob/main/images/user-info.png?raw=true" width="800"> <br>
  관리자 회원의 기본 화면<br>
  <img src="https://github.com/Leemalgum/MeowWms/blob/main/images/admin-page.png?raw=true" width="800"> <br>
  관리자 회원의 전체 회원 정보 조회 및 수정 화면<br>
  <img src="https://github.com/Leemalgum/MeowWms/blob/main/images/admin-info.png?raw=true" width="800">
</p>

