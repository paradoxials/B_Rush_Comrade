package com.example.finalb_rushadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class UpdateUser extends AppCompatActivity {
    private Button cancel, update;
    private EditText fname, mname, lname, add, bday, contact;
    private DatabaseHelper databaseHelper;
    private String FirstName, MiddleName, LastName, Address, Bday, Contact;
    private long personID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        databaseHelper = new DatabaseHelper(this);
        long userID = getUserID();
        displayData(userID);

        fname = (EditText) findViewById(R.id.updatedUserFName);
        mname = (EditText) findViewById(R.id.updatedUserMName);
        lname = (EditText) findViewById(R.id.updatedUserLName);
        add = (EditText) findViewById(R.id.updatedUserAddress);
        bday = (EditText) findViewById(R.id.updatedUserBday);
        contact = (EditText) findViewById(R.id.updatedUserContactNum);

        fname.setText(FirstName);
        mname.setText(MiddleName);
        lname.setText(LastName);
        add.setText(Address);
        bday.setText(Bday);
        contact.setText(Contact);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        bday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        UpdateUser.this, new DatePickerDialog.OnDateSetListener() {
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

        cancel = (Button) findViewById(R.id.cancelUserUpdate);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserFragment userFragment = new UserFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.layout, userFragment).addToBackStack(null).commit();
            }
        });

        update = (Button) findViewById(R.id.updateUser);
        updateUser();
    }

    private long getUserID(){
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("Name");
        String[] strSplit = name.split("-");
        long res = Long.parseLong(strSplit[0]);
        return res;
    }

    private void displayData(long userID){
        Cursor cursor = databaseHelper.getUser(userID);
        if(cursor.getCount() == 0){ Toast.makeText(UpdateUser.this, "Database is empty", Toast.LENGTH_SHORT).show(); }
        else{
            personID = cursor.getLong(cursor.getColumnIndex("PersonID"));
            Cursor user = databaseHelper.getPerson(personID);
            FirstName = user.getString(user.getColumnIndex("FirstName"));
            MiddleName = user.getString(user.getColumnIndex("MiddleName"));
            LastName = user.getString(user.getColumnIndex("LastName"));
            Address = user.getString(user.getColumnIndex("Address"));
            Bday = user.getString(user.getColumnIndex("Birthday"));
            Contact = user.getString(user.getColumnIndex("Contact_Number"));
        }
    }

    private void updateUser(){
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = String.valueOf(personID);
                boolean isUpdated = databaseHelper.updateUser(id, fname.getText().toString(), mname.getText().toString(), lname.getText().toString(),
                        add.getText().toString(), bday.getText().toString(), contact.getText().toString());
                if(isUpdated){ Toast.makeText(UpdateUser.this, "Data has been updated", Toast.LENGTH_SHORT).show();}
                else{ Toast.makeText(UpdateUser.this, "Failed to update data", Toast.LENGTH_SHORT).show(); }
            }
        });
    }
}