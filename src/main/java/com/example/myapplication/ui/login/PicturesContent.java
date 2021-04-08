package com.example.myapplication.ui.login;

import android.net.Uri;
import android.util.Log;

import com.example.myapplication.data.model.PictureItem;
import com.google.gson.annotations.JsonAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

public class PicturesContent {

    ArrayList<PictureItem> items = new ArrayList<>();

    public ArrayList<PictureItem> getItems() {
        return items;
    }

    public void loadImages(JSONArray array){
        JSONArray arr = array ;

        items.clear();
        String path = "D:\\school\\2020Spring\\ENS491\\yelp_dataset\\yelp_photos\\photos\\";

        for(int i= 0; i<arr.length(); i++){
            try {
                JSONObject obj = arr.getJSONObject(i);
                addImage(obj);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    public void addImage (JSONObject object){
        String path = "D:\\school\\2020Spring\\ENS491\\yelp_dataset\\yelp_photos\\photos\\";
        PictureItem newItem = new PictureItem();
        try {
            //getting path for file
            path += (object.getString("photo_id") + ".jpg") ;
            Log.i("----------", "PATH IS : " + path);
            newItem.caption = object.getString("caption");
            newItem.label = object.getString("label");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        File file = new File(path);
        newItem.uri = Uri.fromFile(file);

        items.add(newItem);

        Log.i("----------", "added successfully" + newItem.toString());

    }



}
