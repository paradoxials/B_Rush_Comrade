package com.example.finalb_rushadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class BusSeats extends AppCompatActivity {
    ImageButton btnSeats;
    TextView seatView, calculatedPriceView;
    Button submitS;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_seats);
        btnSeats = (ImageButton) findViewById(R.id.getSeats);
        calculatedPriceView = (TextView) findViewById(R.id.priceView);
        seatView = (TextView) findViewById(R.id.seatsAvailableView);
        submitS = (Button) findViewById(R.id.gcashBtn);
        btnSeats.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){ openSelectSeat();}
        });

        submitS.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int countedSeats = getIntent().getIntExtra(seatSelection.EXTRA_COUNT,0);
                seatValidator(countedSeats);}
        });



        Intent intent = getIntent();
        String price = intent.getStringExtra(seatSelection.EXTRA_PRICE);
        String seats = intent.getStringExtra(seatSelection.EXTRA_SEAT);
//        int countedSeats = intent.getIntExtra(seatSelection.EXTRA_COUNT,0);
//        seatValidator(countedSeats);
        if(price != null && seats != null) {
            seatView.setText(seats);
            calculatedPriceView.setText(price + " php");
        }
//        seatValidator(countSeats); for checking upon opening bus seats
    }

    public void openSelectSeat(){
        Intent intent = new Intent(this, seatSelection.class);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        startActivity(intent);

//        selectSeatPop.setContentView(R.layout.activity_seat_selection); //too be reconstructed
//        selectSeatPop.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //too be reconstructed
//        selectSeatPop.show(); //too be reconstructed
    }

    public void seatValidator(int count){

        //assumming you ordered 3 tickets
        if(count!=3){
            Toast.makeText(this, "You ordered 3 tickets",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "G-Cash logged in Successful",Toast.LENGTH_SHORT).show();
        }
    }

    //something to put for gcash pop the log in from paolo brinquez

}