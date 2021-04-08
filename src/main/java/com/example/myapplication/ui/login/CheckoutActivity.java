package com.example.myapplication.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

public class CheckoutActivity extends AppCompatActivity {

    TextView form, totalprice ;
    EditText first, last, email, phone, street, city, country, postal;

    Button submit ;

    String priceVal ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        form = findViewById(R.id.CheckoutText);
        totalprice = findViewById(R.id.CheckoutTotalPrice);
        first = findViewById(R.id.CheckoutFirst);
        last = findViewById(R.id.CheckoutLast);
        email = findViewById(R.id.CheckoutEmail);
        phone = findViewById(R.id.CheckoutPhone);
        street = findViewById(R.id.CheckoutStreet);
        city = findViewById(R.id.CheckoutCity);
        country = findViewById(R.id.CheckoutCountry);
        postal = findViewById(R.id.CheckoutPostal);
        submit = findViewById(R.id.CheckoutComplete);

        if(getIntent().hasExtra("price")){
            priceVal = getIntent().getStringExtra("price") ;

            Log.i("----------", "The value of total price is :" + priceVal );

            totalprice.setText("Total Price is : $" + priceVal);

        }


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if clicked, show toast & also go to next activity



                Toast.makeText(CheckoutActivity.this , "Order for " + first.getText() + " " + last.getText() + " is placed for " + street.getText() + ", " + city.getText()
                        + " is submitted at " + totalprice.getText()
                        , Toast.LENGTH_LONG).show();

                Toast.makeText(CheckoutActivity.this , "Thank you for your order!"
                        , Toast.LENGTH_SHORT).show();


                Intent i = new Intent(CheckoutActivity.this, ListRestaurants.class);

                CheckoutActivity.this.startActivity(i);



            }
        });






    }
}
