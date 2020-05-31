package com.example.tripit;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

public class book_homestay extends AppCompatActivity  {
    private ImageView imageView;
    private TextView name ,location , description,price;
    String name1,location1 , description1,price1;



    TextView dateformat;
    TextView dateformat1;
    int day,day1;
    int month,month1;
    int year,year1;
    String DOB;
    String DOB1;
    HomeStay_Photos homeStay_photos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_homestay);
        Bundle extras = getIntent().getExtras();
        imageView= findViewById(R.id.HomeStay_Image);
        name = findViewById(R.id.homestay_name_2);
        location=findViewById(R.id.location2);
        description= findViewById(R.id.decsription);
        price=findViewById(R.id.price_book);
        name1=extras.getString("TAG_name");
        location1=extras.getString("TAG_location");
        price1= extras.getString("TAG_Rent");
        description1= extras.getString("TAG_Description");
        name.setText(name1);
        location.setText(location1);
        price.setText(price1);
        description.setText(description1);
        String  image = extras.getString("TAG_image");
        Picasso.get().load(image).fit().into(imageView);
        dateformat=findViewById(R.id.CheckIn);
        dateformat1=findViewById(R.id.CheckOut);
        final Calendar calendar=Calendar.getInstance();
        dateformat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year=calendar.get(Calendar.YEAR);
                month=calendar.get(Calendar.MONTH);
                day=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(book_homestay.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        DOB=day+"/"+month+"/"+year;
                        dateformat.setText(DOB);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
        final Calendar calendar1=Calendar.getInstance();
        dateformat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                day1=calendar1.get(Calendar.DAY_OF_MONTH);
                month1=calendar1.get(Calendar.MONTH);
                year1=calendar1.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog=new DatePickerDialog(book_homestay.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year1, int month1, int day1) {
                        DOB1=day1+"/"+month1+"/"+year1;
                        dateformat1.setText(DOB1);
                    }
                },day1,month1,year1);
                datePickerDialog.show();
            }
        });
    }
    public void bookNow(View view){
        sendEmail();
        Intent intent=new Intent(book_homestay.this,Confirmed.class);
        Bundle extras = new Bundle();
        //extras.putString("TAG_name",);
        startActivity(intent);
    }

    public void sendEmail() {
        //Getting content for email
        User user = SharedPrefManager.getInstance(this).getUser();
        Bundle extras = getIntent().getExtras();
        name1=extras.getString("TAG_name");


        String email = user.getEmail();
        String subject = "BOOKING NOTIFICATION";
        String message = name1 ;

        //Creating SendMail object
        JavaMailAPI sm = new JavaMailAPI(this, email, subject, message);

        //Executing sendmail to send email
        sm.execute();
    }
}
