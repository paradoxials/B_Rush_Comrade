package com.example.finalb_rushadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BusSeats extends AppCompatActivity {
    Dialog selectSeatPop;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_seats);

        selectSeatPop = new Dialog(this);
        button = (Button)findViewById(R.id.BookTicket);
        openGcash();
    }
    private void openGcash(){
        Intent intent = new Intent(this,Gcash1.class);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }

    public void selectSeatPop(View v){
        selectSeatPop.setContentView(R.layout.activity_seat_selection);
        selectSeatPop.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        selectSeatPop.show();
    }
}