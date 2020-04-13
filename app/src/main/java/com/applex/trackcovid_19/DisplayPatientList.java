package com.applex.trackcovid_19;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.applex.trackcovid_19.adapters.PatientAdapter;
import com.applex.trackcovid_19.models.PatientModel;
import com.applex.trackcovid_19.util.JSONParser;
import com.applex.trackcovid_19.util.Keys;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DisplayPatientList extends AppCompatActivity {

    String id="";

    Dialog myDialogue;
    int sel_ID = 0;

    RecyclerView recyclerView;
    PatientAdapter patientAdapter;
    ArrayList<PatientModel> patientModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_patient_list);

        patientModels = new ArrayList<>();
        /////////////////RECYCLER VIEW/////////////
        recyclerView = findViewById(R.id.covid_list) ;
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        patientModels.add(new PatientModel("1285","56, Female","Nagpur, Maharashtra","28/03/2020","Hospitalised","Travelled from Italy"));
        patientAdapter = new PatientAdapter(patientModels);
        recyclerView.setAdapter(patientAdapter);

        /////////////////RECYCLER VIEW/////////////


        if(getIntent().getStringExtra("Selection")!=null)
            sel_ID = Integer.parseInt(getIntent().getStringExtra("Selection"));

        new GetDataTask().execute();

    }


    public class GetDataTask extends AsyncTask<Void, Void, Void> {

        int jIndex=0;
        int len;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            Toast.makeText(DisplayPatientList.this, sel_ID+".", Toast.LENGTH_LONG).show();

//            myDialogue = new Dialog(getApplicationContext());
//            myDialogue.setContentView(R.layout.dialog_general_progress);
//            myDialogue.setCanceledOnTouchOutside(FALSE);
//            Objects.requireNonNull(myDialogue.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//            myDialogue.show();
        }

        @Nullable
        @Override
        protected Void doInBackground(Void... params) {

            JSONParser jp = new JSONParser(Keys.Govt_Sheet_id);
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
                                String patient_no = innerObject.getString("Age_Bracket");
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
                                model = new PatientModel(patient_no,patient_info, detection_location, patient_date, patient_status, patient_notes);

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

//            myDialogue.dismiss();
            buildRecyclerView();

        }
    }


    private void buildRecyclerView(){
        patientAdapter.notifyDataSetChanged();
        Toast.makeText(DisplayPatientList.this, "abcd", Toast.LENGTH_LONG).show();

    }
}
