package com.example.myapplication.data.model;

import java.util.Date;
import java.util.List;

public class OwnerData {
    private int Id ;
    private String UserName ;
    private String FirstName ;
    private String LastName;
    private String Gender ;
    //@Past
    private Date Dob ;
    private List<RestaurantData> Restaurant ;
    private String Email ;
    private String Password ;
    private String PhoneNumber ;


    public OwnerData(int id, String userName, String firstName, String lastName, String gender, Date dob, List<RestaurantData> restaurant, String email, String password, String phoneNumber) {
        Id = id;
        UserName = userName;
        FirstName = firstName;
        LastName = lastName;
        Gender = gender;
        Dob = dob;
        Restaurant = restaurant;
        Email = email;
        Password = password;
        PhoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "OwnerData{" +
                "Id=" + Id +
                ", UserName='" + UserName + '\'' +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Gender='" + Gender + '\'' +
                ", Dob=" + Dob +
                ", Restaurant=" + Restaurant +
                ", Email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                ", PhoneNumber='" + PhoneNumber + '\'' +
                '}';
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public Date getDob() {
        return Dob;
    }

    public void setDob(Date dob) {
        Dob = dob;
    }

    public List<RestaurantData> getRestaurant() {
        return Restaurant;
    }

    public void setRestaurant(List<RestaurantData> restaurant) {
        Restaurant = restaurant;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }
}
