package com.applex.trackcovid_19;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.applex.trackcovid_19.adapters.BusAdapter;
import com.applex.trackcovid_19.adapters.FlightAdapter;
import com.applex.trackcovid_19.adapters.GatheringAdapter;
import com.applex.trackcovid_19.adapters.TrainAdapter;
import com.applex.trackcovid_19.models.BusModel;
import com.applex.trackcovid_19.models.FlightModel;
import com.applex.trackcovid_19.models.GatheringModel;
import com.applex.trackcovid_19.models.PatientModel;
import com.applex.trackcovid_19.models.TrainModel;
import com.applex.trackcovid_19.util.JSONParser;
import com.applex.trackcovid_19.util.Keys;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import static java.lang.Boolean.FALSE;

public class DiaplayGatheringList extends AppCompatActivity {

    String id="";

    Dialog myDialogue;
    int sel_ID = 0;

    RecyclerView recyclerView;

    ArrayList<FlightModel> flightModels = new ArrayList<>();
    ArrayList<TrainModel> trainModels = new ArrayList<>();
    ArrayList<BusModel> busModels = new ArrayList<>();
    ArrayList<GatheringModel> gatheringModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diaplay_patient_list);

        myDialogue = new Dialog(getApplicationContext());
        recyclerView = findViewById(R.id.covid_list) ;

        if(getIntent().getStringExtra("Selection")!=null)
            sel_ID = Integer.parseInt(getIntent().getStringExtra("Selection"));

        if(sel_ID == 2){
            Objects.requireNonNull(getSupportActionBar()).setTitle("Flight");
            id = Keys.Sheet1_Sheet_id;
        }
        if(sel_ID == 2){
            getSupportActionBar().setTitle("Flight");
            id = Keys.Sheet2_Sheet_id;
        }
        if(sel_ID == 2){
            getSupportActionBar().setTitle("Flight");
            id = Keys.Sheet3_Sheet_id;
        }
        if(sel_ID == 2){
            getSupportActionBar().setTitle("Flight");
            id = Keys.Sheet4_Sheet_id;
        }

        new GetDataTask().execute();

    }

    class GetDataTask extends AsyncTask<Void, Void, Void> {

        int jIndex=0;
        int len;

        BusModel busModel = null;
        TrainModel trainModel = null;
        FlightModel flightModel = null;
        GatheringModel gatheringModel = null;

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
                /**
                 * Check Whether Its NULL???
                 */
                if (jsonObject != null) {
                    /**
                     * Check Length...
                     */
                    if(jsonObject.length() > 0) {
                        /**
                         * Getting Array named "contacts" From MAIN Json Object
                         */
                        JSONArray array = jsonObject.getJSONArray(Keys.sheetID);

                        /**
                         * Check Length of Array...
                         */
                        int lenArray = array.length();
                        len = lenArray;

                        if(lenArray > 0) {
                            for(; jIndex < len; jIndex++) {

                                JSONObject innerObject = array.getJSONObject(jIndex);

                                String contact = innerObject.getString(Keys.contact);
                                String blood_group = innerObject.getString(Keys.bloodgroup);
                                String pincode = innerObject.getString(Keys.pincode);

                                if(sel_ID!= 5){
                                    String depart_date = innerObject.getString(Keys.departuredate);
                                    String depart_from = innerObject.getString(Keys.from);
                                    String arrival_date = innerObject.getString(Keys.arrivaldate);
                                    String arrival_to = innerObject.getString(Keys.to);
                                    if(sel_ID ==2){
                                        String flight_no = innerObject.getString(Keys.flightNo);
                                        flightModel = new FlightModel(contact, blood_group, pincode,depart_date,depart_from,arrival_date,arrival_to,flight_no);
                                        flightModels.add(flightModel);
                                    }
                                    else if(sel_ID == 3){
                                        String train_no = innerObject.getString(Keys.trainNo);
                                        String train_name = innerObject.getString(Keys.trainName);
                                        String coach_no = innerObject.getString(Keys.coachNo);
                                        trainModel = new TrainModel(contact, blood_group, pincode,depart_date,depart_from,arrival_date,arrival_to,train_no,train_name,coach_no);
                                        trainModels.add(trainModel);

                                    }
                                    else{
                                        busModel = new BusModel(contact, blood_group, pincode,depart_date,depart_from,arrival_date,arrival_to);
                                        busModels.add(busModel);
                                    }

                                }

                                else if(sel_ID == 5){
                                    String date = innerObject.getString(Keys.date);
                                    String time = innerObject.getString(Keys.time);
                                    String place = innerObject.getString(Keys.place);
                                    String size = innerObject.getString(Keys.approxGathering);
                                    gatheringModel = new GatheringModel(contact, blood_group, pincode,date,time,place,size);
                                    gatheringModels.add(gatheringModel);

                                }

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
            if(sel_ID ==2){
                FlightAdapter flightAdapter= new FlightAdapter(flightModels,getApplicationContext());
                recyclerView.setAdapter(flightAdapter);
            }
            else if(sel_ID == 3){
                TrainAdapter trainAdapter= new TrainAdapter(trainModels,getApplicationContext());
                recyclerView.setAdapter(trainAdapter);
            }
            else if(sel_ID == 4){
                BusAdapter busAdapter= new BusAdapter(busModels,getApplicationContext());
                recyclerView.setAdapter(busAdapter);
            }
            else if(sel_ID == 5){
                GatheringAdapter gatheringAdapter= new GatheringAdapter(gatheringModels,getApplicationContext());
                recyclerView.setAdapter(gatheringAdapter);
            }

            myDialogue.dismiss();


        }
    }
}
