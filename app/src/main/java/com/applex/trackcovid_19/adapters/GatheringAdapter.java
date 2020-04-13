package com.applex.trackcovid_19.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.applex.trackcovid_19.R;
import com.applex.trackcovid_19.models.GatheringModel;
import com.applex.trackcovid_19.models.PatientModel;

import java.util.ArrayList;

public class GatheringAdapter extends RecyclerView.Adapter<GatheringAdapter.ProgrammingViewHolder> {

    private ArrayList<GatheringModel> mList;


    public GatheringAdapter(ArrayList<GatheringModel> list) {
        this.mList = list;
    }


    @NonNull
    @Override
    public ProgrammingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_gathering_list,viewGroup,false);
        return new ProgrammingViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProgrammingViewHolder programmingViewHolder, int i) {
        final GatheringModel currentItem = mList.get(i);

        programmingViewHolder.details.setVisibility(View.GONE);
        programmingViewHolder.set_visibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(programmingViewHolder.details.getVisibility() == View.GONE) {
                    programmingViewHolder.details.setVisibility(View.VISIBLE);
                    programmingViewHolder.set_visibility.setText("-");
                }
                else {
                    programmingViewHolder.details.setVisibility(View.GONE);
                    programmingViewHolder.set_visibility.setText("+");
                }
            }
        });
        programmingViewHolder.contact.setText(currentItem.getContact());
        programmingViewHolder.blood_group.setText(currentItem.getBlood_group());
        programmingViewHolder.pincode.setText(currentItem.getPincode());
        programmingViewHolder.date.setText(currentItem.getDate());
        programmingViewHolder.time.setText(currentItem.getTime());
        programmingViewHolder.place.setText(currentItem.getPlace());
        programmingViewHolder.size.setText(currentItem.getSize());
        programmingViewHolder.patient_no.setText(currentItem.getNo());


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }



    static class ProgrammingViewHolder extends RecyclerView.ViewHolder{

        TextView contact;
        TextView blood_group;
        TextView pincode;
        TextView date;
        TextView time;
        TextView place;
        TextView size;
        TextView set_visibility;
        LinearLayout details;
        TextView patient_no;

        private ProgrammingViewHolder(@NonNull View itemView) {
            super(itemView);
            contact = itemView.findViewById(R.id.contact);
            blood_group = itemView.findViewById(R.id.blood_group);
            pincode = itemView.findViewById(R.id.pincode);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            place = itemView.findViewById(R.id.place);
            size = itemView.findViewById(R.id.size);
            set_visibility = itemView.findViewById(R.id.set_visibility);
            details = itemView.findViewById(R.id.details);
            patient_no = itemView.findViewById(R.id.patient_no);

        }
    }


}


