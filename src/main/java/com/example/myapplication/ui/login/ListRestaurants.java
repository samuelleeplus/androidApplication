package com.example.myapplication.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.data.model.RestaurantData;
import com.example.myapplication.data.model.RestaurantDataServer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListRestaurants extends AppCompatActivity  {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    int sampleRestaurantImages[] = {R.drawable.lotteria, R.drawable.shake, R.drawable.isaac_logo, R.drawable.br, R.drawable.momstouch,
            R.drawable.bolat, R.drawable.hooters, R.drawable.lucias, R.drawable.palm, R.drawable.vector,
            R.drawable.p60 , R.drawable.p62, R.drawable.p63, R.drawable.p64, R.drawable.p65,
            R.drawable.p66, R.drawable.p67, R.drawable.p68, R.drawable.p69, R.drawable.p70,
            R.drawable.p61, R.drawable.p71, R.drawable.p72
    };
    public ArrayList<RestaurantDataServer> dataServers = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_restaurants);
        //getting restaurant data from server
        getResData();
    }

    // HTTP GET & send restaurant list to adapter
    public void getResData(){

        getRestaurantsData tsk = new getRestaurantsData();
        tsk.execute("http://10.0.2.2:8080/restaurants/allRestaurants");

    }

    class getRestaurantsData extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            //create variables
            //including JSON object
            //string builder contains
            StringBuilder stringBuilder = new StringBuilder();
            String url = strings[0];

            try {
                // open & set up connection to the url
                URL UrlOpen = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) UrlOpen.openConnection();
                //initialize connection input values to true
                //to be able to receive
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }


            } catch (MalformedURLException e) {
                Log.i("Log", "INSIDE MALFORMED EXCEPTION");

                e.printStackTrace();
            } catch (IOException e) {

                Log.i("Log", "INSIDE IO EXCEPTION");

                e.printStackTrace();
            }

            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s) {

            try {
                JSONArray array = new JSONArray(s);

                //just sampling 20 restaurants for demo
                for (int i = 0; i <20 ; i++) {
                    RestaurantDataServer resData = new RestaurantDataServer();
                    JSONObject res = array.getJSONObject(i);
                    //storing value inside restaurant class
                    resData.setStreetAddress(res.getString("streetAddress"));
                    resData.setPrePayment(res.getBoolean("prePayment"));
                    resData.setOwner(res.getString("owner"));
                    resData.setPosts(null);
                    resData.setName(res.getString("name"));
                    resData.setId(res.getInt("id"));
                    resData.setCountry(res.getString("country"));
                    resData.setCity(res.getString("city"));
                    resData.setPhoneNumber(res.getString("phoneNumber"));
                    resData.setTakeOut(res.getBoolean("takeOut"));
                    resData.setDelivery(res.getBoolean("delivery"));
                    resData.setWebsite(res.getString("website"));
                    resData.setRating(res.getInt("rating"));
                    resData.setWorkingHours(res.getString("workingHours"));
                    resData.setWifi(res.getBoolean("wifi"));

                    //adding to arraylist
                    dataServers.add(resData);
                }


                recyclerView = findViewById(R.id.recyclerView);
                adapter = new RestaurantAdapter(ListRestaurants.this, dataServers, sampleRestaurantImages);

                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(ListRestaurants.this));
                recyclerView.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}

