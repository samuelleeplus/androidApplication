package com.example.myapplication.ui.login;

import android.os.AsyncTask;
import android.util.Log;

import com.example.myapplication.data.model.RestaurantDataServer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class GetRestaurantDataFromServer {

    // returning some restaurant value

    public ArrayList<RestaurantDataServer> dataServers ;


    public ArrayList<RestaurantDataServer> resResDataServer() {
        return dataServers;
    }

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
                //Log.i("Log", "GOT THRU READING BUFFERED READER");
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
           // Log.i("Log", "INSIDE POST EXECUTE");
            try {
                JSONArray array = new JSONArray(s);
                //JSONObject obj = new JSONObject(s);
                for (int i = 0; i < array.length(); i++) {

                    RestaurantDataServer resData = new RestaurantDataServer();

                    JSONObject res = array.getJSONObject(i);
                    //Log.i("Log", "JSON Object inside for loop is: " + res.toString());
                    //storing value inside restaurant class
                    resData.setStreetAddress(res.getString("streetAddress"));
                    resData.setPrePayment(res.getBoolean("prePayment"));
                    resData.setOwner(res.getString("owner"));
                    resData.setPosts(null);
                    resData.setName(res.getString("name"));
                    resData.setId(res.getInt("id"));
                    resData.setCountry(res.getString("country"));
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


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


    }




}
