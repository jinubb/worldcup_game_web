# worldcup_game_web
이상형월드컵 게임 웹페이지 토이프로젝트

Spring boot Back-end API

마이크로서비스 목록

login-service
- 로그인 및 회원가입
1. 로그인 API
2. 회원가입 API

worldcup-game-service
- 이상형월드컵 게임 관리
1. 게임 목록 확인 API
2. 게임 시작 API
3. 게임 진행 API
4. 게임 종료 API(코멘트 작성)
5. 게임별 코멘트 확인 API(다른 사용자가 남긴 코멘트 정보)
6. 이미지별 정보 확인 API(승률, 우승횟수)
6. 게임 등록 API(required : 등록된 사용자의 token)

file-store-service
- 이미지 업로드 및 다운로드
1. 이미지 파일 업로드 API
2. 여러 이미지 파일 업로드 API
3. 이미지 파일 다운로드 API

DB 설계

추가예정
