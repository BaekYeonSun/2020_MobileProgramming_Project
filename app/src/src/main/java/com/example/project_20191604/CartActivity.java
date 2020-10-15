package com.example.project_20191604;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class CartActivity extends Activity {
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        TextView[] textViews = {
                (TextView) findViewById(R.id.print1),
                (TextView) findViewById(R.id.print2),
                (TextView) findViewById(R.id.print3)
        };
//        TextView tv_print = (TextView) findViewById(R.id.print);

        CheckBox[] checkBoxes = {
                (CheckBox) findViewById(R.id.checkBox1),
                (CheckBox) findViewById(R.id.checkBox2),
                (CheckBox) findViewById(R.id.checkBox3)
        };

        final Intent intent = getIntent();
        final HashMap<String, String> hashMap = (HashMap<String, String>)intent.getSerializableExtra("hashMap");
        final HashMap<String, String> hashMap_buy = new HashMap<String, String>();
        final HashMap<String, String> hashMap_final = new HashMap<String, String>();
        final String[] menu = new String[3]; final String[] price = new String[3];
//        String username = intent.getStringExtra("USERNAME_KEY");
//        int birthday = intent.getIntExtra("BIRTHDAY_KEY", 0);

//        tv_username.setText(username);
//        tv_birthday.setText(String.valueOf(birthday));

        //System.out.println(hashMap.values());
        Set<Map.Entry<String, String>>set = hashMap.entrySet();
        Iterator<Map.Entry<String, String>>itr = set.iterator();
        int i = 0;
        while(itr.hasNext()){
            Map.Entry<String, String> e = (Map.Entry<String, String>)itr.next();
            //System.out.println("메뉴:"+e.getKey() + ",가격:"+e.getValue()+"원");
            textViews[i].setText("메뉴:"+e.getKey() + ",가격:"+e.getValue()+"원");
            menu[i] = e.getKey(); price[i] = e.getValue();
            //hashMap_buy.put(e.getKey(), e.getValue());
            checkBoxes[i].setVisibility(View.VISIBLE);
            i++;
        }

        final CheckBox checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        final CheckBox checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        final CheckBox checkBox3 = (CheckBox) findViewById(R.id.checkBox3);

        Button b_home = (Button) findViewById(R.id.b_home);
        b_home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button b2_buy = (Button) findViewById(R.id.b2_buy);
        b2_buy.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(checkBox1.isChecked()){
                    hashMap_buy.put(menu[0], price[0]);
                    //System.out.println("메뉴:"+menu[0] + ",가격:"+price[0]+"원");
                }
                if(checkBox2.isChecked()){
                    hashMap_buy.put(menu[1], price[1]);
                    //System.out.println("메뉴:"+menu[1] + ",가격:"+price[1]+"원");
                }
                if(checkBox3.isChecked()){
                    hashMap_buy.put(menu[2], price[2]);
                    //System.out.println("메뉴:"+menu[2] + ",가격:"+price[2]+"원");
                }

                Intent intent = new Intent(CartActivity.this, BuyActivity.class);
                intent.putExtra("hashMap", hashMap_buy);
                startActivity(intent);
            }
        });
    }
}
