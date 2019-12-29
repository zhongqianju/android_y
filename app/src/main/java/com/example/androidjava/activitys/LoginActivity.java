package com.example.androidjava.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.androidjava.R;
import com.example.androidjava.views.InputView;

public class LoginActivity extends AppCompatActivity {
    private InputView inputView_username;
    private InputView inputView_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init(){
        inputView_username = (InputView) findViewById(R.id.login_username);
        inputView_password = (InputView) findViewById(R.id.login_password);

    }
}
