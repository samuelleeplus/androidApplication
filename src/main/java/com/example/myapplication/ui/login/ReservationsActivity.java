package com.example.myapplication.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.myapplication.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ReservationsActivity extends AppCompatActivity {

    Button reserve, cancel ;
    TextView PickTime, PickGuests ;

    TimePicker timepicker ;
    CalendarView datePicker ;
    NumberPicker numberPicker ;
    EditText fullName ;

    String time , date, number ;

    String resName, resWebsite, resStreet,resCity ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations);

        reserve = findViewById(R.id.ReserveButton);
        cancel = findViewById(R.id.ReserveCancel);
        PickTime = findViewById(R.id.PickTime);
        PickGuests = findViewById(R.id.reserveNumberPeople);
        timepicker = findViewById(R.id.timePicker) ;
        datePicker = findViewById(R.id.calendarView);
        numberPicker = findViewById(R.id.numberPicker);
        fullName = findViewById(R.id.ReservationName);

        getResData();

        numberPicker.setMaxValue(10);
        numberPicker.setMinValue(1);
        numberPicker.setWrapSelectorWheel(true);
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                String text = "Total Number of Guests: " + newVal;
                number = text ;
            }
        });

        datePicker.setFirstDayOfWeek(2);
        long selectedDate = datePicker.getDate();
        datePicker.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String text = "Date is : " + dayOfMonth +" / " + (month+1) + " / " + year ;
                //Toast.makeText(ReservationsActivity.this, text, Toast.LENGTH_SHORT).show();
                date = text ;

            }
        });

        timepicker.setCurrentHour(0);
        timepicker.setCurrentMinute(00);
        timepicker.setIs24HourView(true);
        timepicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                String text = "Reservation Time is : " + hourOfDay +":" + minute ;
                //Toast.makeText(ReservationsActivity.this, text, Toast.LENGTH_SHORT).show();
                time = text ;
            }
        });

        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ReservationsActivity.this, "Reserving at " +
                        timepicker.getCurrentHour() + ":" + timepicker.getCurrentMinute() +
                        " for " + numberPicker.getValue() + " guests under " + fullName.getText() , Toast.LENGTH_SHORT).show();


                // switch out reservations http call when necessary
                ReservationAsyncTsk tsk = new ReservationAsyncTsk();
                tsk.execute("http://10.0.2.2:8080/reservations/" + fullName.getText().toString(),
                        date,
                        time,
                        fullName.getText().toString(),
                        number,
                        resName,
                        resWebsite,
                        resStreet,
                        resCity
                        );

                Intent i = new Intent(ReservationsActivity.this, ListRestaurants.class);
                ReservationsActivity.this.startActivity(i);

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ReservationsActivity.this, "Reservation canceled!", Toast.LENGTH_SHORT).show();
                //go back to previous activity
                finish();
            }
        });
    }

    public void getResData(){
        if (getIntent().hasExtra("restaurantName") && getIntent().hasExtra("restaurantWebsite")
                && getIntent().hasExtra("restaurantStreetAddress") && getIntent().hasExtra("restaurantCity")) {
            resName = getIntent().getStringExtra("restaurantName");
            resWebsite = getIntent().getStringExtra("restaurantWebsite");
            resStreet = getIntent().getStringExtra("restaurantStreetAddress");
            resCity = getIntent().getStringExtra("restaurantCity");
        } else {

            //Log.i("----------", "MISSING RESTAURANT DETAILS FOR RESERVATIONS!");
            Toast.makeText(this, "Missing data!", Toast.LENGTH_SHORT).show();
        }
    }

    class ReservationAsyncTsk extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder stringBuilder = new StringBuilder() ;
            String url = strings[0];
            String date = strings[1];
            String time = strings[2];
            String name = strings[3];
            String guests = strings[4];
            String resName = strings[5];
            String resWebsite = strings[6];
            String resStreet= strings[7];
            String resCity = strings[8];

            JSONObject newReservation = new JSONObject ();
            try {
                newReservation.put("reservationDate", date);
                newReservation.put("reservationTime", time);
                newReservation.put("reservationName", name);
                newReservation.put("reservationGuests", guests);
                newReservation.put("restaurantName", resName);
                newReservation.put("restaurantWebsite", resWebsite);
                newReservation.put("restaurantStreet", resStreet);
                newReservation.put("restaurantCity", resCity);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            //open & set up connection
            try {
                URL urlOpen = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) urlOpen.openConnection();
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.connect();
                //output stream to send json object
                DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                out.writeBytes(newReservation.toString());

                //when reservation is received from server side
                //it will return reservation details
                if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line = "";

                    while((line= reader.readLine())!= null){
                        stringBuilder.append(line);
                    }

                }


            } catch (Exception e) {
                e.printStackTrace();
            }

            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                //just show confirmation of reservation
                JSONObject confirmation = new JSONObject(s);
                String date = confirmation.getString("reservationDate");
                String time = confirmation.getString("reservationTime");
                String name = confirmation.getString("reservationName");
                String guests = confirmation.getString("reservationGuests");

                // simple toast message to confirm
                // need to work on sending notification to profile
                Toast.makeText(ReservationsActivity.this, "Reservations under " + name + " at " + time + ", " + date + " for " + " of guests completed!", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }




}
