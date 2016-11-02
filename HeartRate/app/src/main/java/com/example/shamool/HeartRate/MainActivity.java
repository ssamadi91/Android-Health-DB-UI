package com.example.shamool.HeartRate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText et_temp, et_gender, et_hr;
    Button btn_next;
    HeartRateDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper =  new HeartRateDbHelper(this);

        et_temp = (EditText) findViewById(R.id.et_temp);
        et_gender = (EditText) findViewById(R.id.et_gender);
        et_hr = (EditText) findViewById(R.id.et_hr);
        btn_next = (Button) findViewById(R.id.btn_next);


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserData userData = new UserData();

                if (!et_temp.getText().toString().isEmpty()) {
                    userData.body_temp = et_temp.getText().toString();
                } else {
                   // userData.body_temp = "";
                }
                if (!et_gender.getText().toString().isEmpty()) {
                    userData.gender = et_gender.getText().toString();
                } else {
                   // userData.gender = "";
                }

                if (!et_hr.getText().toString().isEmpty()) {
                    userData.heart_rate = et_hr.getText().toString();
                } else {
                   // userData.heart_rate = "";
                }

                dbHelper.insertUserDetail(userData);

                Intent intent=new Intent(MainActivity.this,UserDetailList.class);
                startActivity(intent);
            }
        });

    }
}