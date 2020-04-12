package com.applex.trackcovid_19.models;

import android.widget.ScrollView;
import android.widget.TextView;

public class PatientModel {
    String patient_no;
    public String patient_info;
    public String detection_location;
    public String patient_date;
    public String patient_status;
    public String patient_notes;
    public PatientModel() {
    }

    public PatientModel(String patient_info, String detection_location, String patient_date, String patient_status, String patient_notes) {
        this.patient_info = patient_info;
        this.detection_location = detection_location;
        this.patient_date = patient_date;
        this.patient_status = patient_status;
        this.patient_notes = patient_notes;
    }

    public String getPatient_no() {
        return patient_no;
    }

    public void setPatient_no(String patient_no) {
        this.patient_no = patient_no;
    }

    public String getPatient_info() {
        return patient_info;
    }

    public void setPatient_info(String patient_info) {
        this.patient_info = patient_info;
    }

    public String getDetection_location() {
        return detection_location;
    }

    public void setDetection_location(String detection_location) {
        this.detection_location = detection_location;
    }

    public String getPatient_date() {
        return patient_date;
    }

    public void setPatient_date(String patient_date) {
        this.patient_date = patient_date;
    }

    public String getPatient_status() {
        return patient_status;
    }

    public void setPatient_status(String patient_status) {
        this.patient_status = patient_status;
    }

    public String getPatient_notes() {
        return patient_notes;
    }

    public void setPatient_notes(String patient_notes) {
        this.patient_notes = patient_notes;
    }
}

