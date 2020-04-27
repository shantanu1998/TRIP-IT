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
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Login extends AppCompatActivity {
    private EditText editTextUsername, editTextPassword;
    public static final String URL_LOGIN = "http://192.168.43.11/Final/UserLogin.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            this.getSupportActionBar().hide();
        }catch (Exception e){
        }

        setContentView(R.layout.activity_login);
        TextView tvSignup=findViewById(R.id.tvsignup);
        TextView tvSignupSecond=findViewById(R.id.tvsignupsecond);
        TextView loginadmin=findViewById(R.id.loginadmin);
        Button btnLogin=findViewById(R.id.btnLogin);
        editTextUsername = (EditText) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.password);
        btnLogin.setOnClickListener(onClickLogin());
        loginadmin.setOnClickListener(onClickAdmin());
        tvSignup.setOnClickListener(onClickSignup());
        tvSignupSecond.setOnClickListener(onClickSignup());
    }

    private View.OnClickListener onClickAdmin(){

        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,Admin.class);
                Login.this.startActivity(intent);
            }
        };


    }

    private View.OnClickListener onClickSignup(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,Register.class);
                Login.this.startActivity(intent);
            }
        };

    }

    private View.OnClickListener onClickLogin(){

        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userLogin();
                finish();

                Intent intent=new Intent(Login.this,Main2Activity.class);
                Login.this.startActivity(intent);
            }
        };


    }
    private void userLogin() {
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
                        JSONObject userJson = obj.getJSONObject("user");

                        //creating a new user object
                        User user = new User(
                                userJson.getInt("USER_id"),
                                userJson.getString("USER_name"),
                                userJson.getString("USER_email")

                        );
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();



                        //storing the user in shared preferences
                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);


                        //starting the profile activity
                        // StartUserProfileActivity();





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

                params.put("USER_email", username);
                params.put("USER_password", password);

                //returing the response
                return requestHandler.sendPostRequest(URL_LOGIN, params);
            }
        }

        UserLogin ul = new UserLogin();
        ul.execute();



    }


}

