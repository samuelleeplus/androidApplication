package com.example.myapplication.data.model;

import java.util.ArrayList;

public class RestaurantDataServer {

    private Integer Id ;
    private String Name ;
    private String PhoneNumber ;
    private String WorkingHours ;

    private String streetAddress ;
    private String City ;
    private String Country ;

    private Boolean Wifi ;
    private Boolean TakeOut ;
    private Boolean Delivery ;

    private Boolean prePayment ;
    private String Website ;
    private Integer Rating ;
    private String owner ;
    private ArrayList<String> posts ;



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

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
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

    public Boolean getPrePayment() {
        return prePayment;
    }

    public void setPrePayment(Boolean prePayment) {
        this.prePayment = prePayment;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    public Integer getRating() {
        return Rating;
    }

    public void setRating(int rating) {
        Rating = rating;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public ArrayList<String> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<String> posts) {
        this.posts = posts;
    }
}
