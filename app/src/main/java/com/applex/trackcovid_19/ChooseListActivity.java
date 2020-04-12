package com.applex.trackcovid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ChooseListActivity extends AppCompatActivity{

    TextView patient, flight, railway, bus, gathering;

    int sel_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_list);


        patient = findViewById(R.id.patient);
        flight = findViewById(R.id.Flight);
        railway = findViewById(R.id.Railway);
        bus = findViewById(R.id.Bus);
        gathering = findViewById(R.id.Gathering);

        patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sel_ID = 1;
                Intent intent = new Intent(getApplicationContext(), DiaplayPatientList.class);
                intent.putExtra("Selection", Integer.toString(sel_ID));
                startActivity(intent);
            }
        });
        flight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sel_ID = 2;
                Intent intent = new Intent(getApplicationContext(), DiaplayGatheringList.class);
                intent.putExtra("Selection", Integer.toString(sel_ID));
                startActivity(intent);
            }
        });
        railway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sel_ID = 3;
                Intent intent = new Intent(getApplicationContext(), DiaplayGatheringList.class);
                intent.putExtra("Selection", Integer.toString(sel_ID));
                startActivity(intent);
            }
        });
        bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sel_ID = 4;
                Intent intent = new Intent(getApplicationContext(), DiaplayGatheringList.class);
                intent.putExtra("Selection", Integer.toString(sel_ID));
                startActivity(intent);
            }
        });
        gathering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sel_ID = 5;
                Intent intent = new Intent(getApplicationContext(), DiaplayGatheringList.class);
                intent.putExtra("Selection", Integer.toString(sel_ID));
                startActivity(intent);
            }
        });

    }

}
