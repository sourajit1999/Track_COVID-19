package com.applex.trackcovid_19.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.applex.trackcovid_19.R;
import com.applex.trackcovid_19.models.PatientModel;

import java.util.ArrayList;

public class GatheringAdapter extends RecyclerView.Adapter<GatheringAdapter.ProgrammingViewHolder> {

    private ArrayList<PatientModel> mList;
    private Context context;


    public GatheringAdapter(ArrayList<PatientModel> list, Context context) {
        this.mList = list;
        this.context = context;
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


        private ProgrammingViewHolder(@NonNull View itemView) {
            super(itemView);
            patient_info = itemView.findViewById(R.id.patient_info);
            detection_location = itemView.findViewById(R.id.detection_location);
            patient_date = itemView.findViewById(R.id.patient_date);
            patient_status = itemView.findViewById(R.id.patient_status);
            patient_notes = itemView.findViewById(R.id.patient_notes);
        }
    }


}


