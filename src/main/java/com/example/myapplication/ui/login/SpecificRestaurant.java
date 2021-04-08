package com.example.myapplication.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.PhotosGallery;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Random;

public class SpecificRestaurant extends AppCompatActivity {

    ImageView cardViewImage;
    TextView CardViewName, CardViewPhone, CardViewWorkingHours, CardViewStreet, CardViewCity, CardViewCountry, CardViewWebsite;
    RatingBar CardViewRatingBar;
    CheckedTextView Delivery, Wifi, TakeOut, Prepayment;

    ImageButton Gallery;
    Button CardViewReservation;
    Button seemenu ;


    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    String resName, websiteData, hoursData, ratingData, resPhone, resStreet, resCity, resCountry, resId;
    int imageData;


    String resWifi, resTakeout, resDelivery, resPrepayment;

    Random random = new Random();


    //TODO
    // make it receive the photos from previousa activity
    // then display it on listview with captions & labels

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_restaurant);
        cardViewImage = findViewById(R.id.cardViewResImage);
        CardViewName = findViewById(R.id.CarviewCompanyName);
        CardViewPhone = findViewById(R.id.CardViewPhoneNumber);
        CardViewWorkingHours = findViewById(R.id.CardViewWorkingHours);
        CardViewWebsite = findViewById(R.id.CarviewWebsite);
        CardViewStreet = findViewById(R.id.CardViewAddress);
        CardViewCity = findViewById(R.id.CardViewCity);
        CardViewCountry = findViewById(R.id.CardViewCountry);
        CardViewRatingBar = findViewById(R.id.CardviewRatingBar);
        Delivery = findViewById(R.id.CheckDelivery);
        Wifi = findViewById(R.id.checkWifi);
        TakeOut = findViewById(R.id.checkTakeout);
        Prepayment = findViewById(R.id.CheckPrePayment);
        Gallery = findViewById(R.id.RestaurantPhotosGalleryButton);
        CardViewReservation = findViewById(R.id.CardViewReservation);
        seemenu = findViewById(R.id.seeMenu);


        Delivery.setCheckMarkDrawable(null);
        Wifi.setCheckMarkDrawable(null);
        TakeOut.setCheckMarkDrawable(null);
        Prepayment.setCheckMarkDrawable(null);

        //getting restaurant data
        // and setting necessary values
        getResData();
        setResData();


        recyclerView = findViewById(R.id.recyclerViewReviews);

        // just sample reviews for demo
        ArrayList<String> reviews = new ArrayList<>();
        reviews.add("Great for watching games, ufc, and whatever else tickles yer fancy");
        reviews.add("Good chips and salsa. Loud at times. Good service. Bathrooms AWFUL. So that tanks my view on this place.");
        reviews.add("The setting and decoration here is amazing. Come check out the waterfall fountain in the middle!");
        reviews.add("Molly is definately taking a picture with Santa lols");
        reviews.add("It's true! The drunken noodles are outrageous!");
        reviews.add("Only worth a visit in the summer time, to take advantage of the huge sun soaked patio.");

        ArrayList<String> users = new ArrayList<>();
        users.add("Jack");
        users.add("Jenny");
        users.add("Fatima");
        users.add("Big Boss");
        users.add("Ahmet");
        users.add("Evan");


        adapter = new ReviewAdapter(reviews, users, this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(SpecificRestaurant.this));
        recyclerView.setAdapter(adapter);

        Gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Showing Gallery Photos", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(SpecificRestaurant.this, PhotosGallery.class);

                SpecificRestaurant.this.startActivity(i);

            }
        });

        CardViewReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Making Reservations", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(SpecificRestaurant.this, ReservationsActivity.class);
                //including information to check reservation details
                i.putExtra("restaurantName", resName) ;
                i.putExtra("restaurantWebsite", websiteData);
                i.putExtra("restaurantStreet", resStreet);
                i.putExtra("restaurantCity", resCity);
                SpecificRestaurant.this.startActivity(i);


            }
        });


        seemenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Going to Menu Page", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(SpecificRestaurant.this, MenuActivity.class);
                SpecificRestaurant.this.startActivity(i);
            }
        });


    }

    private void getResData() {

        if (getIntent().hasExtra("name") && getIntent().hasExtra("rating")
                && getIntent().hasExtra("id") && getIntent().hasExtra("website")
                && getIntent().hasExtra("workingHours")) {
            resName = getIntent().getStringExtra("name");
            websiteData = getIntent().getStringExtra("website");
            hoursData = getIntent().getStringExtra("workingHours");
            ratingData = getIntent().getStringExtra("rating");
            resId = getIntent().getStringExtra("id");
            resPhone = getIntent().getStringExtra("phoneNumber");
            resStreet = getIntent().getStringExtra("streetAddress");
            resCity = getIntent().getStringExtra("city");
            resCountry = getIntent().getStringExtra("country");
            resWifi = getIntent().getStringExtra("wifi");
            resTakeout = getIntent().getStringExtra("takeout");
            resDelivery = getIntent().getStringExtra("delivery");
            resPrepayment = getIntent().getStringExtra("prepayment");

            //NEEED TO SOLVE IMAGES PROBLEM!!!
            imageData = getIntent().getIntExtra("images", 1);
        } else {
            Toast.makeText(this, "Missing data!", Toast.LENGTH_SHORT).show();

        }

    }

    private void setResData() {

        if (Boolean.valueOf(resDelivery) == true) {
            Delivery.setCheckMarkDrawable(R.drawable.ic_check_box_black_24dp);
        }
        if (Boolean.valueOf(resWifi) == true) {
            Wifi.setCheckMarkDrawable(R.drawable.ic_check_box_black_24dp);
        }
        if (Boolean.valueOf(resTakeout) == true) {
            TakeOut.setCheckMarkDrawable(R.drawable.ic_check_box_black_24dp);
        }
        if (Boolean.valueOf(resPrepayment) == true) {
            Prepayment.setCheckMarkDrawable(R.drawable.ic_check_box_black_24dp);
        }

        int randomval = random.nextInt(11);

        if (randomval == 1) {
            Gallery.setImageResource(R.drawable.p41);
        } else if (randomval == 2) {
            Gallery.setImageResource(R.drawable.p42);
        } else if (randomval == 3) {
            Gallery.setImageResource(R.drawable.p43);
        } else if (randomval == 4) {
            Gallery.setImageResource(R.drawable.p44);
        } else if (randomval == 5) {
            Gallery.setImageResource(R.drawable.p45);
        } else if (randomval == 6) {
            Gallery.setImageResource(R.drawable.p46);
        } else if (randomval == 7) {
            Gallery.setImageResource(R.drawable.p47);
        } else if (randomval == 8) {
            Gallery.setImageResource(R.drawable.p48);
        } else if (randomval == 9) {
            Gallery.setImageResource(R.drawable.p49);
        } else if (randomval == 10) {
            Gallery.setImageResource(R.drawable.p50);
        } else {
            Gallery.setImageResource(R.drawable.p40);

        }
        cardViewImage.setImageResource(imageData);
        CardViewName.setText(resName);
        CardViewPhone.setText("Phone: " + resPhone);
        CardViewStreet.setText("Street Address: " + resStreet);
        CardViewCity.setText("City: " + resCity);
        CardViewCountry.setText("Country: " + resCountry);
        CardViewRatingBar.setRating(Float.parseFloat(ratingData));
        CardViewWebsite.setText(websiteData);
        CardViewWorkingHours.setText(hoursData);


    }

}
