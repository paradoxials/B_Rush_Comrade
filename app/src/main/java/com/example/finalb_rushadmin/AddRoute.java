package com.example.finalb_rushadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AddRoute extends AppCompatActivity {
    private Spinner spinnerTime, spinnerDriver, spinnerBusNum;
    private DatabaseHelper databaseHelper;
    private String time, driver, bus_number;
    private EditText destination;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_route);
        databaseHelper = new DatabaseHelper(this);

        destination = (EditText) findViewById(R.id.getDestination);
        btnAdd = (Button) findViewById(R.id.addRoute);

        //static spinner
        spinnerTime = findViewById(R.id.getTime);
        String[] timeSched = getResources().getStringArray(R.array.time_schedule);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, timeSched);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTime.setAdapter(adapter);
        getSpinnerTimeValue();

        spinnerBusNum = findViewById(R.id.getBusNumber);
        String[] busNum = getResources().getStringArray(R.array.bus_number);
        ArrayAdapter adapterB = new ArrayAdapter(this, android.R.layout.simple_spinner_item, busNum);
        adapterB.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBusNum.setAdapter(adapterB);
        getBusNumberValue();

        //dynamic spinner
        spinnerDriver = findViewById(R.id.getBusDriver);
        ArrayList<String> list = databaseHelper.getDriverList();
        ArrayAdapter<String> adapterD = new ArrayAdapter<String>(this, R.layout.layout_spinner, R.id.text, list);
        spinnerDriver.setAdapter(adapterD);
        getDriverValue();

        insertRoute();
    }

    private void insertRoute(){
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long driverID = Long.parseLong(driver);
                boolean isInserted = databaseHelper.insertBusSchedule(driverID, destination.getText().toString(),
                        bus_number, time);
                if(isInserted){
                    RouteFragment routeFragment = new RouteFragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.layout, routeFragment).addToBackStack(null).commit();
                }
                else{ Toast.makeText(AddRoute.this, "Failed to insert data", Toast.LENGTH_SHORT).show(); }
            }
        });
    }
    private void getSpinnerTimeValue(){
        spinnerTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                time = (String)parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    private void getBusNumberValue(){
        spinnerBusNum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bus_number = (String)parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void getDriverValue(){
        spinnerDriver.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tempStr = (String)parent.getItemAtPosition(position);
                String[] str = tempStr.split("-");
                driver = str[0];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}