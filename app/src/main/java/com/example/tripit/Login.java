package com.example.tripit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            this.getSupportActionBar().hide();
        }catch (Exception e){
        }

        setContentView(R.layout.activity_login);
        TextView tvSignup=findViewById(R.id.tvsignup);
        TextView tvSignupSecond=findViewById(R.id.tvsignupsecond);
        TextView loginadmin=findViewById(R.id.loginadmin);
        Button btnLogin=findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(onClickLogin());
        loginadmin.setOnClickListener(onClickAdmin());
        tvSignup.setOnClickListener(onClickSignup());
        tvSignupSecond.setOnClickListener(onClickSignup());
    }

    private View.OnClickListener onClickAdmin(){

        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,Admin.class);
                Login.this.startActivity(intent);
            }
        };


    }

    private View.OnClickListener onClickSignup(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,Register.class);
                Login.this.startActivity(intent);
            }
        };

    }

    private View.OnClickListener onClickLogin(){

        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,Main2Activity.class);
                Login.this.startActivity(intent);
            }
        };


    }
}
