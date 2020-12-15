package com.example.finalb_rushadmin;

import android.app.Activity;
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

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class BusSearch extends AppCompatActivity {
    private Button button;
    private EditText ediTxtResult;
    Dialog micDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        button = (Button) findViewById(R.id.searchBtn);
        ediTxtResult = (EditText) findViewById(R.id.searchBar1);
        micDialog = new Dialog(this);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openBusList();
            }
        });
    }

    public void openBusList(){
        Intent intent = new Intent(this, busList.class);
        startActivity(intent);
    }


    public void showMicPop(View v){
        micDialog.setContentView(R.layout.popupmic);
        micDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        micDialog.show();
    }

    public void getSpeech(View v){
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
        Intent intent = new Intent(BusSearch.this, busList.class);
        ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
        ediTxtResult.setText(result.get(0));
//        intent.putExtra("speech", result);
        startActivity(intent);

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
