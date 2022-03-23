package com.example.c1811070__assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class chessclock extends AppCompatActivity {
    String m_name1=MainActivity.getName1();
    String m_name2=MainActivity.getName2();
    String m_time=MainActivity.getTime();

    final long startTime=Integer.parseInt(m_time)*60000;
    TextView c_time1;
    TextView c_time2;

    TextView c_name1;
    TextView c_name2;

    Button c_changeClock;
    Button c_reset;
    Button c_pause;
    Button c_startTime1;
    Button c_startTime2;

    CountDownTimer c_timer1;
    CountDownTimer c_timer2;

    boolean runClock1;
    boolean runClock2;

    long leftTime1=startTime;
    long leftTime2=startTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chessclock);

        c_time1=findViewById(R.id.l_time1);
        c_time2=findViewById(R.id.l_time2);
        c_changeClock=findViewById(R.id.l_changeClock);
        c_reset=findViewById(R.id.l_reset);
        c_pause=findViewById(R.id.l_pause);
        c_name1=findViewById(R.id.l_name1);
        c_name2=findViewById(R.id.l_name2);
        c_name1.setText(m_name1);
        c_name2.setText(m_name2);
        c_startTime1=findViewById(R.id.l_StartTime1);
        c_startTime2=findViewById(R.id.l_StartTime2);
        startTimer1();
        c_startTime1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTimer1();
                c_startTime1.setVisibility(View.INVISIBLE);
                c_startTime2.setVisibility(View.INVISIBLE);
                c_pause.setVisibility(View.VISIBLE);
            }
        });
        c_startTime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTimer2();
                c_startTime1.setVisibility(View.INVISIBLE);
                c_startTime2.setVisibility(View.INVISIBLE);
                c_pause.setVisibility(View.VISIBLE);
            }
        });
        c_changeClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(runClock1)
                {
                    pauseTimer1();
                    if(!runClock2)
                    {
                        startTimer2();
                    }
                }
                else if(runClock2)
                {
                    pauseTimer2();
                    if(!runClock1){
                        startTimer1();
                    }
                }
            }
        });
        c_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTimer();
            }
        });
        c_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseTimer();
            }
        });
        updateText1();
        updateText2();
    }

    void startTimer1(){
        c_timer1=new CountDownTimer(leftTime1,1000) {
            @Override
            public void onTick(long lastTimes) {
                leftTime1=lastTimes;
                updateText1();
            }

            @Override
            public void onFinish() {
                runClock1=false;
            }
        }.start();

        runClock1=true;
    }
    void startTimer2(){
        c_timer2=new CountDownTimer(leftTime2,1000) {
            @Override
            public void onTick(long lastTimes) {
                leftTime2=lastTimes;
                updateText2();
            }

            @Override
            public void onFinish() {
                runClock2=false;
            }
        }.start();

        runClock2=true;

    }
    void pauseTimer1(){
        c_timer1.cancel();
        runClock1=false;
    }
    void pauseTimer2(){
        c_timer2.cancel();
        runClock2=false;
    }
    void resetTimer(){
        leftTime1=startTime;
        leftTime2=startTime;
        updateText1();
        updateText2();
        pauseTimer();
    }
    void updateText1(){
        int min= (int) ((leftTime1/1000)/60);
        int sec= (int) ((leftTime1/1000)%60);

        String writeTime1=String.format(Locale.getDefault(),"%02d:%02d",min,sec);

        c_time1.setText(writeTime1);
    }
    void updateText2(){
        int min= (int) ((leftTime2/1000)/60);
        int sec= (int) ((leftTime2/1000)%60);

        String writeTime2=String.format(Locale.getDefault(),"%02d:%02d",min,sec);

        c_time2.setText(writeTime2);
    }
    void pauseTimer(){
        pauseTimer1();
        pauseTimer2();
        c_pause.setVisibility(View.INVISIBLE);
        c_startTime1.setVisibility(View.VISIBLE);
        c_startTime2.setVisibility(View.VISIBLE);
    }
}
