package com.example.project_20191604;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final HashMap<String, String> hashMap = new HashMap<String, String>();

        final CheckBox checkBox1 = (CheckBox) findViewById(R.id.cb_food1);
        final CheckBox checkBox2 = (CheckBox) findViewById(R.id.cb_food2);
        final CheckBox checkBox3 = (CheckBox) findViewById(R.id.cb_food3);
        Button b_cart = (Button) findViewById(R.id.b_cart);
        Button b_buy = (Button) findViewById(R.id.b_buy);

        b_cart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(checkBox1.isChecked()){
                    String menu = getString(R.string.menu1);
                    String price = getString(R.string.price1);
                    hashMap.put(menu, price);
                }
                if(checkBox2.isChecked()){
                    String menu = getString(R.string.menu2);
                    String price = getString(R.string.price2);
                    hashMap.put(menu, price);
                }
                if(checkBox3.isChecked()){
                    String menu = getString(R.string.menu3);
                    String price = getString(R.string.price3);
                    hashMap.put(menu, price);
                }
                //System.out.println(hashMap.values());
//                Set<Map.Entry<String, String>>set = hashMap.entrySet();
//                Iterator<Map.Entry<String, String>>itr = set.iterator();
//                while(itr.hasNext()){
//                    Map.Entry<String, String> e = (Map.Entry<String, String>)itr.next();
//                    System.out.println("메뉴:"+e.getKey() + ",가격:"+e.getValue()+"원");
//                }

                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                intent.putExtra("hashMap", hashMap);
                startActivity(intent);
            }
        });

        b_buy.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(checkBox1.isChecked()){
                    String menu = getString(R.string.menu1);
                    String price = getString(R.string.price1);
                    hashMap.put(menu, price);
                }
                if(checkBox2.isChecked()){
                    String menu = getString(R.string.menu2);
                    String price = getString(R.string.price2);
                    hashMap.put(menu, price);
                }
                if(checkBox3.isChecked()){
                    String menu = getString(R.string.menu3);
                    String price = getString(R.string.price3);
                    hashMap.put(menu, price);
                }

                Intent intent = new Intent(MainActivity.this, BuyActivity.class);
                intent.putExtra("hashMap", hashMap);
                startActivity(intent);
            }
        });
    }
}