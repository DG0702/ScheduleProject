# ScheduleProject

🪄 - **일정 관리 앱 프로젝트**

## 🖥️ 개발 프로세스(환경)

### ⛏️IDE :
- `Intellij`

### 📌 Java :
- **JDK 17버전 사용**

### 🔁 Version Control :
- `GitHub`

### 🎗️ API 테스트 도구
- `PostMan`

<br>
<hr>

## ✅ 요구사항

### 🎀 공통 조건 

- **일정 작성, 수정, 조회 시 -> 응답 받는 일정 정보에서 `비밀번호`는 제외**


- **일정 수정, 삭제 시 -> 선택한 일정의 `비밀번호`가 요청 하는 `비밀번호`와 일치할 경우 가능**

    - **오류 발생 시 적절한 오류 코드, 및 메시지 반환**


- **CRUD 필수 기능은 모두 데이터베이스 연결 및 `JDBC`를 사용해서 개발**


- **`JPA` 사용이 아닌 `JDBC` 사용**


- **`Entity`에 그대로 반환하지 않고 `DTO`에 담아서 반환**


- **`3 Layer Architecture`에 따라 각 Layer 목적에 알맞게 개발**


### [LV0️⃣] API 명세 및 ERD 작성

- **🍭 API 명세서** 


  - **[멤버API 명세서](https://documenter.getpostman.com/view/44617139/2sB2jAcTv4)**


  - **[일정API 명세서](https://documenter.getpostman.com/view/44617139/2sB2jAcU4r)**



- **[ERD](https://www.erdcloud.com/d/Nb9nYKe988ckh29W6)**

<br>

### [LV1️⃣] 일정 생성 및 조회

- **일정 생성(일정 작성하기)**

    - **일정 생성 시 포함되어야할 데이터**

        - **`할일`, `작성자명`, `비밀번호`, `작성/수정일`**

        - **`작성/수정일`은 날짜와 시간 모두 포함한 상태**

        - **각 일정의 고유 식별자(ID)를 자동으로 생성해서 관리**

        - **최초 입력시 수정일은 작성일과 동일**


- **전체 일정 조회 (등록된 일정 불러오기)**

    - **조건**

        - **수정일 (형식 : YYYY-MM-DD)**

        - **수정일 기준으로 `내림차순` 정렬**


- **선택 일정 조회(선택한 일정 정보 불러오기)**

    - **선택한 일정 단건의 정보 조회**

    - **일정의 고유 식별자(ID)를 사용하여 조회**


<br>

### [LV2️⃣]

- **일정 수정 및 삭제**

    - **선택한 일정 수정**

       - **선택한 일정 내용 중 `할 일`, `작성자명`만 수정가능**

       - **`작성일`은 변경할 수 없으면 `수정일`은 수정 완료 시 수정한 시점으로 변경**

    - **선택한 일정 삭제**

       - **서버에 일정 삭제을 요청할 때 `비밀번호`를 함께 전달**

<br>
<hr>

## ⭐ 주요 기능

📌 **`3 Layer Architecture`에 알맞게 책임 분할**

📌 **`JPA`가 아닌 `JDBC`활용 -> 데이터베이스 쿼리문 활용**

📌 **`CRUD` 기능을 활용**

📌 **`DTO`을 활용하여 `Entity` 변환**

<br>
<hr>

## 🗂️ 디렉토리 구조

### 🪄 **src 디렉토리**

```
C:.
└─src
├─main
│ ├─java
│ │ └─com
│ │ └─project
│ │ └─scheduleproject
│ │ ├─controller
│ │ │ ├─MemberRestController.java
│ │ │ └─ScheduleRestController.java
│ │ ├─dto
│ │ │ ├─MemberRequestDto.java
│ │ │ ├─MemberResponseDto.java
│ │ │ ├─ScheduleRequestDto.java
│ │ │ └─ScheduleResponseDto.java
│ │ ├─entity
│ │ │ ├─Member.java
│ │ │ └─Schedule.java
│ │ ├─repository
│ │ │ ├─JdbcMemberRepository.java
│ │ │ ├─JdbcScheduleRepository.java
│ │ │ ├─MemberRepository.java
│ │ │ └─ScheduleRepository.java
│ │ └─service
│ │ ├─MemberService.java
│ │ ├─MemberServiceImpl.java
│ │ ├─ScheduleService.java
│ │ └─ScheduleServiceImpl.java
│ └─resources
│ ├─application.properties
│ ├─static
│ └─templates
└─test
```

<br>
<hr>

## 💫 트러블 슈팅

- **[Velog에서 확인]()**