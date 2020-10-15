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
- 세번째 화면(구매 페이지)에서 연락처와 주소를 입력하고 선택한 상품 정보 및 합계를 확인한 후 버튼(구매하기) 클릭하고 최종 결제 유무 선택하여 버튼(확인 또는 취소) 
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
- 현재 화면에서 다음 화면으로 이동(첫번째 화면 → 두번째 화면 또는 두번째 화면 → 세번째 화면)할 때 선택한 상품의 정보(메뉴, 가격)를 전달하기 위해 HashMao<String, String> 이용(key값: 메뉴, value값: 가격)
- 화면 전환하고 화면간의 데이터를 전달할 때는 Intent 이용(데이터를 보내는 메서드: putExtra(), 전페이지에서 보낸 값을 받아오는 메서드: getExtra())
~~~java
Intent intent = new Intent(MainActivity.this, CartActivity.class);
intent.putExtra("hashMap", hashMap);
startActivity(intent);
~~~
- 두번째 화면에서 전달받은 HashMap에서 원하는 상품의 정보를 세번째 화면으로 전달할 때는 String 배열에 각각 넣어 
~~~java
final Intent intent = getIntent();
final HashMap<String, String> hashMap = (HashMap<String, String>)intent.getSerializableExtra("hashMap");
final String[] menu = new String[3]; final String[] price = new String[3];

Set<Map.Entry<String, String>>set = hashMap.entrySet();
Iterator<Map.Entry<String, String>>itr = set.iterator();
int i = 0;
while(itr.hasNext()){
    Map.Entry<String, String> e = (Map.Entry<String, String>)itr.next();
    textViews[i].setText("메뉴:"+e.getKey() + ",가격:"+e.getValue()+"원");
    menu[i] = e.getKey(); price[i] = e.getValue();
    checkBoxes[i].setVisibility(View.VISIBLE);
    i++;
}
~~~
- 총 합계를 구하기 위해 String 타입인 HashMap의 value들을 Integer 타입으로 바꿔서 계산
~~~java
sum += Integer.valueOf(entry.getValue()); //총 합계
~~~
- 연락처 또는 주소를 입력하지 않았을 경우에는 버튼(구매하기) 클릭하지 못하도록 하고, Toast message와 requestFocus()로 입력 유도함
~~~java
if(ed_phone.getText().toString().length() == 0){
    Toast.makeText(BuyActivity.this, "연락처를 입력하세요.", Toast.LENGTH_SHORT).show();
    ed_phone.requestFocus();
}
else if(ed_address.getText().toString().length() == 0){
    Toast.makeText(BuyActivity.this, "주소를 입력하세요.", Toast.LENGTH_SHORT).show();
    ed_address.requestFocus();
}
~~~
- 결제할 총합을 한 번 더 알려주고 버튼(확인 또는 취소) 클릭(확인 버튼 클릭하면 구매 완료 메세지 띄우고 첫번째 화면으로 이동, 취소 버튼 클릭하면 취소 메세지 띄우기)
~~~java
new AlertDialog.Builder(BuyActivity.this)
        .setTitle("결제를 진행하시겠습니까?")
        .setMessage("총 결제 금액은 " + finalResult + "원입니다.")
        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener(){
             @Override
             public void onClick(DialogInterface dialog, int which){
                 Toast.makeText(BuyActivity.this, "구매를 완료하였습니다.", Toast.LENGTH_SHORT).show();
                 Intent intent = new Intent(BuyActivity.this, MainActivity.class);
                 startActivity(intent);
             }
         })
         .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                 Toast.makeText(BuyActivity.this, "취소하였습니다.", Toast.LENGTH_SHORT).show();
             }
         })
         .show();
~~~

<br></br>

- Firebase 연동
<div>
  <img src="https://user-images.githubusercontent.com/55418359/96168081-388ae780-0f5b-11eb-8397-c92d9cb07956.PNG">
  <img src="https://user-images.githubusercontent.com/55418359/96173762-52302d00-0f63-11eb-9933-ec34b036a6cc.PNG">
</div>
<br></br>
