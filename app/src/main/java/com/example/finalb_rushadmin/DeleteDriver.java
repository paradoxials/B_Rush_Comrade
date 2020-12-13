package com.example.finalb_rushadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteDriver extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private EditText username, password;
    private Button submit;
    private String driverID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_driver);
        databaseHelper = new DatabaseHelper(this);
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("Name");
        String[] strSplit = name.split("-");
        driverID = strSplit[0];

        username = (EditText) findViewById(R.id.getEmailDriver);
        password = (EditText) findViewById(R.id.getPassDriver);
        submit = (Button) findViewById(R.id.deleteDriver);
        deleteDriver();
    }

    private void deleteDriver(){
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean accountExists = databaseHelper.adminAccountExists(username.getText().toString(), password.getText().toString());
                if(accountExists){
                    //Toast.makeText(DeleteDriver.this, "Account exists", Toast.LENGTH_SHORT).show();
                    boolean isDeleted = databaseHelper.deleteDriver(driverID);
                    if(isDeleted){ Toast.makeText(DeleteDriver.this, "Data has been deleted", Toast.LENGTH_SHORT).show();}
                    else{ Toast.makeText(DeleteDriver.this, "Failed to delete data", Toast.LENGTH_SHORT).show(); }
                }
                else {Toast.makeText(DeleteDriver.this, "Account does not exists", Toast.LENGTH_SHORT).show();}
            }
        });
    }
}