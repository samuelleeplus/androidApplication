package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.example.myapplication.ui.login.ListRestaurants;
import com.example.myapplication.ui.login.PhotoGalleryAdapter;
import com.example.myapplication.ui.login.RestaurantAdapter;

public class PhotosGallery extends AppCompatActivity {

    RecyclerView recyclerView ;
    RecyclerView.Adapter adapter;


    //sample photogallery usage for demo

    int sampleRestaurantImages[] = {R.drawable.picture1 , R.drawable.pic2, R.drawable.pic3, R.drawable.pic4, R.drawable.pic5,
            R.drawable.p6, R.drawable.p7, R.drawable.p8, R.drawable.p9, R.drawable.p10 ,
            R.drawable.p11 , R.drawable.p12, R.drawable.p13, R.drawable.p14, R.drawable.p15,
            R.drawable.p16, R.drawable.p17, R.drawable.p18, R.drawable.p19, R.drawable.p20,
            R.drawable.p21 , R.drawable.p22, R.drawable.p23, R.drawable.p24, R.drawable.p25,
            R.drawable.p26, R.drawable.p27, R.drawable.p28, R.drawable.p29, R.drawable.p30,
            R.drawable.p31 , R.drawable.p32, R.drawable.p33, R.drawable.p34, R.drawable.p35,
            R.drawable.p36, R.drawable.p37, R.drawable.p38, R.drawable.p39, R.drawable.p40 };

    String captions [] = { "Clean and simple interior",
            "12oz. Boneless Ribeye | Grilled to perfection with your choice of spice, sauce, and toppers.",
            "The Most Yummy Tarts: Chantilly, Guava, Salted Caramel Coffee, and Ube",
            "Dynamic Duo!  Great dance music!",
            "Shrimp, mussels, crawfish, crab claws, potatoes, corn,",
            "Surf and turf with 1.5lb lobster and 7oz Filet Mignon. Delicious and lobster was cracked table-side!",
            "Birthday girls as the center of attention.",
            "Double Crunch Bone-In Wings",
            "Freddy's Single California Style Burger and Italian Beef Combo",
            "If you come here, get ready to do a lot of waiting.",
            //11-20
            "Line thick for that smoked meat.. Haha",
            "Decor is modern !",
            "For private party Blakbird",
            "Entrance",
            "Nice atmosphere and vibe.",
            "Chickens karage (sp?) on rice",
            "Dining area!",
            "Lobster balls",
            "Iberico Tataki - yum",
            "Inside ordering counter",
            //21-30
            "Should've taken a pic of the food when it came, but we ate too fast.",
            "La Gourmandine's sign - I shot this photo a little over a year ago :)",
            "Tomaydo Mozz salad",
            "Turkey burger",
            "Signature sticky toffee pudding",
            "Y a du monde!",
            "Grilled chicken tacos",
            "Front of store! They make milkshakes too! Yum !",
            "Small seats at the bar",
            "So nice!",
            //31
            "Very clean location",
            "Sizzling Sisig",
            "Hard at work making bubble tea!",
            "Hokkaido Boba Tea : best one yet!",
            "Our Wine Bar is pouring some of the most celebrated wines in the world.",
            "Main/front bar",
            "Bun bo hue",
            "Hyde Lounge",
            "Steak & Salmon Bento",
            "Spectacular \"set\"- gets you in the mood for dining outside in a Paris Cafe, when you're neither outside or in Paris!"

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos_gallery);

        recyclerView = findViewById(R.id.recyclerviewGallery);


        adapter = new PhotoGalleryAdapter(sampleRestaurantImages, PhotosGallery.this, captions );

        recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(PhotosGallery.this));
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);




    }
}
