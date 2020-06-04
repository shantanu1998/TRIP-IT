package com.example.tripit;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity {

    private Button btn;


    @Override

    protected void onCreate (Bundle saveInstanceState){

        super.onCreate(saveInstanceState);

        setContentView(R.layout.activity_main2);

        BottomNavigationView bottomNavigationView=(BottomNavigationView) findViewById(R.id.bottomNav);

        bottomNavigationView.setSelectedItemId(R.id.homee);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){



                    case R.id.bookings:
                        startActivity(new Intent(getApplicationContext(),Booking.class));
                        overridePendingTransition(0,0);

                        return true;

                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(),Profile.class));
                        overridePendingTransition(0,0);

                        return true;

                    case R.id.support:
                        startActivity(new Intent(getApplicationContext(),Support.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.homee:
                        return true;




                }
                return false;
            }
        });

        btn = findViewById(R.id.btn1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Main2Activity.this,Main3Activity.class);
                startActivity(intent);
            }
        });

    }


}



