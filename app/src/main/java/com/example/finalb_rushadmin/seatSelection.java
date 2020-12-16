package com.example.finalb_rushadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;

public class seatSelection extends AppCompatActivity {
    // ArrayList<String> seatCount = new ArrayList<String>;
    //TextView selectedSeats;
    ImageButton getChecked;
    public static int count,price,ticketsLeft;
    public static final String EXTRA_PRICE = "com.team_brush.brush.EXTRA_PRICE";
    public static final String EXTRA_SEAT = "com.team_brush.brush.EXTRA_SEAT";
    public static final String EXTRA_COUNT = "com.team_brush.brush.EXTRA_COUNT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_selection);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // selectedSeats = (TextView) findViewById(R.id.selectedSeats);
        getChecked = (ImageButton) findViewById(R.id.seatsChecked);
        getChecked.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){ submitSeats();}
        });
        CheckBox checkbox1 = (CheckBox) findViewById(R.id.checkBox1);
        CheckBox checkbox2 = (CheckBox) findViewById(R.id.checkBox2);
        CheckBox checkbox3 = (CheckBox) findViewById(R.id.checkBox3);
        CheckBox checkbox4 = (CheckBox) findViewById(R.id.checkBox4);
        CheckBox checkbox5 = (CheckBox) findViewById(R.id.checkBox5);
        CheckBox checkbox6 = (CheckBox) findViewById(R.id.checkBox6);
        CheckBox checkbox7 = (CheckBox) findViewById(R.id.checkBox7);
        CheckBox checkbox8 = (CheckBox) findViewById(R.id.checkBox8);
        CheckBox checkbox9 = (CheckBox) findViewById(R.id.checkBox9);
        CheckBox checkbox10 = (CheckBox) findViewById(R.id.checkBox10);
        CheckBox checkbox11 = (CheckBox) findViewById(R.id.checkBox11);
        CheckBox checkbox12 = (CheckBox) findViewById(R.id.checkBox12);
        CheckBox checkbox13 = (CheckBox) findViewById(R.id.checkBox13);
        CheckBox checkbox14 = (CheckBox) findViewById(R.id.checkBox14);
        CheckBox checkbox15 = (CheckBox) findViewById(R.id.checkBox15);
        CheckBox checkbox16 = (CheckBox) findViewById(R.id.checkBox16);
        CheckBox checkbox17 = (CheckBox) findViewById(R.id.checkBox17);
        CheckBox checkbox18 = (CheckBox) findViewById(R.id.checkBox18);
        CheckBox checkbox19 = (CheckBox) findViewById(R.id.checkBox19);
        CheckBox checkbox20 = (CheckBox) findViewById(R.id.checkBox20);
        CheckBox checkbox21 = (CheckBox) findViewById(R.id.checkBox21);
        CheckBox checkbox22 = (CheckBox) findViewById(R.id.checkBox22);
        CheckBox checkbox23 = (CheckBox) findViewById(R.id.checkBox23);
        CheckBox checkbox24 = (CheckBox) findViewById(R.id.checkBox24);
        CheckBox checkbox25 = (CheckBox) findViewById(R.id.checkBox25);
        CheckBox checkbox26 = (CheckBox) findViewById(R.id.checkBox26);
        CheckBox checkbox27 = (CheckBox) findViewById(R.id.checkBox27);
        CheckBox checkbox28 = (CheckBox) findViewById(R.id.checkBox28);
        CheckBox checkbox29 = (CheckBox) findViewById(R.id.checkBox29);
        CheckBox checkbox30 = (CheckBox) findViewById(R.id.checkBox30);
        CheckBox checkbox31 = (CheckBox) findViewById(R.id.checkBox31);
        CheckBox checkbox32 = (CheckBox) findViewById(R.id.checkBox32);
        CheckBox checkbox33 = (CheckBox) findViewById(R.id.checkBox33);
        CheckBox checkbox34 = (CheckBox) findViewById(R.id.checkBox34);
        CheckBox checkbox35 = (CheckBox) findViewById(R.id.checkBox35);
        CheckBox checkbox36 = (CheckBox) findViewById(R.id.checkBox36);
        CheckBox checkbox37 = (CheckBox) findViewById(R.id.checkBox37);
        CheckBox checkbox38 = (CheckBox) findViewById(R.id.checkBox38);
        CheckBox checkbox39 = (CheckBox) findViewById(R.id.checkBox39);
        CheckBox checkbox40 = (CheckBox) findViewById(R.id.checkBox40);
        CheckBox checkbox41 = (CheckBox) findViewById(R.id.checkBox41);
        CheckBox checkbox42 = (CheckBox) findViewById(R.id.checkBox42);
        CheckBox checkbox43 = (CheckBox) findViewById(R.id.checkBox43);
        CheckBox checkbox44 = (CheckBox) findViewById(R.id.checkBox44);
        CheckBox checkbox45 = (CheckBox) findViewById(R.id.checkBox45);
        CheckBox checkbox46 = (CheckBox) findViewById(R.id.checkBox46);
        CheckBox checkbox47 = (CheckBox) findViewById(R.id.checkBox47);


        CompoundButton.OnCheckedChangeListener checkListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                countCheck(isChecked);
                //Log.i("MAIN", count + "");
            }
        };

        checkbox1.setOnCheckedChangeListener(checkListener);
        checkbox2.setOnCheckedChangeListener(checkListener);
        checkbox3.setOnCheckedChangeListener(checkListener);
        checkbox4.setOnCheckedChangeListener(checkListener);
        checkbox5.setOnCheckedChangeListener(checkListener);
        checkbox6.setOnCheckedChangeListener(checkListener);
        checkbox7.setOnCheckedChangeListener(checkListener);
        checkbox8.setOnCheckedChangeListener(checkListener);
        checkbox9.setOnCheckedChangeListener(checkListener);
        checkbox10.setOnCheckedChangeListener(checkListener);
        checkbox11.setOnCheckedChangeListener(checkListener);
        checkbox12.setOnCheckedChangeListener(checkListener);
        checkbox13.setOnCheckedChangeListener(checkListener);
        checkbox14.setOnCheckedChangeListener(checkListener);
        checkbox15.setOnCheckedChangeListener(checkListener);
        checkbox16.setOnCheckedChangeListener(checkListener);
        checkbox17.setOnCheckedChangeListener(checkListener);
        checkbox18.setOnCheckedChangeListener(checkListener);
        checkbox19.setOnCheckedChangeListener(checkListener);
        checkbox20.setOnCheckedChangeListener(checkListener);
        checkbox21.setOnCheckedChangeListener(checkListener);
        checkbox22.setOnCheckedChangeListener(checkListener);
        checkbox23.setOnCheckedChangeListener(checkListener);
        checkbox24.setOnCheckedChangeListener(checkListener);
        checkbox25.setOnCheckedChangeListener(checkListener);
        checkbox26.setOnCheckedChangeListener(checkListener);
        checkbox27.setOnCheckedChangeListener(checkListener);
        checkbox28.setOnCheckedChangeListener(checkListener);
        checkbox29.setOnCheckedChangeListener(checkListener);
        checkbox30.setOnCheckedChangeListener(checkListener);
        checkbox31.setOnCheckedChangeListener(checkListener);
        checkbox32.setOnCheckedChangeListener(checkListener);
        checkbox33.setOnCheckedChangeListener(checkListener);
        checkbox34.setOnCheckedChangeListener(checkListener);
        checkbox35.setOnCheckedChangeListener(checkListener);
        checkbox36.setOnCheckedChangeListener(checkListener);
        checkbox37.setOnCheckedChangeListener(checkListener);
        checkbox38.setOnCheckedChangeListener(checkListener);
        checkbox39.setOnCheckedChangeListener(checkListener);
        checkbox40.setOnCheckedChangeListener(checkListener);
        checkbox41.setOnCheckedChangeListener(checkListener);
        checkbox42.setOnCheckedChangeListener(checkListener);
        checkbox43.setOnCheckedChangeListener(checkListener);
        checkbox44.setOnCheckedChangeListener(checkListener);
        checkbox45.setOnCheckedChangeListener(checkListener);
        checkbox46.setOnCheckedChangeListener(checkListener);
        checkbox47.setOnCheckedChangeListener(checkListener);
    }

    public void submitSeats(){
        price = count*25;
        ticketsLeft = 47-count;
        int countedSeats = count;
        String priceText = Integer.toString(price);
        String ticketsText = Integer.toString(ticketsLeft);
//      TextView price = (TextView) findViewById(R.id.priceView);
//      TextView seatsAvailable = (TextView) findViewById(R.id.seatsAvailableView);
        Intent intent = new Intent(this, BusSeats.class);
        intent.putExtra(EXTRA_PRICE, priceText);
        intent.putExtra(EXTRA_SEAT, ticketsText);
        intent.putExtra(EXTRA_COUNT, countedSeats);
        startActivity(intent);


    }

    public void countCheck(boolean isChecked){
        count += isChecked ? 1: -1;
    }

//    void backgroundActivity(Activity BusSeats){
//        BusSeats.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//    }


}