package com.example.tripit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            this.getSupportActionBar().hide();
        }catch (Exception e){
        }

        setContentView(R.layout.activity_register);

        TextView tvLogin=findViewById(R.id.tvLogin);
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register.this.finish();
            }
        });
    }
}
