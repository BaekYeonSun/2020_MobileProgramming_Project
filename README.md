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

#### Android Virtual Device
---
- Pixel 2 API 30
<br></br>

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
- Firebase
<br></br>

## 개발 환경
---
- Window OS
- Android Studio 4.0.1
<br></br>

## 코드 관련 설명
---
### xml
##### activity_main.xml, activity_cart.xml, activity_buy.xml
- Relative Layout(첫번째 화면), Linear Layout(두번째 화면), Table Layout(세번째 화면) 사용
### Java
##### MainActivity.java, CartActivity.java, BuyActivity.java, User.java
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
- 연락처 또는 주소를 입력하지 않았을 경우에는 버튼(구매하기) 클릭해도 구매하지 못하도록 하고(화면 전환 불가), Toast message와 requestFocus()로 입력 유도함
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
## 추가구현 → Firebase 연동
---
<div>
  <img src="https://user-images.githubusercontent.com/55418359/96168081-388ae780-0f5b-11eb-8397-c92d9cb07956.PNG">
  <img src="https://user-images.githubusercontent.com/55418359/96341562-18326880-10d4-11eb-9b1e-ba11ae15c3b6.PNG">
  <img src="https://user-images.githubusercontent.com/55418359/96341576-19639580-10d4-11eb-9bee-4d02915dacbc.PNG">
  <img src="https://user-images.githubusercontent.com/55418359/96337201-21b1d580-10c0-11eb-9db8-02d76800936b.PNG">
  <img src="https://user-images.githubusercontent.com/55418359/96337202-22e30280-10c0-11eb-86b4-699edba1e7d6.PNG">
</div>

<br></br>
---
### xml
- firebase에 접근하기 위해 AndroidManifest.xml에 인터넷 권한 허용
~~~
<uses-permission android:name="android.permission.INTERNET" />
~~~
### Java
- firebase에서 데이터를 추가하거나 조회하기 위해 DatabaseReferene의 인스턴스 선언
~~~java
private DatabaseReference mDatabase;
mDatabase = FirebaseDatabase.getInstance().getReference();
~~~
- 데이터를 추가하거나 조회하기 위한 클래스 생성(User.java)
~~~java
@IgnoreExtraProperties
public class User {
    public String phonenumber;
    public String address;
    public String menu;

    public User() {
        this.phonenumber = "";
        this.address = "";
        this.menu = "";
    }

    public User(String phonenumber, String address, String menu) {
        this.phonenumber = phonenumber;
        this.address = address;
        this.menu = menu;
    }

    public String getPhonenumber() { return phonenumber; }
    public void setPhonenumber(String phonenumber) { this.phonenumber = phonenumber; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getMenu() { return menu; }
    public void setMenu(String menu) { this.menu = menu; }

    @Override
    public String toString() {
        return "User{" +
                "phone-number='" + phonenumber + '\'' +
                ", address='" + address + '\'' +
                ", menu='" + menu + '\'' +
                '}';
    }
}
~~~
- 데이터 추가 함수 호출(데이터 전달할 때 연락처를 사용하여 userId 구분함)
~~~java
final EditText ed_phone = (EditText) findViewById(R.id.phone);

String getPhone = ed_phone.getText().toString();
String userId = getPhone;

writeNewUser(userId, getPhone, getAddress, getMenu);
~~~
- 데이터를 추가하기 위한 함수
~~~java
private void writeNewUser(String userId, String phonenumber, String address, String menu) {
    User user = new User(phonenumber, address, menu);

    mDatabase.child("users").child(userId).setValue(user)
            .addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(BuyActivity.this, "저장을 완료했습니다.", Toast.LENGTH_SHORT).show();
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(BuyActivity.this, "저장을 실패했습니다.", Toast.LENGTH_SHORT).show();
                }
            });
}
~~~
<br></br>
