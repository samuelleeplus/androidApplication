package com.example.myapplication.ui.login;

import android.content.Context;
import android.content.Intent;
import android.media.Rating;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.data.model.PictureItem;
import com.example.myapplication.data.model.RestaurantData;
import com.example.myapplication.data.model.RestaurantDataServer;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {

    public RestaurantData[] list;

    public ArrayList<RestaurantDataServer> dataServers ;

    public ArrayList<RestaurantData> reslist ;

    Random random = new Random();

    PicturesContent pictures = new PicturesContent();

    int images[] ;
    Context context ;

    public RestaurantAdapter( Context context, ArrayList<RestaurantDataServer> reslist, int[] images ) {
        this.dataServers = reslist;
        this.images = images;
        this.context = context;
    }

    @NonNull
    @Override
    public RestaurantAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        // could be R.layout.activity_list_restaurants.
        View listItem = layoutInflater.inflate(R.layout.list_restaurant, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantAdapter.ViewHolder holder, final int position) {

        //for server data
        holder.name.setText(dataServers.get(position).getName());
        holder.website.setText(dataServers.get(position).getCity());
        holder.id.setText(dataServers.get(position).getId().toString());
        //get images in random order - for now - only 10 images
        holder.rating.setRating((dataServers.get(position).getRating()))  ;
        final int imageValue = random.nextInt(22);

        holder.image.setImageResource(images[imageValue]);

        holder.SpecificRestaurantLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(view.getContext(), "You clicked on restaurant: " + dataServers.get(position).getName(), Toast.LENGTH_SHORT).show();


                Intent i = new Intent( context , SpecificRestaurant.class);


                i.putExtra("name" ,dataServers.get(position).getName());
                i.putExtra("id" ,dataServers.get(position).getId().toString());
                i.putExtra("phoneNumber", dataServers.get(position).getPhoneNumber());
                i.putExtra("streetAddress" ,dataServers.get(position).getStreetAddress());
                i.putExtra("city", dataServers.get(position).getCity());
                i.putExtra("country", dataServers.get(position).getCountry());
                i.putExtra("wifi", dataServers.get(position).getWifi().toString());
                i.putExtra("takeout", dataServers.get(position).getTakeOut().toString());
                i.putExtra("delivery", dataServers.get(position).getDelivery().toString());
                i.putExtra("prepayment", dataServers.get(position).getPrePayment().toString());

                i.putExtra("rating", dataServers.get(position).getRating().toString());
                i.putExtra("website" ,dataServers.get(position).getWebsite());
                i.putExtra("workingHours" ,dataServers.get(position).getWorkingHours());
                i.putExtra("images" , images[imageValue]) ;
                //i.putExtra("Photos", photos);

                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
    return dataServers.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
            TextView id, name, website ;
            ImageView image ;

            RatingBar rating ;

            ConstraintLayout SpecificRestaurantLayout ;

            ViewHolder(View view){
                super(view);
                id = view.findViewById(R.id.restaurantId);
                name = view.findViewById(R.id.restaurantName);
                website = view.findViewById(R.id.restaurantWebsite);
                rating = view.findViewById(R.id.listRestaurantRatingBar);
                image = view.findViewById(R.id.imageView);
                SpecificRestaurantLayout = view.findViewById(R.id.listRestaurantsLayout) ;
            }

    }


}



