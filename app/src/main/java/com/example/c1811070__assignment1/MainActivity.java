package com.example.c1811070__assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class MainActivity extends AppCompatActivity {


    EditText c_name1;
    EditText c_name2;
    EditText c_time;

    Button c_send;
    static String sendName1;
    static String sendName2;
    static String sendTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        c_name1=findViewById(R.id.l_PlayerName1);
        c_name2= findViewById(R.id.l_PlayerName2);
        c_time= findViewById(R.id.l_Time);
        c_send= findViewById(R.id.send);

        getInput();

    }
    void getInput(){
        c_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendName1=c_name1.getText().toString();
                sendName2=c_name2.getText().toString();
                sendTime=c_time.getText().toString();
                Intent intent = new Intent(MainActivity.this,chessclock.class);
                startActivity(intent);
            }
        });
    }

    static String getName1(){
        return sendName1;
    }
    static String getName2(){
        return sendName2;
    }
    static String getTime(){
        return sendTime;
    }


}