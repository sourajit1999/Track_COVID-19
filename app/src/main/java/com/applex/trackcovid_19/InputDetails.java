package com.applex.trackcovid_19;

import androidx.appcompat.app.AppCompatActivity;

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

    EditText train_name,train_no,coach_no;

    EditText flight_no,boarding_class;

    EditText address, date, no, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.element_recyclerview);

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
                if(sel_ID==1){
                    if(to.getText().toString().isEmpty()||)
                }
                else if(sel_ID == 2) {

                }
                else if(sel_ID ==3) {

                }
                else if(sel_ID ==4) {

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
                mydialogue.dismiss();
            }
        });
        mydialogue.show();
    }

    private void customize(){
        travel = findViewById(R.id.traveldetails);
        train = findViewById(R.id.traveldetails);
        flight = findViewById(R.id.traveldetails);
        gathering = findViewById(R.id.traveldetails);

        if(sel_ID==1){
            urlLink = Keys.Sheet1_Script_id;
            id = Keys.Sheet1_Sheet_id;

            travel.setVisibility(View.VISIBLE);
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
                    postDataParams.put(Keys.to ,inst);
                    postDataParams.put(Keys.from ,designation);
                    postDataParams.put(Keys.departureTime ,email);
                    postDataParams.put(Keys.arrivalTime ,contact);
                    postDataParams.put(Keys.source ,source);
                }
                else if(sel_ID == 2) { //train

                }
                else if(sel_ID ==3) { //bus

                }
                else if(sel_ID ==4) { //gathering

                }
                postDataParams.put(Keys.name ,name);
                postDataParams.put(Keys.institution ,inst);
                postDataParams.put(Keys.designation ,designation);
                postDataParams.put(Keys.email ,email);
                postDataParams.put(Keys.contact ,contact);
                postDataParams.put(Keys.source ,source);

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
            intent.putExtra("value","submit");
            intent.putExtra("name",name);
            intent.putExtra("email",email);
//            intent.putExtra("pic",pic);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
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
