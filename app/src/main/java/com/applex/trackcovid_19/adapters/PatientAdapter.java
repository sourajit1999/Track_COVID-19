package com.applex.trackcovid_19.adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.applex.trackcovid_19.R;
import com.applex.trackcovid_19.models.PatientModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.TRUE;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.ProgrammingViewHolder> {

    private ArrayList<PatientModel> mList;


    public PatientAdapter(ArrayList<PatientModel> list) {
        this.mList = list;
    }


    @NonNull
    @Override
    public ProgrammingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_patient_list,viewGroup,false);
        return new ProgrammingViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProgrammingViewHolder programmingViewHolder, int i) {
        final PatientModel currentItem = mList.get(i);

        programmingViewHolder.patient_no.setText(currentItem.getPatient_no());
        programmingViewHolder.patient_info.setText(currentItem.getPatient_info());
        programmingViewHolder.detection_location.setText(currentItem.getDetection_location());
        programmingViewHolder.patient_date.setText(currentItem.getPatient_date());
        programmingViewHolder.patient_status.setText(currentItem.getPatient_status());
        programmingViewHolder.patient_notes.setText(currentItem.getPatient_notes());


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }



    static class ProgrammingViewHolder extends RecyclerView.ViewHolder{

        TextView patient_info;
        TextView detection_location;
        TextView patient_date;
        TextView patient_status;
        TextView patient_notes;
        TextView patient_no;


        ProgrammingViewHolder(@NonNull View itemView) {
            super(itemView);
            patient_no = itemView.findViewById(R.id.patient_no);
            patient_info = itemView.findViewById(R.id.patient_info);
            detection_location = itemView.findViewById(R.id.detection_location);
            patient_date = itemView.findViewById(R.id.patient_date);
            patient_status = itemView.findViewById(R.id.patient_status);
            patient_notes = itemView.findViewById(R.id.patient_notes);

        }
    }


}


