package com.example.myapplication.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.acl.Owner;

public class Registration extends AppCompatActivity {

    TextView registration ;
    EditText firstName ;
    EditText lastName ;
    EditText email ;
    EditText password ;
    Button register ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        registration = findViewById(R.id.textView) ;
        firstName = findViewById(R.id.FirstName);
        lastName = findViewById(R.id.LastName);
        email = findViewById(R.id.username);
        password= findViewById(R.id.password) ;
        register = findViewById(R.id.button2) ;

        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkValidity() ;
            }
        });

    }

    public void ownerTaskCallClicked (){
        OwnerRegistrationJsonTask tsk = new OwnerRegistrationJsonTask();
        tsk.execute("http://10.0.2.2:8080/owners/create",
        firstName.getText().toString(),
                lastName.getText().toString(),
                email.getText().toString(),
                password.getText().toString()) ;

    }

    public void checkValidity(){
        boolean isEmailValid, isPasswordValid;

        // Check for a valid email address.
        if (email.getText().toString().isEmpty()) {
            email.setError("Please enter a email address");
            isEmailValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            email.setError("Email is not valid!");
            isEmailValid = false;
        } else  {
            isEmailValid = true;
        }

        if (password.getText().toString().isEmpty()) {
            password.setError("Please enter a password");
            isPasswordValid = false;
        } else if (password.getText().length() < 6) {
            password.setError("Password must be minimum 6 characters");
            isPasswordValid = false;
        } else  {
            isPasswordValid = true;
        }

        if ( isEmailValid && isPasswordValid) {
            ownerTaskCallClicked();
            Intent i2 = new Intent(Registration.this, LoginActivity.class);
            Toast.makeText(Registration.this, "You are now registered!", Toast.LENGTH_SHORT).show();
            Registration.this.startActivity(i2);
        }
//        return false;
    }

    class OwnerRegistrationJsonTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            //create variables
            //including JSON object

            //string builder contains
            StringBuilder stringBuilder = new StringBuilder() ;
            String url = strings[0];
            String first = strings[1];
            String last = strings[2];
            String email = strings[3];
            String pwd = strings[4];

            //
            JSONObject newUser = new JSONObject();
            try {
                newUser.put("firstName", first) ;
                newUser.put("lastName", last) ;
                newUser.put("email", email) ;
                newUser.put("password", pwd) ;
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                // open & set up connection to the url
                URL UrlOpen = new URL (url) ;
                HttpURLConnection conn = (HttpURLConnection) UrlOpen.openConnection();
                //initialize connection input values to true
                //to be able to receive
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.connect();
                //create output stream to send json object
                DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                //will send it in bytes
                out.writeBytes(newUser.toString());

                if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line = "";

                    while((line= reader.readLine())!= null){
                        stringBuilder.append(line);
                    }

                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s) {

            try {
                JSONObject createdUser = new JSONObject(s);
                String firstName = createdUser.getString("firstName");
                String lastname = createdUser.getString("lastName");
                Toast.makeText(Registration.this, "Welcome " + firstName + " " + lastname , Toast.LENGTH_SHORT).show();

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }




}
