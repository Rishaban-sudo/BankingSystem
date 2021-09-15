package com.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer {
    private int id;
    private String fname;
    private String lname;
    private String city;
    private String zipCode;
    private String state;
    private String contactNo;
    private String email;
    private String password;

    public Customer(int id, String fname, String lname, String city, String zipCode, String state, String contactNo,
                    String email,String password) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.city = city;
        this.zipCode = zipCode;
        this.state = state;
        this.contactNo = contactNo;
        this.email = email;
        this.password = password;
    }

    public static Customer fromResultSet(ResultSet rs) {
        try {
            return new Customer(rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getString(9));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getState() {
        return state;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return
                "id = " + id + "\n" +
                "fname = " + fname + "\n" +
                "lname = " + lname + "\n" +
                "city = " + city + "\n" +
                "zipCode = " + zipCode + "\n" +
                "state = " + state + "\n" +
                "contactNo = " + contactNo + "\n" +
                 "email = " + email + "\n" +
                 "password = " + password + "\n";
    }
}
