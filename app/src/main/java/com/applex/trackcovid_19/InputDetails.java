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

import javax.net.ssl.HttpsURLConnection;

import static java.lang.Boolean.FALSE;

public class InputDetails extends AppCompatActivity {

    Dialog mydialogue;
    int sel_ID = 0;
    String id;
    String urlLink= null;

    LinearLayout gathering;
    LinearLayout flight;
    LinearLayout train;
    LinearLayout travel;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.element_recyclerview);

        setDialog();
        travel = findViewById(R.id.traveldetails);
        train = findViewById(R.id.traveldetails);
        flight = findViewById(R.id.traveldetails);
        gathering = findViewById(R.id.traveldetails);

        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SendRequest().execute();
            }
        });


    }


    private void setDialog(){
        mydialogue = new Dialog(InputDetails.this);
        mydialogue.setContentView(R.layout.select_dialog);
        mydialogue.setCanceledOnTouchOutside(FALSE);
        mydialogue.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

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

                postDataParams.put(Keys.name ,name);
                postDataParams.put(Keys.institution ,inst);
                postDataParams.put(Keys.designation ,designation);
                postDataParams.put(Keys.email ,email);
                postDataParams.put(Keys.contact ,contact);
                postDataParams.put(Keys.source ,source);

                //INSERT SHEET ID
                postDataParams.put(Keys.idSheet ,Keys.central_Sheet_id);


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
            Intent intent = new Intent(RegistrationFormPost.this, HomeActivity.class);
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
