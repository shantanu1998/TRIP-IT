package com.example.tripit;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {
    private ProgressBar progressBar;
    ArrayList<HomeStay_Photos> arrayList;
    ListView lv;
    public final static String TAG_name="com.example.tripit";
    public final static String TAG_image="com.example.tripit";
    public final static String TAG_location="com.example.tripit";
    public final static String TAG_Rent="com.example.tripit";
    public final static String TAG_Description="com.example.tripit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        arrayList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.listView);



        runOnUiThread(new Runnable() {
            @SuppressLint("NewApi")
            @Override
            public void run() {
                new ReadJSON().execute("http://192.168.43.11/Final/fetchImage.php");
                progressBar = (ProgressBar) findViewById(R.id.progressBar);
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }




    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    class ReadJSON extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            return readURL(params[0]);
        }

        @Override
        protected void onPostExecute(String content) {
            try {

                JSONObject jsonObject = new JSONObject(content);
                final JSONArray jsonArray =  jsonObject.getJSONArray("images");

                for(int i =0;i<jsonArray.length(); i++){
                    JSONObject HomeStay_Photo_Object = jsonArray.getJSONObject(i);
                    arrayList.add(new HomeStay_Photos(
                            HomeStay_Photo_Object.getString("HSID"),
                            HomeStay_Photo_Object.getString("HS_Image"),
                            HomeStay_Photo_Object.getString("HS_Name"),
                            HomeStay_Photo_Object.getString("Admin_ID"),
                            HomeStay_Photo_Object.getString("HS_Category"),
                            HomeStay_Photo_Object.getString("HS_location"),
                            HomeStay_Photo_Object.getString("HS_Rent"),
                            HomeStay_Photo_Object.getString("HS_Description")
                    ));

                    progressBar.setVisibility(View.GONE);



                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            CustomListAdapter adapter = new CustomListAdapter(
                    getApplicationContext(), R.layout.custom_list_layout, arrayList
            );

            lv.setAdapter(adapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {


                    HomeStay_Photos homeStay_photos =(HomeStay_Photos)lv.getItemAtPosition(position);

                    String name = homeStay_photos.getName();
                    Toast.makeText(Main3Activity.this,name, Toast.LENGTH_SHORT).show();




                    Bundle extras = new Bundle();
                    extras.putString("TAG_name",homeStay_photos.getName());
                    extras.putString("TAG_HSID",homeStay_photos.getHSID());
                    extras.putString("TAG_image",homeStay_photos.getImage());
                    extras.putString("TAG_location",homeStay_photos.getHS_location());
                    extras.putString("TAG_Rent",homeStay_photos.getHS_Rent());
                    extras.putString("TAG_Description",homeStay_photos.getHS_Description());

                    Intent intent=new Intent(Main3Activity.this,book_homestay.class);
                    intent.putExtras(extras);
                    startActivity(intent);

                }
            });




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

}