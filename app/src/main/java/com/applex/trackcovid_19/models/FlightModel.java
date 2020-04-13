package com.applex.trackcovid_19.models;

public class FlightModel {
    String no;

    String contact;
    String blood_group;
    String pincode;
    String depart_date;
    String depart_from;
    String arrival_date;
    String arrival_to;
    String flight_no;

    public FlightModel() {
    }

    public FlightModel(String contact, String blood_group, String pincode, String depart_date, String depart_from, String arrival_date, String arrival_to, String flight_no) {
        this.contact = contact;
        this.blood_group = blood_group;
        this.pincode = pincode;
        this.depart_date = depart_date;
        this.depart_from = depart_from;
        this.arrival_date = arrival_date;
        this.arrival_to = arrival_to;
        this.flight_no = flight_no;
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

    public String getDepart_date() {
        return depart_date;
    }

    public void setDepart_date(String depart_date) {
        this.depart_date = depart_date;
    }

    public String getDepart_from() {
        return depart_from;
    }

    public void setDepart_from(String depart_from) {
        this.depart_from = depart_from;
    }

    public String getArrival_date() {
        return arrival_date;
    }

    public void setArrival_date(String arrival_date) {
        this.arrival_date = arrival_date;
    }

    public String getArrival_to() {
        return arrival_to;
    }

    public void setArrival_to(String arrival_to) {
        this.arrival_to = arrival_to;
    }

    public String getFlight_no() {
        return flight_no;
    }

    public void setFlight_no(String flight_no) {
        this.flight_no = flight_no;
    }
}
