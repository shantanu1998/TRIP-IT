package com.example.tripit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Confirmed extends AppCompatActivity {
private Button remind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmed);

         remind = (Button) findViewById(R.id.calendar);
        remind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Reminder();

            }
        });


    }
    public void Reminder(){
        Intent intent=new Intent(Confirmed.this,Reminder.class);
        startActivity(intent);
        finish();

    }
}
