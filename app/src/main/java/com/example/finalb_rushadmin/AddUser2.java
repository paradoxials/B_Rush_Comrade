package com.example.finalb_rushadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class AddUser2 extends AppCompatActivity {
    private Button btnAddUser;
    private EditText add, bday, num;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user2);
        //get values
        add = (EditText) findViewById(R.id.getUserAddress);
        bday = (EditText) findViewById(R.id.getUserBday);
        num = (EditText) findViewById(R.id.getUserNum);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        bday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AddUser2.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month += 1;
                        String date = day+"/"+month+"/"+year;
                        bday.setText(date);
                    }
                },year, month, day);
                datePickerDialog.show();
            }
        });

        //button to insert data in table
        btnAddUser = (Button) findViewById(R.id.addUser);
        databaseHelper = new DatabaseHelper(this);
        InsertUser();
    }
    public void InsertUser()
    {
        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = getIntent().getExtras();
                String Fname = bundle.getString("First");
                String Mname = bundle.getString("Middle");
                String Lname = bundle.getString("Last");
                String Username = bundle.getString("User");
                String Pass = bundle.getString("Pass");

                boolean isInserted = databaseHelper.insertUser(Fname, Mname, Lname, add.getText().toString(), bday.getText().toString(),
                        num.getText().toString(), Username, Pass);
                if(isInserted) { Toast.makeText(AddUser2.this, "Data inserted", Toast.LENGTH_SHORT).show(); }
                else {Toast.makeText(AddUser2.this, "Failed to insert data", Toast.LENGTH_SHORT).show();}
            }
        });
    }
}