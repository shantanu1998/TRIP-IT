package com.example.tripit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Admin2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try{
            this.getSupportActionBar().hide();
        }catch (Exception e){
        }
        setContentView(R.layout.activity_admin2);


        CardView cardView=findViewById(R.id.cardview);
        cardView.setOnClickListener(onCardView());

        CardView cardView1=findViewById(R.id.cardview1);
        cardView1.setOnClickListener(onCardView1());

        Button btnhome=findViewById(R.id.btnhome);
        btnhome.setOnClickListener(btnAdminHome());

    }

    private View.OnClickListener onCardView(){

        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Admin2.this,ViewBookings.class);
                Admin2.this.startActivity(intent);
            }
        };


    }


    private View.OnClickListener onCardView1(){

        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Admin2.this,AddListings.class);
                Admin2.this.startActivity(intent);
            }
        };


    }

    private View.OnClickListener btnAdminHome(){

        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Admin2.this,Main2Activity.class);
                Admin2.this.startActivity(intent);
            }
        };


    }
}
