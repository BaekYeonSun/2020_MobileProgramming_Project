package com.example.project_20191604;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class BuyActivity extends Activity {
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        final Intent intent = getIntent();

          //연락처 입력시 하이픈(-) 자동 입력
//        EditText phonenumber = (EditText) findViewById(R.id.phone);
//        phonenumber.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        TextView[] textViews = {
                (TextView) findViewById(R.id.print1),
                (TextView) findViewById(R.id.print2),
                (TextView) findViewById(R.id.print3)
        };
        TextView textView = (TextView) findViewById(R.id.print_sum);
        final EditText ed_phone = (EditText) findViewById(R.id.phone);
        final EditText ed_address = (EditText) findViewById(R.id.address);

        final HashMap<String, String> hashMap = (HashMap<String, String>)intent.getSerializableExtra("hashMap");

        Set<Map.Entry<String, String>> set = hashMap.entrySet();
        Iterator<Map.Entry<String, String>> itr = set.iterator();
        int i = 0; int result = 0;
        while(itr.hasNext()){
            Map.Entry<String, String> e = (Map.Entry<String, String>)itr.next();
            //System.out.println("메뉴:"+e.getKey() + ",가격:"+e.getValue()+"원");
            textViews[i].setText("메뉴:"+e.getKey() + ", 가격:"+e.getValue()+"원");
            result += Integer.valueOf(e.getValue());
            i++;
        }

        textView.setText("총 합계: "+result+"원입니다.");

//        new AlertDialog.Builder(this)
//                .setTitle("결제 확인")
//                .setMessage("결제를 진행하시겠습니까?")
//                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener(){
//                    @Override
//                    public void onClick(DialogInterface dialog, int which){
//                        //확인 버튼 클릭시 처리 로직
//                        Toast.makeText(BuyActivity.this, "구매가 완료되었습니다.", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        //취소 버튼 클릭시 처리 로직
//                        Toast.makeText(BuyActivity.this, "구매가 취소되었습니다.", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .show();

        Button b2_home = (Button) findViewById(R.id.b2_home);
        final int finalResult = result;
        b2_home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(ed_phone.getText().toString().length() == 0){
                    Toast.makeText(BuyActivity.this, "연락처를 입력하세요.", Toast.LENGTH_SHORT).show();
                    ed_phone.requestFocus();
                }
                else if(ed_address.getText().toString().length() == 0){
                    Toast.makeText(BuyActivity.this, "주소를 입력하세요.", Toast.LENGTH_SHORT).show();
                    ed_address.requestFocus();
                }
                else{
                    new AlertDialog.Builder(BuyActivity.this)
                            .setTitle("결제를 진행하시겠습니까?")
                            .setMessage("총 결제 금액은 " + finalResult + "원입니다.")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialog, int which){
                                    //확인 버튼 클릭시 처리 로직
                                    Toast.makeText(BuyActivity.this, "구매를 완료하였습니다.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(BuyActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //취소 버튼 클릭시 처리 로직
                                    Toast.makeText(BuyActivity.this, "취소하였습니다.", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .show();
                }
            }
        });
    }
}
