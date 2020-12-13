package com.example.finalb_rushadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class UpdateRoute extends AppCompatActivity {
    private Spinner spinnerTime, spinnerDriver, spinnerBusNum, spinnerDes;
    private DatabaseHelper databaseHelper;
    private long schedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_route);
        databaseHelper = new DatabaseHelper(this);
        schedID = getBusSchedID();
        displayData();

        spinnerTime = findViewById(R.id.updatedTime);
        String[] timeSched = getResources().getStringArray(R.array.time_schedule);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, timeSched);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTime.setAdapter(adapter);

        spinnerBusNum = findViewById(R.id.updatedBusNumber);
        String[] busNum = getResources().getStringArray(R.array.bus_number);
        ArrayAdapter adapterB = new ArrayAdapter(this, android.R.layout.simple_spinner_item, busNum);
        adapterB.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBusNum.setAdapter(adapterB);

        spinnerDes = findViewById(R.id.updatedDestination);
        String[] desList = getResources().getStringArray(R.array.bus_destination);
        ArrayAdapter destination = new ArrayAdapter(this, android.R.layout.simple_spinner_item, desList);
        destination.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDes.setAdapter(destination);
    }

    private long getBusSchedID(){
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("Name");
        String[] strSplit = name.split("-");
        long res = Long.parseLong(strSplit[0]);
        return res;
    }
    private void displayData(){
        Cursor cursor = databaseHelper.getBusSched(schedID);
        if(cursor.getCount() == 0){ Toast.makeText(UpdateRoute.this, "Database is empty", Toast.LENGTH_SHORT).show(); }
        else{
            long busID = cursor.getLong(cursor.getColumnIndex("BusID"));
            String bus_id = String.valueOf(busID);
            Cursor bus = databaseHelper.getBus(busID);
            long routeID = bus.getLong(bus.getColumnIndex("RouteID"));
            Cursor route = databaseHelper.getRoute(routeID);
        }
    }
}