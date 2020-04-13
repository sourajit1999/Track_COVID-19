package com.applex.trackcovid_19;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
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

public class DisplayGatheringList extends AppCompatActivity {

    String id="";


    int sel_ID = 0;

    RecyclerView recyclerView;

    ArrayList<FlightModel> flightModels;
    ArrayList<TrainModel> trainModels;
    ArrayList<BusModel> busModels;
    ArrayList<GatheringModel> gatheringModels;

    GatheringAdapter gatheringAdapter;
    BusAdapter busAdapter;
    TrainAdapter trainAdapter;
    FlightAdapter flightAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diaplay_patient_list);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        flightModels = new ArrayList<>();
        trainModels = new ArrayList<>();
        busModels = new ArrayList<>();
        gatheringModels = new ArrayList<>();

        recyclerView = findViewById(R.id.covid_list) ;
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.hasFixedSize();


        if(getIntent().getStringExtra("Selection")!=null)
            sel_ID = Integer.parseInt(getIntent().getStringExtra("Selection"));

        if(sel_ID == 2){
//            Objects.requireNonNull(getSupportActionBar()).setTitle("Flight");
            id = Keys.Sheet1_Sheet_id;
            flightAdapter = new FlightAdapter(flightModels);
            recyclerView.setAdapter(flightAdapter);
        }
        if(sel_ID == 3){
            id = Keys.Sheet2_Sheet_id;
            trainAdapter = new TrainAdapter(trainModels);
            recyclerView.setAdapter(trainAdapter);
        }
        if(sel_ID == 4){
//            getSupportActionBar().setTitle("Bus");
            id = Keys.Sheet3_Sheet_id;
            busAdapter = new BusAdapter(busModels);
            recyclerView.setAdapter(busAdapter);
        }
        if(sel_ID == 5){
            id = Keys.Sheet4_Sheet_id;
            gatheringAdapter = new GatheringAdapter(gatheringModels);
            recyclerView.setAdapter(gatheringAdapter);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        new GetDataTask().execute();

    }

    public class GetDataTask extends AsyncTask<Void, Void, Void> {

        int jIndex=0;
        int len;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

//            Toast.makeText(DisplayGatheringList.this, sel_ID+".", Toast.LENGTH_LONG).show();
//
//            myDialogue = new Dialog(DisplayGatheringList.this);
//            myDialogue.setContentView(R.layout.dialog_otp_progress);
//            myDialogue.setCanceledOnTouchOutside(FALSE);
//            Objects.requireNonNull(myDialogue.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//            myDialogue.show();
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

                            for(; jIndex < lenArray; jIndex++) {

                                BusModel busModel = new BusModel();
                                TrainModel trainModel = new TrainModel();
                                FlightModel flightModel = new FlightModel();
                                GatheringModel gatheringModel = new GatheringModel();

                                JSONObject innerObject = array.getJSONObject(jIndex);

                                String contact = innerObject.getString("Contact");
                                String blood_group = innerObject.getString("BloodGroup");
                                String pincode = innerObject.getString("Pincode");
                                String no = Integer.toString(jIndex+1);

                                if(sel_ID!= 5){
                                    String depart_date = innerObject.getString("Departure_Date");
                                    String depart_from = innerObject.getString("From");
                                    String arrival_date = innerObject.getString("Arrival_Date");
                                    String arrival_to = innerObject.getString("To");
                                    if(sel_ID ==2){
//                                        String flight_no = innerObject.getString("FlightNo");
                                        flightModel.setNo(no);
                                        flightModel.setContact(contact);
                                        flightModel.setBlood_group(blood_group);
                                        flightModel.setPincode(pincode);
                                        flightModel.setDepart_date(depart_date);
                                        flightModel.setDepart_from(depart_from);
                                        flightModel.setArrival_date(arrival_date);
                                        flightModel.setArrival_to(arrival_to);
                                        flightModel.setFlight_no("112376");
                                        flightModels.add(flightModel);
                                    }
                                    else if(sel_ID == 3){
                                        String train_no = innerObject.getString("TrainNo");
                                        String train_name = innerObject.getString("TrainName");
                                        String coach_no = innerObject.getString("Coach_No");
                                        trainModel.setContact(contact);
                                        trainModel.setBlood_group(blood_group);
                                        trainModel.setPincode(pincode);
                                        trainModel.setDepart_date(depart_date);
                                        trainModel.setDepart_from(depart_from);
                                        trainModel.setArrival_date(arrival_date);
                                        trainModel.setArrival_to(arrival_to);
                                        trainModel.setTrain_no(train_no);
                                        trainModel.setTrain_name(train_name);
                                        trainModel.setCoach_no(coach_no);
                                        trainModel.setNo(no);

                                        trainModels.add(trainModel);

                                    }
                                    else{
                                        busModel.setNo(no);

                                        busModel.setContact(contact);
                                        busModel.setBlood_group(blood_group);
                                        busModel.setPincode(pincode);
                                        busModel.setDepart_date(depart_date);
                                        busModel.setDepart_from(depart_from);
                                        busModel.setArrival_date(arrival_date);
                                        busModel.setArrival_to(arrival_to);
                                        busModels.add(busModel);
                                    }

                                }

                                else{
                                    String date = innerObject.getString(Keys.date);
                                    String time = innerObject.getString(Keys.time);
                                    String place = innerObject.getString(Keys.place);
                                    String size = innerObject.getString("ApproxGathering");
                                    gatheringModel.setNo(no);
                                    gatheringModel.setContact(contact);
                                    gatheringModel.setBlood_group(blood_group);
                                    gatheringModel.setPincode(pincode);
                                    gatheringModel.setDate(date);
                                    gatheringModel.setTime(time);
                                    gatheringModel.setSize(size);
                                    gatheringModel.setPlace(place);

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

            if(sel_ID ==2){
                flightAdapter.notifyDataSetChanged();
//                Toast.makeText(DisplayGatheringList.this, len + "Flight" + jIndex, Toast.LENGTH_LONG).show();

            }

            else if(sel_ID == 3){
                trainAdapter.notifyDataSetChanged();
//                Toast.makeText(DisplayGatheringList.this, len + " Train " + jIndex, Toast.LENGTH_LONG).show();
            }

            else if(sel_ID == 4){
                busAdapter.notifyDataSetChanged();
//                Toast.makeText(DisplayGatheringList.this, len + "Bus" + jIndex, Toast.LENGTH_LONG).show();
            }

            else if(sel_ID == 5){
                gatheringAdapter.notifyDataSetChanged();
//                Toast.makeText(DisplayGatheringList.this, len + "Gathering" + jIndex, Toast.LENGTH_LONG).show();
            }



        }
    }
}
