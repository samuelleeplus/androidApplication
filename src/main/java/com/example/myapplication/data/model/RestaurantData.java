package com.example.myapplication.data.model;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RestaurantData {

    private Integer Id ;
    private String Name ;
    private String PhoneNumber ;
    private String WorkingHours ;
    private Boolean Wifi ;
    private Boolean TakeOut ;
    private Boolean Delivery ;
    private String Website ;
    private int Rating ;

    public RestaurantData(RestaurantData restaurantDatum) {
    }

    public RestaurantData(Integer id, String name, String phoneNumber, String workingHours, Boolean wifi, Boolean takeOut, Boolean delivery, String website, int rating) {
        Id = id;
        Name = name;
        PhoneNumber = phoneNumber;
        WorkingHours = workingHours;
        Wifi = wifi;
        TakeOut = takeOut;
        Delivery = delivery;
        Website = website;
        Rating = rating;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getWorkingHours() {
        return WorkingHours;
    }

    public void setWorkingHours(String workingHours) {
        WorkingHours = workingHours;
    }

    public Boolean getWifi() {
        return Wifi;
    }

    public void setWifi(Boolean wifi) {
        Wifi = wifi;
    }

    public Boolean getTakeOut() {
        return TakeOut;
    }

    public void setTakeOut(Boolean takeOut) {
        TakeOut = takeOut;
    }

    public Boolean getDelivery() {
        return Delivery;
    }

    public void setDelivery(Boolean delivery) {
        Delivery = delivery;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    public int getRating() {
        return Rating;
    }

    public void setRating(int rating) {
        Rating = rating;
    }

//    public ArrayList<RestaurantData> allRestaurants() {
//
//        String filename = "C:\\Users\\SL\\eclipse-workspace\\demo\\src\\main\\java\\com\\example\\demo\\Restaurant&Owner.csv";
//        ArrayList<RestaurantData> reslist = new ArrayList<RestaurantData>();
//
//        try {
//            FileReader reader = new FileReader(filename);
//            BufferedReader bfr = new BufferedReader(reader);
//
//            while(true)
//            {
//                String line = bfr.readLine();
//                if (line == null)
//                {
//                    break;
//                }
//                //getting owner details
//
//                String[] arr = line.split(",");
//                //System.out.println(arr);
//                String id = arr[15];
//
//                String username = arr[16];
//                String firstname = arr[17];
//                String lastname = arr[18];
//                String gender = arr[19];
//                String dob = arr[20];
//                String email = arr[21];
//                String password = arr[22];
//                String phonenumber = arr[23];
//                //getting restaurant of owner
//                String restaurant = arr[1];
//
//
//                //changing values
//                int ID = Integer.parseInt(id);
//
//                //date string format = 08.03.1984
//                SimpleDateFormat formatter1=new SimpleDateFormat("dd.MM.yyyy");
//                Date date2 = formatter1.parse(dob);
//
//                //getting restaurant details
//
//                String resname = arr[1];
//                String resphone = arr[2];
//                String resstreet = arr[3];
//                String rescountry = arr[4];
//                String rescity = arr[5];
//                String wifi = arr[6];
//                String takeout = arr[7];
//                String delivery = arr[8];
//
//                String  website  = arr[9];
//                String rating = arr[10];
//                String workingHours = arr[13];
//
//                String prePayment = arr[14];
//
//                //changing values
//                int RATING = Integer.parseInt(rating);
//
//                boolean WIFI = Boolean.parseBoolean(wifi);
//                boolean TAKEOUT = Boolean.parseBoolean(takeout);
//                boolean DELIVERY = Boolean.parseBoolean(delivery);
//                boolean PREPAYMENT = Boolean.parseBoolean(prePayment);
//
//                //public RestaurantData(Integer id, String name, String phoneNumber, String workingHours, Boolean wifi, Boolean takeOut, Boolean delivery, String website, int rating)
//                RestaurantData res = new RestaurantData(ID, resname, resphone, workingHours,WIFI,TAKEOUT, DELIVERY, website, RATING ) ;
//
//                //add restaurant to list
//                reslist.add(res);
//
//
//                //resRepo.save(res);
//            }
//        } catch (Exception e) {
//
//            e.printStackTrace();
//        }
//
//
//        return reslist;
//    }




}

