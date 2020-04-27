package com.example.tripit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Profile extends AppCompatActivity {
    TextView textViewId, textViewUsername, textViewEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        BottomNavigationView bottomNavigationView=(BottomNavigationView) findViewById(R.id.bottomNav);

        bottomNavigationView.setSelectedItemId(R.id.profile);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){



                    case R.id.bookings:
                        startActivity(new Intent(getApplicationContext(),Booking.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.profile:

                        return true;

                    case R.id.support:
                        startActivity(new Intent(getApplicationContext(),Support.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.homee:
                        startActivity(new Intent(getApplicationContext(),Main2Activity.class));
                        overridePendingTransition(0,0);
                        return true;




                }
                return false;
            }
        });
        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, Login.class));
        }


        textViewId = findViewById(R.id.textViewId);
        textViewUsername = findViewById(R.id.textViewUsername);
        textViewEmail = findViewById(R.id.textViewEmail);


        //getting the current user
        User user = SharedPrefManager.getInstance(this).getUser();

        //setting the values to the textviews
        textViewId.setText(String.valueOf(user.getId()));
        textViewUsername.setText(user.getUsername());
        textViewEmail.setText(user.getEmail());



        //when the user presses logout button
        //calling the logout method
        findViewById(R.id.buttonLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPrefManager.getInstance(getApplicationContext()).logout();
                Intent intent =new Intent(Profile.this,Login.class);
                finishAffinity();
                startActivity(intent);
            }
        });


    }
}
