package com.example.grampanchayat;

public class Applicants {
    private String email, fname, lname, ph_no, country, city, pincode, house_no, applied_for, status, aid;

    public Applicants() {
    }

    public Applicants(String email, String fname, String lname, String ph_no, String country, String city, String pincode, String house_no, String applied_for, String status, String aid) {
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.ph_no = ph_no;
        this.country = country;
        this.city = city;
        this.pincode = pincode;
        this.house_no = house_no;
        this.applied_for=applied_for;
        this.status = status;
        this.aid=aid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPh_no() {
        return ph_no;
    }

    public void setPh_no(String ph_no) {
        this.ph_no = ph_no;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getHouse_no() {
        return house_no;
    }

    public void setHouse_no(String house_no) {
        this.house_no = house_no;
    }

    public String getApplied_for() {
        return applied_for;
    }

    public void setApplied_for(String applied_for) {
        this.applied_for = applied_for;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }
}
