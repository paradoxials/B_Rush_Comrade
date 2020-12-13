package com.example.finalb_rushadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DeleteUser extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private EditText username, password;
    private Button submit;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);

        databaseHelper = new DatabaseHelper(this);
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("Name");
        String[] strSplit = name.split("-");
        userID = strSplit[0];

        username = (EditText) findViewById(R.id.getEmailUser);
        password = (EditText) findViewById(R.id.getPassUser);
        submit = (Button) findViewById(R.id.deleteUser);
        deleteUser();
    }

    private void deleteUser(){
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean accountExists = databaseHelper.adminAccountExists(username.getText().toString(), password.getText().toString());
                if(accountExists){
                    boolean isDeleted = databaseHelper.deleteUser(userID);
                    if(isDeleted){ String str = "User has been deleted"; showMessage(str);}
                    else{ String str = "Failed to delete user"; showMessage(str); }
                }
                else {String str = "Account does not exists"; showMessage(str);}
            }
        });
    }

    private void showMessage(String message) {
        AlertDialog.Builder alert = new AlertDialog.Builder(DeleteUser.this);
        alert.setCancelable(true);
        alert.setTitle("Is Data Deleted?");
        alert.setMessage(message);
        alert.show();
    }
}