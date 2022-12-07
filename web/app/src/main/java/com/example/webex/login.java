package com.example.webex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;

public class login extends AppCompatActivity {
    private DBHelper dbHelper  = new DBHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText text_login = (EditText) findViewById(R.id.text_login);
        EditText text_password = (EditText) findViewById(R.id.text_password);

        Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    dbHelper.authorize(text_login.getText().toString(),
                            text_password.getText().toString(),
                            ()->{openPersonalAccount(); return null;},
                            ()->{loginFailed(); return null;}
                            );
                } catch (UnsupportedEncodingException e){
                    e.printStackTrace();
                }
            }
        });

        Button web = (Button) findViewById(R.id.web);
        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, browser.class);
                startActivity(intent);
            }
        });
    }

    private void openPersonalAccount(){
        Intent intent = new Intent(login.this, personal_account.class);
        startActivity(intent);
    }
    private void loginFailed(){
        Toast.makeText(this, "Неверный логин или пароль", Toast.LENGTH_SHORT).show();
    }
}