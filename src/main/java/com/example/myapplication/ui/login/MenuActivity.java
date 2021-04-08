package com.example.myapplication.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.data.model.Menu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;

public class MenuActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    ArrayList<Menu> menuList = new ArrayList<>();
    Random random = new Random();
    Button checkout ;
    TextView menuListed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        recyclerView = findViewById(R.id.menuRecyclerView);
        checkout = findViewById(R.id.menuCheckout);
        menuListed = findViewById(R.id.menuListedBelow);

        // creating menu files
        // just testing on front-end
        String json = "" ;
        try {
            InputStream is = getAssets().open("menu_mock.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException e) {
            Log.i("Log", "INSIDE IO EXCEPTION");
            e.printStackTrace();
        }

        try {
            JSONArray array = new JSONArray(json);
            int randomVal = random.nextInt(80);
            for(int i = randomVal ; i< (randomVal + 20) ; i++){
                JSONObject obj = array.getJSONObject(i);
                Menu menu = new Menu();
                menu.setId(obj.getInt("id"));
                menu.setMenuName(obj.getString("menuName"));
                menu.setPrice(obj.getInt("price"));
                menuList.add(menu);
            }
        } catch (JSONException e) {
            Log.i("Log", "INSIDE JSON EXCEPTION");
        }

        adapter = new MenuListAdapter( menuList, MenuActivity.this  );
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MenuActivity.this));
        recyclerView.setAdapter(adapter);

        Random random = new Random();
        final Integer ranomVal = random.nextInt(150);
        final Float floatval = random.nextFloat() + ranomVal   ;

        //need to work on setting up prices
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.i("----------", "Total Price Value is :" + floatval );
                Intent i = new Intent( MenuActivity.this , CheckoutActivity.class);
                i.putExtra("price", floatval.toString());
                MenuActivity.this.startActivity(i);
            }
        });
    }
}
