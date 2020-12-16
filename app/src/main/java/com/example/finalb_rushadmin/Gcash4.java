package com.example.finalb_rushadmin;


import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.Random;

public class Gcash4 extends AppCompatActivity {
    private ImageButton backButton;
    private Button button;
    private  DatabaseHelper myDbGcash;
    private String ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gcash4);
        backButton = findViewById(R.id.backButton4);
        myDbGcash = new DatabaseHelper(this);
        button = (Button)findViewById(R.id.Submit4);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToAct3();
            }
        });
        Cont();
    }
    private void Cont(){
        sendText();
        myDbGcash.insertGcashRef(ref);

    }
    private void sendText(){
        ref =""+ getRandomNumber(0,9)+ getRandomNumber(0,9) +getRandomNumber(0,9)+getRandomNumber(0,9)+getRandomNumber(0,9)+getRandomNumber(0,9);
        String From="123123";
        String msg="This is is your G-cash reference code:"+ref;
        Intent intent=new Intent(getApplicationContext(),Gcash2.class);
        PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);
        SmsManager sms=SmsManager.getDefault();
        sms.sendTextMessage(From, null, msg, pi,null);

    }
    private int getRandomNumber(int min,int max) {
        return (new Random()).nextInt((max - min) + 1) + min;
    }
    private void backToAct3(){
        Intent intent = new Intent(this, Gcash3.class);
        startActivity(intent);
    }
}