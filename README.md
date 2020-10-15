# 연선반점
### 20191604 백연선
---
<div>
  <img src="https://user-images.githubusercontent.com/55418359/96143554-29e20780-0f3e-11eb-8401-b74c8b62f8b0.PNG" width="250">
  <img src="https://user-images.githubusercontent.com/55418359/96143551-28b0da80-0f3e-11eb-832f-8b73ee7411a6.PNG" width="250">
  <img src="https://user-images.githubusercontent.com/55418359/96143549-28b0da80-0f3e-11eb-8440-63342c1cdea7.PNG" width="250">
  <img src="https://user-images.githubusercontent.com/55418359/96143552-29497100-0f3e-11eb-9ebd-040207e38d04.PNG" width="250">
  <img src="https://user-images.githubusercontent.com/55418359/96143543-26e71700-0f3e-11eb-9819-bbd209ae26fb.PNG" width="250">
  <img src="https://user-images.githubusercontent.com/55418359/96143547-28184400-0f3e-11eb-82b8-d89d328ac82b.PNG" width="250">
</div>

## 설명
---
- 첫번째 화면(상품 선택 페이지)에서 상품(짜장면, 짬뽕, 탕수육)의 정보(메뉴, 가격)를 보고 원하는 상품을 선택한 후 버튼(장바구니 또는 구매하기) 클릭하여 화면 이동
- 두번째 화면(장바구니 페이지)에서 최종 결제를 위한 상품을 선택한 후 버튼(돌아가기 또는 구매하기) 클릭하여 화면 이동
- 세번째 화면(구매 페이지)에서 연락처와 주소를 입력하고 선택한 상품 정보 및 합계를 확인한 후 버튼(구매하기) 클릭
<br></br>

## 사용 기술
---
- xml
- Java
<br></br>

## 개발 환경
---
- Window OS
- Android Studio 4.0.1
<br></br>

## 코드 관련 설명
---
### xml
- Relative Layout(첫번째 화면), Linear Layout(두번째 화면), Table Layout(세번째 화면) 사용
### Java
- 현재 화면에서 다음 화면으로 이동(첫번째 화면 → 두번째 화면 또는 두번째 화면 → 세번째 화면)할 때 선택한 상품의 정보(메뉴, 가격)를 전달하기 위해 HashMao<String, String>
<br></br>
