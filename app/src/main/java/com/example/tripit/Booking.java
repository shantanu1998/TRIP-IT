package com.example.tripit;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Booking extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    private Button btn,btn2;
    private ProgressBar progressBar;
    ArrayList<HomeStay_Photos> arrayList;
    ListView lv;
    public final static String TAG_name="com.example.tripit";
    public final static String TAG_image="com.example.tripit";
    public final static String TAG_location="com.example.tripit";
    public final static String TAG_Rent="com.example.tripit";
    public final static String TAG_Description="com.example.tripit";
    public static final String URL_ADD_USER = "http://192.168.43.11/Final/RegisterUser.php";


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
        /*
        arrayList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.listView);



        runOnUiThread(new Runnable() {
            @SuppressLint("NewApi")
            @Override
            public void run() {
                new Booking.ReadJSON().execute("http://192.168.43.11/Final/fetchImage.php");
                progressBar = (ProgressBar) findViewById(R.id.progressBar);
                progressBar.setVisibility(View.VISIBLE);
            }
        });
        */
    }
    /*
    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    class ReadJSON extends AsyncTask<String, Integer, String> {


        User user= SharedPrefManager.getInstance(this).getUser();
        final String ID = String.valueOf(user.getId());
        @Override
        protected String doInBackground(String... params) {
            RequestHandler requestHandler = new RequestHandler();

            //creating request parameters
            HashMap<String, String> param = new HashMap<>();
            param.put("USER_name", ID);
            return requestHandler.sendPostRequest(URL_ADD_USER,param);
        }

        @Override
        protected void onPostExecute(String content) {
            try {

                JSONObject jsonObject = new JSONObject(content);
                final JSONArray jsonArray =  jsonObject.getJSONArray("images");

                for(int i =0;i<jsonArray.length(); i++){
                    JSONObject HomeStay_Photo_Object = jsonArray.getJSONObject(i);
                    arrayList.add(new HomeStayBooking(
                            HomeStay_Photo_Object.getString("Booking_ID"),
                            HomeStay_Photo_Object.getString("HSID"),
                            HomeStay_Photo_Object.getString("User_ID"),
                            HomeStay_Photo_Object.getString("Image"),
                            HomeStay_Photo_Object.getString("HS_Category"),
                            HomeStay_Photo_Object.getString("HS_location"),
                            HomeStay_Photo_Object.getString("HS_Rent"),
                            HomeStay_Photo_Object.getString("HS_Description"),
                    ));

                    progressBar.setVisibility(View.GONE);



                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            CustomListAdapter adapter = new CustomListAdapter(
                    getApplicationContext(), R.layout.activity_custom_list_adapter1 ,arrayList
            );

            lv.setAdapter(adapter);






        }
    }


    private static String readURL(String theUrl) {
        StringBuilder content = new StringBuilder();
        try {
            // create a url object
            URL url = new URL(theUrl);
            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();
            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }
*/
}
