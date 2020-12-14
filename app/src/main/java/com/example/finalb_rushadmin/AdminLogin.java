package com.example.finalb_rushadmin;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AdminLogin extends AppCompatActivity {
    private Button loginBtn;
    private DatabaseHelper databaseHelper;
    private TextInputEditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        databaseHelper = new DatabaseHelper(this);

        username = (TextInputEditText) findViewById(R.id.getAdminUsername);
        password = (TextInputEditText) findViewById(R.id.getAdminPassword);

        loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openDashboard();
            }

        });
    }

    public void openDashboard(){
        boolean isValid = databaseHelper.adminAccountExists(username.getText().toString(), password.getText().toString());
        if(isValid){
            Intent intent = new Intent(AdminLogin.this, NavDashboard.class);
            startActivity(intent);
        }
        else{
          Toast.makeText(AdminLogin.this, "Account does not exist", Toast.LENGTH_SHORT).show();}
    }

    public void showMessage(String str1, String str2){
        AlertDialog.Builder alert = new AlertDialog.Builder(AdminLogin.this);
        alert.setCancelable(true);
        alert.setTitle("Is Data Deleted?");
        alert.setMessage(str1+str2);
        alert.show();
    }
}
