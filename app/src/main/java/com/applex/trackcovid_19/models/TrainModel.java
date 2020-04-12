package com.applex.trackcovid_19.models;

public class TrainModel {
    String contact;
    String blood_group;
    String pincode;
    String depart_date;
    String depart_from;
    String arrival_date;
    String arrival_to;

    String train_no;
    String train_name;
    String coach_no;


    public TrainModel() {
    }

    public TrainModel(String contact, String blood_group, String pincode, String depart_date, String depart_from, String arrival_date, String arrival_to, String train_no, String train_name, String coach_no) {
        this.contact = contact;
        this.blood_group = blood_group;
        this.pincode = pincode;
        this.depart_date = depart_date;
        this.depart_from = depart_from;
        this.arrival_date = arrival_date;
        this.arrival_to = arrival_to;
        this.train_no = train_no;
        this.train_name = train_name;
        this.coach_no = coach_no;
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

    public String getTrain_no() {
        return train_no;
    }

    public void setTrain_no(String train_no) {
        this.train_no = train_no;
    }

    public String getTrain_name() {
        return train_name;
    }

    public void setTrain_name(String train_name) {
        this.train_name = train_name;
    }

    public String getCoach_no() {
        return coach_no;
    }

    public void setCoach_no(String coach_no) {
        this.coach_no = coach_no;
    }
}
