package com.example.tripit;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;

@SuppressWarnings("ALL")
public class book_homestay extends AppCompatActivity  {
    private ImageView imageView;
    private TextView name ,location , description,price;
    String name1,location1 , description1,price1;
    public static final String URL_ADD_USER = "http://192.168.43.11/Final/BookStay.php";


    HomeStay_Photos homeStayPhotos;
    private String User_ID;
    private String HSID ;
    private String CheckIN_Date;
    private String CheckOUT_Date;
    private String T1 ;
    private float daysBetween;
    private String DOB;
    private String DOB1;
    private String T3;
    private int days;

    TextView dateformat;
    TextView dateformat1;
    int day,day1;
    int month,month1;
    int year,year1;
    int Total;

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
        Total= Integer.parseInt(price1);
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

        SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");

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
                        String date =day+"/"+month+"/"+year;
                        dateformat.setText(date);

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
                        String date =day1+"/"+month1+"/"+year1;
                        dateformat1.setText(date);
                    }
                },day1,month1,year1);
                datePickerDialog.show();
            }
        });


        

    }
    public void bookNow(View view) {


        BOOK();
        sendEmail();


    }
    public void BOOK() {
        User user = SharedPrefManager.getInstance(this).getUser();
        HomeStay_Photos homeStayPhotos= SharedPrefManager.getInstance(this).getHomeStay();

        User_ID=String.valueOf(user.getId());
        HSID =homeStayPhotos.getHSID();
        CheckIN_Date=DOB;
        CheckOUT_Date=DOB1;
        T1 = homeStayPhotos.getHS_Rent();
        float Sum = Float.parseFloat(T1);
        final float T2= Sum + ((Sum)*(0.18f));

        //Toast.makeText(book_homestay.this, "sum :"+T2, Toast.LENGTH_LONG).show();

        T3=String.valueOf(T2);
        //String.valueOf(Total+((18/100)*(Total)));



        @SuppressLint("StaticFieldLeak")
        class Product extends AsyncTask<Void, Void, String> {

            private ProgressDialog pdLoading = new ProgressDialog(book_homestay.this);

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                //this method will be running on UI thread
                pdLoading.setMessage("\tLoading...");
                pdLoading.setCancelable(false);
                pdLoading.show();
            }

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();

                params.put("User_ID", User_ID);
                params.put("HSID", HSID);
                params.put("Check_IN", CheckIN_Date);
                params.put("Check_OUT", CheckOUT_Date);
                params.put("Total_Price", String.valueOf(T2));


                //returing the response
                return requestHandler.sendPostRequest(URL_ADD_USER, params);

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                pdLoading.dismiss();



                try {
                    //converting response to json object

                    JSONObject obj = new JSONObject(s);
                    //if no error in response
                    if (!obj.getBoolean("error")) {

                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                        Intent intent= new Intent(book_homestay.this,Confirmed.class);
                        startActivity(intent);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(book_homestay.this, "Exception: "+e, Toast.LENGTH_LONG).show();
                }
            }

        }

        Product prod_exec = new Product();
        prod_exec.execute();



  }



    public void sendEmail() {
        //Getting content for email
        User user = SharedPrefManager.getInstance(this).getUser();
        HomeStay_Photos homeStayPhotos= SharedPrefManager.getInstance(this).getHomeStay();
        String name = homeStayPhotos.getName();
        String location =homeStayPhotos.getHS_location();

        String email = user.getEmail();
        String subject = "BOOKING NOTIFICATION";
        String message =    "Dear Sir/mam,"+ System.lineSeparator()+
                            "Your Booking has been confirmed"+ System.lineSeparator()+
                            "Home Stay Name:"+name+ System.lineSeparator()+
                            "Home Stay Location:"+location+ System.lineSeparator()+
                            "Total amount to be paid ="+T3+ System.lineSeparator()+ System.lineSeparator()+ System.lineSeparator()+ System.lineSeparator()+



                            "nHope You enjoy your Stay with US"+ System.lineSeparator()+
                            "Regards,"+ System.lineSeparator()+
                            "Team TRIP IT"+ System.lineSeparator()+
                            "JOStechnologies Pvt. Ltd.";
        //Dear Sir/mam,\n"+"Your Booking has been confirmed \n"+"Home Stay Name:"+name+"\nHome Stay Location:"+location+"\nTotal amount to be paid ="+T3+"\n \n \nHope You enjoy your Stay with US"+"\n Regards,"+"\n Team TRIP IT"+"\nJOStechnologies Pvt. Ltd.";

        //Creating SendMail object
        JavaMailAPI sm = new JavaMailAPI(this, email, subject, message);

        //Executing sendmail to send email
        sm.execute();
    }
}
