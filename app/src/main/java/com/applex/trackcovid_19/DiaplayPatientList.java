package com.applex.trackcovid_19;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.applex.trackcovid_19.models.PatientModel;
import com.applex.trackcovid_19.util.JSONParser;
import com.applex.trackcovid_19.util.Keys;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import static java.lang.Boolean.FALSE;

public class DiaplayPatientList extends AppCompatActivity {

    String id="";

    Dialog myDialogue;
    int sel_ID = 0;

    RecyclerView recyclerView;
    ArrayList<PatientModel> patientModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diaplay_patient_list);

        myDialogue = new Dialog(getApplicationContext());
        recyclerView = findViewById(R.id.covid_list) ;
        patientModels = new ArrayList<>();

        if(getIntent().getStringExtra("Selection")!=null)
            sel_ID = Integer.parseInt(getIntent().getStringExtra("Selection"));

        new GetDataTask().execute();

    }

    private void buildRecyclerView(){
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

    }

    class GetDataTask extends AsyncTask<Void, Void, Void> {

        int jIndex=0;
        int len;

        String modelTeamName;
        String modelTeamID;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            myDialogue.setContentView(R.layout.dialog_general_progress);
            myDialogue.setCanceledOnTouchOutside(FALSE);
            Objects.requireNonNull(myDialogue.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            myDialogue.show();
        }

        @Nullable
        @Override
        protected Void doInBackground(Void... params) {

            JSONParser jp = new JSONParser(id);
            JSONObject jsonObject = jp.getDataFromWeb();
            try {

                if (jsonObject != null) {

                    if(jsonObject.length() > 0) {

                        JSONArray array = jsonObject.getJSONArray("Raw_Data");

                        int lenArray = array.length();
                        len = lenArray;

                        PatientModel model = null;

                        if(lenArray > 0) {
                            for(; jIndex < len; jIndex++) {

                                JSONObject innerObject = array.getJSONObject(jIndex);

                                if(sel_ID == 1){
                                    String patient_info = innerObject.getString("Age_Bracket") + ", ";

                                    if(innerObject.getString("Gender").matches("F")){
                                        patient_info = patient_info.concat("Female");
                                    }
                                    else if(innerObject.getString("Gender").matches("M")){
                                        patient_info = patient_info.concat("Male");
                                    }
                                    else {
                                        patient_info = patient_info.concat("Sex Unknown");
                                    }

                                    String detection_location = innerObject.getString("Detected_City") +", "+ innerObject.getString("Detected_State");
                                    String patient_date = innerObject.getString("Date_Announced");
                                    String patient_status = innerObject.getString("Current_Status");
                                    String patient_notes = innerObject.getString("Notes");
                                    model = new PatientModel(patient_info, detection_location, patient_date, patient_status, patient_notes);
                                }

                                patientModels.add(model);
                            }
                        }
                    }
                } else {

                }
            } catch (JSONException je) {
                Log.i(JSONParser.TAG, "" + je.getLocalizedMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            /**
             * Checking if List size if more than zero then
             * Update ListView
             */

            myDialogue.dismiss();
            buildRecyclerView();

        }
    }
}
