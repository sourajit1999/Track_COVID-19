package com.applex.trackcovid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.applex.trackcovid_19.util.Keys;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Objects;

import javax.net.ssl.HttpsURLConnection;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.logicalAnd;

public class InputDetails extends AppCompatActivity {

    Dialog mydialogue;
    int sel_ID = 0;
    String id;
    String urlLink= null;

    String phone, bloodgroup, pincode;

    LinearLayout gathering;
    LinearLayout flight;
    LinearLayout train;
    LinearLayout travel;
    Button submit;

    EditText from,to,depart_date,depart_time,arrival_date,arrival_time;

    EditText train_name,train_no,coach_no; //train

    EditText flight_no,boarding_class; //flight

    EditText address, date, no, time; //gathering

    String travel_to,travel_from,travel_d_date,travel_a_date,travel_d_time,travel_a_time;

    String flight_num,board_class;

    String train_num, train_nam,coach_num;

    String addrs, gather_date, gather_no,gather_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.element_recyclerview);

        travel = findViewById(R.id.traveldetails);
        train = findViewById(R.id.traveldetails);
        flight = findViewById(R.id.traveldetails);
        gathering = findViewById(R.id.traveldetails);

        from = findViewById(R.id.from_travel);
        to = findViewById(R.id.to_travel);
        depart_date = findViewById(R.id.depart_date_travel);
        depart_time = findViewById(R.id.depart_time_travel);
        arrival_date = findViewById(R.id.arrival_date_travel);
        arrival_time = findViewById(R.id.arrival_time_travel);

        train_name = findViewById(R.id.train_name);
        train_no = findViewById(R.id.train_no);
        coach_no = findViewById(R.id.coach_no);

        flight_no = findViewById(R.id.flight_no);
        boarding_class = findViewById(R.id.flight_boarding);

        address = findViewById(R.id.address_gather);
        date = findViewById(R.id.date_gather);
        no = findViewById(R.id.no_gather);
        time = findViewById(R.id.time_gather);

        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Flight
                if(sel_ID==1){
                    if(to.getText().toString().isEmpty()){
                        to.setError("Source missing");
                        to.requestFocus();
                        return;
                    }
                    if(from.getText().toString().isEmpty()){
                        from.setError("Destination missing");
                        from.requestFocus();
                        return;
                    }
                    if(depart_date.getText().toString().isEmpty()){
                        depart_date.setError("Date missing");
                        depart_date.requestFocus();
                        return;
                    }
                    if(arrival_date.getText().toString().isEmpty()){
                        arrival_date.setError("Date missing");
                        arrival_date.requestFocus();
                        return;
                    }

                    if(flight_no.getText().toString().isEmpty()){
                        flight_no.setError("Flight number missing");
                        flight_no.requestFocus();
                        return;
                    }
                    if(boarding_class.getText().toString().isEmpty()){
                        boarding_class.setError("Class type missing");
                        boarding_class.requestFocus();
                        return;
                    }

                    travel_to = to.getText().toString().trim();
                    travel_from = from.getText().toString().trim();
                    travel_d_date = depart_date.getText().toString().trim();
                    travel_d_time = depart_time.getText().toString().trim();
                    travel_a_date = arrival_date.getText().toString().trim();
                    travel_a_time = arrival_time.getText().toString().trim();
                    flight_num = to.getText().toString().trim();
                    board_class = to.getText().toString().trim();

                }
                else if(sel_ID == 2) {
                    if(to.getText().toString().isEmpty()){
                        to.setError("Source missing");
                        to.requestFocus();
                        return;
                    }
                    if(from.getText().toString().isEmpty()){
                        from.setError("Destination missing");
                        from.requestFocus();
                        return;
                    }
                    if(depart_date.getText().toString().isEmpty()){
                        depart_date.setError("Date missing");
                        depart_date.requestFocus();
                        return;
                    }
                    if(arrival_date.getText().toString().isEmpty()){
                        arrival_date.setError("Date missing");
                        arrival_date.requestFocus();
                        return;
                    }

                    if(train_name.getText().toString().isEmpty()){
                        train_name.setError("Train name missing");
                        train_name.requestFocus();
                        return;
                    }
//                    if(train_no.getText().toString().isEmpty()){
//                        train_no.setError("Train number missing");
//                        train_no.requestFocus();
//                        return;
//                    }
                    if(coach_no.getText().toString().isEmpty()){
                        coach_no.setError("Coach details missing");
                        coach_no.requestFocus();
                        return;
                    }

                    travel_to = to.getText().toString().trim();
                    travel_from = from.getText().toString().trim();
                    travel_d_date = depart_date.getText().toString().trim();
                    travel_d_time = depart_time.getText().toString().trim();
                    travel_a_date = arrival_date.getText().toString().trim();
                    travel_a_time = arrival_time.getText().toString().trim();

                    train_nam = train_name.getText().toString().trim();
                    train_num = train_no.getText().toString();
                    coach_num = coach_no.getText().toString();

                }
                else if(sel_ID ==3) {
                    if(to.getText().toString().isEmpty()){
                        to.setError("Source missing");
                        to.requestFocus();
                        return;
                    }
                    if(from.getText().toString().isEmpty()){
                        from.setError("Destination missing");
                        from.requestFocus();
                        return;
                    }
                    if(depart_date.getText().toString().isEmpty()){
                        depart_date.setError("Date missing");
                        to.requestFocus();
                        return;
                    }
                    if(arrival_date.getText().toString().isEmpty()){
                        arrival_date.setError("Date missing");
                        to.requestFocus();
                        return;
                    }

                    travel_to = to.getText().toString().trim();
                    travel_from = from.getText().toString().trim();
                    travel_d_date = depart_date.getText().toString().trim();
                    travel_d_time = depart_time.getText().toString().trim();
                    travel_a_date = arrival_date.getText().toString().trim();
                    travel_a_time = arrival_time.getText().toString().trim();
                }//BUS

                else if(sel_ID ==4) {
                    if(address.getText().toString().isEmpty()){
                        address.setError("Address details missing");
                        address.requestFocus();
                        return;
                    }
                    if(date.getText().toString().isEmpty()){
                        date.setError("Date missing");
                        date.requestFocus();
                        return;
                    }
                    if(time.getText().toString().isEmpty()){
                        time.setError("Time details missing");
                        time.requestFocus();
                        return;
                    }

                    addrs = address.getText().toString().trim();
                    gather_date = date.getText().toString().trim();
                    gather_no = no.getText().toString().trim();
                    gather_time = time.getText().toString().trim();
                }
                new SendRequest().execute();
            }
        });

        Intent i = getIntent();
        phone = i.getStringExtra("phone");
        pincode = i.getStringExtra("pincode");
        bloodgroup = i.getStringExtra("bloodgroup");

        setDialog();

    }

    private void setDialog(){
        mydialogue = new Dialog(InputDetails.this);
        mydialogue.setContentView(R.layout.select_dialog);
        mydialogue.setCanceledOnTouchOutside(FALSE);
        Objects.requireNonNull(mydialogue.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        mydialogue.findViewById(R.id.flight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sel_ID = 1;
                customize();
                mydialogue.dismiss();
            }
        });
        mydialogue.findViewById(R.id.train).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sel_ID = 2;
                customize();
                mydialogue.dismiss();
            }
        });
        mydialogue.findViewById(R.id.bus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sel_ID = 3;
                customize();
                mydialogue.dismiss();
            }
        });
        mydialogue.findViewById(R.id.gathering).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sel_ID = 4;
                customize();
                travel.setVisibility(View.GONE);
                flight.setVisibility(View.GONE);
                train.setVisibility(View.GONE);
                gathering.setVisibility(View.VISIBLE);
                mydialogue.dismiss();
            }
        });
        mydialogue.show();
    }

    private void customize(){


        if(sel_ID==1){
            Toast.makeText(getApplicationContext(),"flight",Toast.LENGTH_SHORT).show();
            urlLink = Keys.Sheet1_Script_id;
            id = Keys.Sheet1_Sheet_id;

            travel.setVisibility(View.GONE);
            flight.setVisibility(View.VISIBLE);
            train.setVisibility(View.GONE);
            gathering.setVisibility(View.GONE);
        }
        else if(sel_ID == 2) {
            urlLink = Keys.Sheet2_Script_id;
            id = Keys.Sheet2_Sheet_id;

            travel.setVisibility(View.VISIBLE);
            flight.setVisibility(View.GONE);
            train.setVisibility(View.VISIBLE);
            gathering.setVisibility(View.GONE);
        }
        else if(sel_ID ==3) {
            urlLink = Keys.Sheet3_Script_id;
            id = Keys.Sheet3_Sheet_id;

            travel.setVisibility(View.VISIBLE);
            flight.setVisibility(View.GONE);
            train.setVisibility(View.GONE);
            gathering.setVisibility(View.GONE);
        }
        else if(sel_ID ==4) {
            urlLink = Keys.Sheet4_Script_id;
            id = Keys.Sheet4_Sheet_id;

            travel.setVisibility(View.GONE);
            flight.setVisibility(View.GONE);
            train.setVisibility(View.GONE);
            gathering.setVisibility(View.VISIBLE);
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class SendRequest extends AsyncTask<String, Void, String> {

        protected void onPreExecute(){
            super.onPreExecute();
            ////////////SELECT SHEET////////////

            ////////////SELECT SHEET////////////
            mydialogue = new Dialog(InputDetails.this);
            mydialogue.setContentView(R.layout.select_dialog);
            mydialogue.setCanceledOnTouchOutside(FALSE);
            mydialogue.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        }

        protected String doInBackground(String... arg0) {

            try{
                //INSERT SCRIPT URL
                URL url = new URL(urlLink);

                JSONObject postDataParams = new JSONObject();

                postDataParams.put(Keys.contact ,phone);
                postDataParams.put(Keys.pincode ,pincode);
                postDataParams.put(Keys.bloodgroup ,bloodgroup);


                if(sel_ID==1){ //flight
                    postDataParams.put(Keys.to ,travel_to);
                    postDataParams.put(Keys.from ,travel_from);
                    postDataParams.put(Keys.departureTime ,travel_d_time);
                    postDataParams.put(Keys.arrivalTime ,travel_a_time);
                    postDataParams.put(Keys.departuredate,travel_d_date);
                    postDataParams.put(Keys.arrivaldate,travel_a_date);

                    postDataParams.put(Keys.flightNo ,flight_num);
                    postDataParams.put(Keys.boardingClass ,board_class);
                }
                else if(sel_ID == 2) { //train
                    postDataParams.put(Keys.to ,travel_to);
                    postDataParams.put(Keys.from ,travel_from);
                    postDataParams.put(Keys.departureTime ,travel_d_time);
                    postDataParams.put(Keys.arrivalTime ,travel_a_time);
                    postDataParams.put(Keys.departuredate,travel_d_date);
                    postDataParams.put(Keys.arrivaldate,travel_a_date);

                    postDataParams.put(Keys.coachNo ,coach_num);
                    postDataParams.put(Keys.trainName ,train_nam);
                    postDataParams.put(Keys.trainNo ,train_num);
                }
                else if(sel_ID ==3) { //bus
                    postDataParams.put(Keys.to ,travel_to);
                    postDataParams.put(Keys.from ,travel_from);
                    postDataParams.put(Keys.departureTime ,travel_d_time);
                    postDataParams.put(Keys.arrivalTime ,travel_a_time);
                    postDataParams.put(Keys.departuredate,travel_d_date);
                    postDataParams.put(Keys.arrivaldate,travel_a_date);
                }
                else if(sel_ID ==4) { //gathering

                }
                postDataParams.put(Keys.approxGathering ,gather_no);
                postDataParams.put(Keys.date ,gather_date);
                postDataParams.put(Keys.place ,addrs);
                postDataParams.put(Keys.time ,gather_time);


                //INSERT SHEET ID
                postDataParams.put(Keys.idSheet ,id);


                Log.e("params",postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                int responseCode=conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuffer sb = new StringBuffer("");
                    String line;

                    while((line = in.readLine()) != null) {

                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();

                }
                else {
                    return new String("false : "+responseCode);
                }
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            mydialogue.dismiss();
            Intent intent = new Intent(InputDetails.this, SuccessActivity.class);
//            intent.putExtra("value","submit");
//            intent.putExtra("name",name);
//            intent.putExtra("email",email);
//            intent.putExtra("pic",pic);
            startActivity(intent);
//            overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            finish();

        }
    }

    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }
}
