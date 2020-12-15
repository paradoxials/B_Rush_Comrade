package com.example.finalb_rushadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class busList extends AppCompatActivity {
    Dialog listDialog;
    Dialog micDialog;
    EditText searchBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_list);
        listDialog = new Dialog(this);
        micDialog = new Dialog(this);
        searchBtn2 = (EditText) findViewById(R.id.searchBar2);

    }

    public void showBusList(View v){
        Button modalBack;
        Button modalBook;
        listDialog.setContentView(R.layout.activity_pop_up_list);
        listDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        listDialog.show();
        modalBack = (Button) listDialog.findViewById(R.id.modalBtnBack);
        modalBook = (Button) listDialog.findViewById(R.id.modalBtnBook);
        modalBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                listDialog.dismiss();
            }
        });
        modalBook.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openBusSeats();
            }
        });

    }

    public void showMicPop(View v){
        micDialog.setContentView(R.layout.popupmic);
        micDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        micDialog.show();
    }

    public void openBusSeats(){
        Intent intent = new Intent(this, BusSeats.class);
        startActivity(intent);
    }

    public void getSpeech2(View v){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        // intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 10);
        }
        else{
            Toast.makeText(this, "Your device does not support Speech input", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
//        Intent intent = new Intent(search.this, busList.class);
        ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
        searchBtn2.setText(result.get(0));
//        intent.putExtra("speech", result);
//        startActivity(intent);

//        switch (resultCode){
//            case 10:
//                if(resultCode == RESULT_OK && data != null){
//                   ediTxtResult.setText(result.get(10));
//                }
//                break;
//
//        }
    }
}