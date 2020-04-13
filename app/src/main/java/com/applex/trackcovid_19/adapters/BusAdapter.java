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
import com.applex.trackcovid_19.models.BusModel;
import com.applex.trackcovid_19.models.GatheringModel;

import java.util.ArrayList;

public class BusAdapter extends RecyclerView.Adapter<BusAdapter.ProgrammingViewHolder> {

    private ArrayList<BusModel> mList;


    public BusAdapter(ArrayList<BusModel> list) {
        this.mList = list;
    }


    @NonNull
    @Override
    public ProgrammingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_bus_list,viewGroup,false);
        return new ProgrammingViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProgrammingViewHolder programmingViewHolder, int i) {
        final BusModel currentItem = mList.get(i);

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
        programmingViewHolder.blood_group.setText("Blood Group: "+currentItem.getBlood_group());
        programmingViewHolder.pincode.setText("Pincode: " + currentItem.getPincode());
        programmingViewHolder.depart_date.setText(currentItem.getDepart_date());
        programmingViewHolder.depart_from.setText(currentItem.getDepart_from());
        programmingViewHolder.arrival_date.setText(currentItem.getArrival_date());
        programmingViewHolder.arrival_to.setText(currentItem.getArrival_to());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }



    static class ProgrammingViewHolder extends RecyclerView.ViewHolder{

        TextView contact;
        TextView blood_group;
        TextView pincode;
        TextView depart_date;
        TextView depart_from;
        TextView arrival_date;
        TextView arrival_to;

        TextView set_visibility;
        LinearLayout details;


        private ProgrammingViewHolder(@NonNull View itemView) {
            super(itemView);
            contact = itemView.findViewById(R.id.contact);
            blood_group = itemView.findViewById(R.id.blood_group);
            pincode = itemView.findViewById(R.id.pincode);
            depart_date = itemView.findViewById(R.id.depart_date);
            depart_from = itemView.findViewById(R.id.depart_from);
            arrival_date = itemView.findViewById(R.id.arrival_date);
            arrival_to = itemView.findViewById(R.id.arrival_to);

            set_visibility = itemView.findViewById(R.id.set_visibility);
            details = itemView.findViewById(R.id.details);
        }
    }


}


