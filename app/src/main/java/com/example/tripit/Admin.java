package com.example.tripit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try{
            this.getSupportActionBar().hide();
        }catch (Exception e){
        }
        setContentView(R.layout.activity_admin);

        Button btn_login_admin=findViewById(R.id.btn_login_admin);
        btn_login_admin.setOnClickListener(onClickLoginAdmin());


    }

    private View.OnClickListener onClickLoginAdmin(){

        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Admin.this,Admin2.class);
                Admin.this.startActivity(intent);
            }
        };


    }
}
