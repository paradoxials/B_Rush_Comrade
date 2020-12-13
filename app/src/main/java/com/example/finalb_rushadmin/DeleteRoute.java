package com.example.finalb_rushadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteRoute extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private EditText username, password;
    private Button submit;
    private String schedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_route);

        databaseHelper = new DatabaseHelper(this);
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("Name");
        String[] strSplit = name.split("-");
        schedID = strSplit[0];

        username = (EditText) findViewById(R.id.getEmailRoute);
        password = (EditText) findViewById(R.id.getPassRoute);
        submit = (Button) findViewById(R.id.deleteRoute);
        deleteRoute();
    }

    private void deleteRoute(){
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean accountExists = databaseHelper.adminAccountExists(username.getText().toString(), password.getText().toString());
                if(accountExists){
                    //Toast.makeText(DeleteDriver.this, "Account exists", Toast.LENGTH_SHORT).show();
                    boolean isDeleted = databaseHelper.deleteBusSched(schedID);
                    if(isDeleted){ Toast.makeText(DeleteRoute.this, "Data has been deleted", Toast.LENGTH_SHORT).show();}
                    else{ Toast.makeText(DeleteRoute.this, "Failed to delete data", Toast.LENGTH_SHORT).show(); }
                }
                else {Toast.makeText(DeleteRoute.this, "Account does not exists", Toast.LENGTH_SHORT).show();}
            }
        });
    }
}