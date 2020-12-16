package com.example.finalb_rushadmin;


import android.Manifest;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.telephony.SmsManager;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;

public class Gcash1 extends AppCompatActivity {
    private Button Submit;
    private EditText MobileNo_Login1;
    private  DatabaseHelper myDbGcash;
    private String pin;
    private String Phone;
    private static final int PERMISSION_REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gcash1);
        myDbGcash = new DatabaseHelper(this);
        Submit = findViewById(R.id.Submit);
        MobileNo_Login1 = (EditText) findViewById(R.id.MobileNo_Login1);

        pin=""+getRandomNumber(0,9)+getRandomNumber(0,9)+getRandomNumber(0,9)+getRandomNumber(0,9);
        openAct2();


    }

    private void openAct2(){
        Intent intent = new Intent(this, Gcash2.class);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = login_validation(MobileNo_Login1.getText().toString());
                if(flag == true){
                    showMessage("Success", "Account found please wait for the text message \n");

                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {

                        if (checkSelfPermission(Manifest.permission.SEND_SMS)
                                == PackageManager.PERMISSION_DENIED) {

                            Log.d("permission", "permission denied to SEND_SMS - requesting it");
                            String[] permissions = {Manifest.permission.SEND_SMS};

                            requestPermissions(permissions, PERMISSION_REQUEST_CODE);

                        }
                    }
                    sendText();
                    startActivity(intent);
                }
                else {
                    showMessage("ERROR", "No Account found with that mobile number \n");
                }

            }
        });
    }
    private boolean login_validation(String mobileNo){
        Boolean flag = false;
        String Data ="";
        String Mob = mobileNo;
        Cursor login= myDbGcash.LoginGcash();

        if(login.moveToFirst()){
            do {
                Data = login.getString(1);
                if (Data.equals(Mob)) {
                    flag = true;
                    break;
                }else {
                    flag = false;
                }
            }  while (login.moveToNext());
        }
        return flag;
    }

    private void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
    private void sendText(){
        String From="123123";
        String msg="This is is your G-cash authentication code:"+pin;
        Intent intent=new Intent(getApplicationContext(),Gcash2.class);
        PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);
        SmsManager sms=SmsManager.getDefault();
        sms.sendTextMessage(From, null, msg, pi,null);

    }
    public String getPin(){
        return pin;
    }
    public String getPhone(){
        Phone=MobileNo_Login1.getText().toString();
        return Phone;
    }
    private int getRandomNumber(int min,int max) {
        return (new Random()).nextInt((max - min) + 1) + min;
    }
}