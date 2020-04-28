package com.example.tripit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Admin extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;
    public static final String URL_Admin_LOGIN = "http://192.168.43.11/Final/AdminLogin.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try{
            this.getSupportActionBar().hide();
        }catch (Exception e){
        }
        setContentView(R.layout.activity_admin);

        Button btn_login_admin=findViewById(R.id.btn_login_admin);
        btn_login_admin.setOnClickListener(onClickLoginAdmin());

        editTextUsername = (EditText) findViewById(R.id.adminemail);
        editTextPassword = (EditText) findViewById(R.id.adminpass);


    }

    private View.OnClickListener onClickLoginAdmin(){

        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AdminLogin();
                Intent intent=new Intent(Admin.this,Admin2.class);
                Admin.this.startActivity(intent);

            }
        };


    }
    private void AdminLogin() {
        //first getting the values
        final String username = editTextUsername.getText().toString();
        final String password = editTextPassword.getText().toString();

        //validating inputs
        if (TextUtils.isEmpty(username)) {
            editTextUsername.setError("Please enter your username");
            editTextUsername.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Please enter your password");
            editTextPassword.requestFocus();
            return;
        }

        //if everything is fine

        @SuppressLint("StaticFieldLeak")
        class UserLogin extends AsyncTask<Void, Void, String> {

            private ProgressBar progressBar;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressBar = (ProgressBar) findViewById(R.id.progressBar);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressBar.setVisibility(View.GONE);


                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(s);

                    //if no error in response
                    if (!obj.getBoolean("error")) {

                        //getting the user from the response
                        JSONObject userJson = obj.getJSONObject("Admin");

                        //creating a new user object
                        Admin_User admin_user = new Admin_User(
                                userJson.getInt("Admin_ID"),
                                userJson.getString("Admin_Name"),
                                userJson.getString("Admin_Email")

                        );
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();



                        //storing the user in shared preferences
                        SharedPrefManager.getInstance(getApplicationContext()).AdminLogin(admin_user);

                        //starting the profile activity
                        Intent intent=new Intent(Admin.this,Admin2.class);
                        Admin.this.startActivity(intent);









                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                finish();
            }

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();

                params.put("Admin_Email", username);
                params.put("Admin_password", password);

                //returing the response
                return requestHandler.sendPostRequest(URL_Admin_LOGIN, params);
            }
        }

        UserLogin ul = new UserLogin();
        ul.execute();




    }
}
