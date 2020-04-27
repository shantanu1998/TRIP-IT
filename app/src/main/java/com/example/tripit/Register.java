package com.example.tripit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Register extends AppCompatActivity {
    private EditText name1,etEmail,etpassword;
    private Button Register;
    public static final String URL_ADD_USER = "http://192.168.43.11/Final/RegisterUser.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            this.getSupportActionBar().hide();
        }catch (Exception e){
        }

        setContentView(R.layout.activity_register);

        name1=(EditText)findViewById(R.id.editText_Name);
        etEmail=(EditText)findViewById(R.id.editTextEmail);
        etpassword=(EditText)findViewById(R.id.editTextPassword);

        Register=(Button)findViewById(R.id.Register);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_User();


            }
        });


        TextView tvLogin=findViewById(R.id.tvLogin);
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register.this.finish();
            }
        });
    }

    public void add_User(){

        final String name = name1.getText().toString();
        final String Email = etEmail.getText().toString();
        final String password = etpassword.getText().toString();


        //noinspection deprecation
        @SuppressLint("StaticFieldLeak")
        class Product extends AsyncTask<Void, Void, String> {

            private ProgressDialog pdLoading = new ProgressDialog(Register.this);

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

                params.put("USER_name", name);
                params.put("USER_email", Email);
                params.put("USER_password", password);

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
                        Intent intent = new Intent(Register.this,Login.class);
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Register.this, "Exception: "+e, Toast.LENGTH_LONG).show();
                }
            }

        }

        Product prod_exec = new Product();
        prod_exec.execute();

    }
}
