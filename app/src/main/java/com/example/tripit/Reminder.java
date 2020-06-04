package com.example.tripit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.allyants.notifyme.NotifyMe;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;
import java.util.Calendar;
import androidx.appcompat.app.AppCompatActivity;

public class Reminder extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

   Calendar now=Calendar.getInstance();
   TimePickerDialog tpd;
   DatePickerDialog dpd;
   EditText etTitle,etContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        Button btnNotify=findViewById(R.id.reminder);
        etTitle=findViewById(R.id.reminder_title);
        etContent=findViewById(R.id.reminder_content);
        Button btnCancel=findViewById(R.id.cancel);



        dpd= DatePickerDialog.newInstance(
                Reminder.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
                );


        tpd=TimePickerDialog.newInstance(
                Reminder.this,
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                now.get(Calendar.SECOND),
                false);


       btnCancel.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
              NotifyMe.cancel(getApplicationContext(),"test");
              Intent intent=new Intent(Reminder.this,Confirmed.class);
              startActivity(intent);
           }
       });

       btnNotify.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               dpd.show(getFragmentManager(),"Datepickerdialog");
           }
       });


    }




    @Override
    public void onTimeSet(TimePickerDialog view,int hourOfDay,int minute,int second){

        now.set(Calendar.YEAR,hourOfDay);
        now.set(Calendar.MONTH,minute);
        now.set(Calendar.DAY_OF_MONTH,second);


        NotifyMe notifyme=new NotifyMe.Builder(getApplicationContext())
                .title(etTitle.getText().toString())
                .content(etContent.getText().toString())
                .color(255,0,0,255)
                .led_color(255,255,255,255)
                .time(now)
                .addAction(new Intent(),"Snooze",false)
                .key("test")
                .addAction(new Intent(),"Dismiss",true,false)
                .addAction(new Intent(),"Done")
                .large_icon(R.mipmap.ic_launcher_round)
                .build();
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int month, int dayOfMonth) {
        now.set(Calendar.YEAR,year);
        now.set(Calendar.MONTH,month);
        now.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        tpd.show(getFragmentManager(),"Timepickerdialog");
    }
}
