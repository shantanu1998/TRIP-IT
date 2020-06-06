package com.example.tripit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Confirmed extends AppCompatActivity {
private Button remind;
private TextView HomeStay_name,HomeStay_location,Total,rent,rent1,GST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmed);
        Bundle extras = getIntent().getExtras();

        HomeStay_name= findViewById(R.id.homestay_name_confirm);
        HomeStay_location=findViewById(R.id.address_name_confirm);
        Total=findViewById(R.id.Total_Cost);
        rent=findViewById(R.id.pernight);
        rent1=findViewById(R.id.rent1);
        GST=findViewById(R.id.GST);


        remind = (Button) findViewById(R.id.calendar);
        remind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Reminder();

            }
        });

        HomeStay_name.setText(extras.getString("TAG_name1"));
        HomeStay_location.setText(extras.getString("TAG_location1"));
        rent.setText(extras.getString("TAG_Rent1"));
        rent1.setText(extras.getString("TAG_Rent1"));
        Total.setText(extras.getString("TAG_Total1"));

        String R=extras.getString("TAG_Rent1");
        int T=Integer.parseInt(R);

        float gst=T*0.20f;
        GST.setText(String.valueOf(gst));
        //finishAffinity();
    }
    public void Reminder(){
        Intent intent=new Intent(Confirmed.this,Reminder.class);
        startActivity(intent);


    }
}
