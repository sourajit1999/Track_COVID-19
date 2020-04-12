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

import com.applex.trackcovid_19.models.PatientModel;
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

    class GetDataTask extends AsyncTask<Void, Void, Void> {

        int jIndex=0;
        int len;

        String modelTeamName;
        String modelTeamID;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            myDialogue.setContentView(R.layout.dialog_otp_progress);
            myDialogue.setCanceledOnTouchOutside(FALSE);
            Objects.requireNonNull(myDialogue.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            myDialogue.show();


        }

        @Nullable
        @Override
        protected Void doInBackground(Void... params) {

            /**
             * Getting JSON Object from Web Using okHttp
             */

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

                                /**
                                 * Creating Every time New Object
                                 * and
                                 * Adding into List
                                 */
//                                UserDataModel model = new UserDataModel();

                                /**
                                 * Getting Inner Object from contacts array...
                                 * and
                                 * From that We will get Name of that Contact
                                 *
                                 */
                                JSONObject innerObject = array.getJSONObject(jIndex);

                                else if(sel_ID ==2){
                                    String modelemail = innerObject.getString(Keys.eventEmail);

                                }
                                else if(sel_ID == 3){
                                    String modelemail = innerObject.getString(Keys.eventEmail);

                                }
                                else if(sel_ID == 4){
                                    String modelemail = innerObject.getString(Keys.eventEmail);

                                }
                                else if(sel_ID == 5){
                                    String modelemail = innerObject.getString(Keys.eventEmail);

                                }
                                String modelemail = innerObject.getString(Keys.eventEmail);

                                if(modelemail.matches(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                                    modelTeamID = innerObject.getString(Keys.teamId);
                                    modelTeamName = innerObject.getString(Keys.eventTeamName);
                                    break;
                                }


                                /**
                                 * Getting Object from Object "phone"
                                 */
                                //JSONObject phoneObject = innerObject.getJSONObject(Keys.KEY_PHONE);
                                //String phone = phoneObject.getString(Keys.KEY_MOBILE);

//                                model.setName(modelname);
//                                model.setEmail(modelemail);
//                                model.setParticipantID(modelparticipantID);
//                                model.setContactNo(modelcontactNo);

                                /**
                                 * Adding name and phone concatenation in List...
                                 */
//                                list.add(model);
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


        }
    }
}
