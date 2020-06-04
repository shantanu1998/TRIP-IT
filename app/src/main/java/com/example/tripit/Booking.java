package com.example.tripit;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Booking extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    private Button btn,btn2;


    @Override
    protected void onCreate(Bundle saveInstanceState){


        super.onCreate(saveInstanceState);


        setContentView(R.layout.activity_booking);


        BottomNavigationView bottomNavigationView=(BottomNavigationView) findViewById(R.id.bottomNav);


        bottomNavigationView.setSelectedItemId(R.id.bookings);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){

                    case R.id.bookings:
                        return true;



                    case R.id.homee:

                        startActivity(new Intent(getApplicationContext(),Main2Activity.class));
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

                }
                return false;
            }
        });

      //  btn = findViewById(R.id.ButtonTEST);
       /* btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Booking.this,Main3Activity.class);
                startActivity(intent);
            }
        });
*/

     //   btn2=findViewById(R.id.Button);
       //btn2.setOnClickListener(new View.OnClickListener() {
         //   @Override
           // public void onClick(View v){

             //   Intent intent=new Intent(Booking.this,book_homestay.class);
               // startActivity(intent);
            //}
        //});





    }
}
