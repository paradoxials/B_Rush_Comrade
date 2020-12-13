package com.example.finalb_rushadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class AddDriver extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private Button btnAddDriver;
   // private EditText fname, mname, lname, add, bday, num;
    private TextInputEditText fname, mname, lname, add, bday, num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_driver);
        databaseHelper = new DatabaseHelper(this);
        //get the values
        fname = (TextInputEditText) findViewById(R.id.driverFName);
        mname = (TextInputEditText) findViewById(R.id.driverMName);
        lname = (TextInputEditText) findViewById(R.id.driverLName);
        add = (TextInputEditText) findViewById(R.id.driverAddress);
        bday = (TextInputEditText) findViewById(R.id.driverBday);
        num = (TextInputEditText) findViewById(R.id.driverContactNum);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        bday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AddDriver.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month += 1;
                        String date = day+"/"+month+"/"+year;
                        bday.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        //button to insert a new driver
        btnAddDriver = (Button) findViewById(R.id.addDriver);
        InsertDriverData();
    }
    private boolean validFirstName(){
        String value = fname.getText().toString();
        if(value.isEmpty()){ fname.setError("Field cannot be empty"); return false; }
        else{
            fname.setError(null);
            return true;
        }
    }
    private boolean validLastName(){
        String value = lname.getText().toString();
        if(value.isEmpty()){ lname.setError("Field cannot be empty"); return false; }
        else{
            lname.setError(null);
            return true;
        }
    }
    private boolean validAddress(){
        String value = add.getText().toString();
        if(value.isEmpty()){ add.setError("Field cannot be empty"); return false; }
        else{
            add.setError(null);
            return true;
        }
    }
    private boolean validBday(){
        String value = bday.getText().toString();
        if(value.isEmpty()){ bday.setError("Field cannot be empty"); return false; }
        else{
            bday.setError(null);
            return true;
        }
    }
    private boolean validContactNum(){
        String value = num.getText().toString();
        boolean flag = true;
        if(value.isEmpty()){ num.setError("Field cannot be empty"); flag = false; }
        else if(value.length() < 11){ num.setError("Please input 11 numbers"); flag = false; }
        else{ num.setError(null); flag = true; }
        return flag;
    }
    private void InsertDriverData()
    {
        btnAddDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validFirstName() || !validLastName() || !validAddress() || !validBday() || !validContactNum()){ return; }
                boolean isInserted = databaseHelper.insertBusDriver(fname.getText().toString(), mname.getText().toString(), lname.getText().toString(),
                        add.getText().toString(), bday.getText().toString(), num.getText().toString());
                if(isInserted){
                    DriverFragment driverFragment = new DriverFragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.layout, driverFragment).addToBackStack(null).commit();
                }
                else { Toast.makeText(AddDriver.this, "Failed to insert data", Toast.LENGTH_SHORT).show();}
            }
        });
    }
}