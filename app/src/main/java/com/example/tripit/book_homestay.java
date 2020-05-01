package com.example.tripit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class book_homestay extends AppCompatActivity  {

TextView dateformat;
TextView dateformat1;
int day,day1;
int month,month1;
int year,year1;
String DOB;
    String DOB1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_homestay);

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
}
