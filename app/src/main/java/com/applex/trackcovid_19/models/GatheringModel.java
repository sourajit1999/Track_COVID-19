package com.applex.trackcovid_19.models;

public class GatheringModel {
    String no;

    String contact;
    String blood_group;
    String pincode;
    String date;
    String time;
    String place;
    String size;

    public GatheringModel() {
    }

    public GatheringModel(String contact, String blood_group, String pincode, String date, String time, String place, String size) {
        this.contact = contact;
        this.blood_group = blood_group;
        this.pincode = pincode;
        this.date = date;
        this.time = time;
        this.place = place;
        this.size = size;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
