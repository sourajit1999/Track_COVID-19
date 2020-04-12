package com.applex.trackcovid_19;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.applex.trackcovid_19.util.JSONParser;
import com.applex.trackcovid_19.util.Keys;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DatabaseListActivity extends AppCompatActivity {

    TextView database1, database2, database3, database4;
    String id="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_list);

        database1 = findViewById(R.id.database1);
        database2 = findViewById(R.id.database2);
        database3 = findViewById(R.id.database3);
        database4 = findViewById(R.id.database4);



    }

    class GetDataTask extends AsyncTask<Void, Void, Void> {

        int jIndex=0;
        int len;

        String modelTeamName;
        String modelTeamID;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

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
            if(!Sid5.isEmpty()){
                myref.child(Sid5).child("Events").child(event_name).child("TeamID").setValue(modelTeamID);
                myref.child(Sid5).child("Events").child(event_name).child("TeamName").setValue(modelTeamName);

            }
            if(!Sid1.isEmpty()){
                myref.child(Sid1).child("Events").child(event_name).child("TeamID").setValue(modelTeamID);
                myref.child(Sid1).child("Events").child(event_name).child("TeamName").setValue(modelTeamName);
//               Utility.showToast(getApplicationContext(),modelTeamID+" abc "+modelTeamName+"0"+len);

            }
            if(!Sid2.isEmpty()){
                myref.child(Sid2).child("Events").child(event_name).child("TeamID").setValue(modelTeamID);
                myref.child(Sid2).child("Events").child(event_name).child("TeamName").setValue(modelTeamName);

            }
            if(!Sid3.isEmpty()){
                myref.child(Sid3).child("Events").child(event_name).child("TeamID").setValue(modelTeamID);
                myref.child(Sid3).child("Events").child(event_name).child("TeamName").setValue(modelTeamName);

            }
            if(!Sid4.isEmpty()){
                myref.child(Sid4).child("Events").child(event_name).child("TeamID").setValue(modelTeamID);
                myref.child(Sid4).child("Events").child(event_name).child("TeamName").setValue(modelTeamName);

            }

            bottomSheetBehavior.setState(bottomSheetBehavior.STATE_COLLAPSED);
            etName.setText("");
            id0.setText("");
            id1.setText("");
            id2.setText("");
            id3.setText("");
            id4.setText("");

            mydialogue.dismiss();


        }
    }

    private String selectScriptId(){
        String urlLink = "";

        if(event_name.toLowerCase().matches("APERTURE".toLowerCase())){
            urlLink = Keys.aperture_Script_id;
            id = Keys.aperture_Sheet_id;
        }
        else if(event_name.toLowerCase().matches("BRICKOGRAPHY".toLowerCase())){
            urlLink = Keys.brickography_Script_id;
            id = Keys.brickography_Sheet_id;
        }
        else if(event_name.toLowerCase().matches("CS-GO".toLowerCase())){
            urlLink = Keys.csgo_Script_id;
            id = Keys.csgo_Sheet_id;
        }
        else if(event_name.toLowerCase().matches("Energy Conservation Make-a-thon".toLowerCase())){
            urlLink = Keys.energy_conservation_Script_id;
            id = Keys.energy_conservation_Sheet_id;
        }
        else if(event_name.toLowerCase().matches("electrocuted")){
            urlLink = Keys.electrocuted_Script_id;
            id = Keys.electrocuted_Sheet_id;
        }
        else if(event_name.toLowerCase().matches("electroslides")){
            urlLink = Keys.electroslides_Script_id;
            id = Keys.electroslides_Sheet_id;
        }
        else if(event_name.toLowerCase().matches("eureka")){
            urlLink = Keys.eureka_Script_id;
            id = Keys.eureka_Sheet_id;
        }
        else if(event_name.toLowerCase().matches("fifa")){
            urlLink = Keys.fifa_Script_id;
            id = Keys.fifa_Sheet_id;
        }
        else if(event_name.toLowerCase().matches("24-hour live hackathon")) {
            urlLink = Keys.hackathon_Script_id;
            id = Keys.hackathon_Sheet_id;
        }
        else if(event_name.toLowerCase().matches("Hell in a Cell".toLowerCase())){
            urlLink = Keys.HellinaCell_Script_id;
            id = Keys.HellinaCell_Sheet_id;
            id2.setVisibility(View.VISIBLE);
            id3.setVisibility(View.VISIBLE);
            id4.setVisibility(View.VISIBLE);
        }
        else if(event_name.toLowerCase().matches("Innovare".toLowerCase())){
            urlLink = Keys.innovare_Script_id;
            id = Keys.innovare_Sheet_id;
        }
        else if(event_name.toLowerCase().matches("IEDC exhibition".toLowerCase())){
            urlLink = Keys.IEDC_Robotic_Exhibition_Script_id;
            id = Keys.IEDC_Robotic_Exhibition_Sheet_id;
        }
        else if(event_name.toLowerCase().matches("Mathemagic".toLowerCase())){
            urlLink = Keys.mathemagic_Script_id;
            id = Keys.mathemagic_Sheet_id;
        }
        else if(event_name.toLowerCase().matches("ML Summit".toLowerCase())){
            urlLink = Keys.MLsummit_Script_id;
            id = Keys.MLsummit_Sheet_id;
        }
        else if(event_name.toLowerCase().matches("PUBG".toLowerCase())){
            urlLink = Keys.pubg_Script_id;
            id = Keys.pubg_Sheet_id;
        }
        else if(event_name.toLowerCase().matches("Quiz Crusade".toLowerCase())){
            urlLink = Keys.QuizCrusade_Script_id;
            id = Keys.QuizCrusade_Sheet_id;
        }
        else if(event_name.toLowerCase().matches("Robofooties".toLowerCase())){
            urlLink = Keys.robofooties_Script_id;
            id = Keys.robofooties_Sheet_id;
        }
        else if(event_name.toLowerCase().matches("AR-VR Challenge".toLowerCase())){
            urlLink = Keys.theARVRchallenge_Script_id;
            id = Keys.theARVRchallenge_Sheet_id;
        }
        else if(event_name.toLowerCase().matches("X Race".toLowerCase())){
            urlLink = Keys.xrace_Script_id;
            id = Keys.xrace_Sheet_id;
        }
        else if(event_name.toLowerCase().matches("Tracker".toLowerCase())){
            urlLink = Keys.tracker_Script_id;
            id = Keys.tracker_Sheet_id;
        }

        return urlLink;

    }
}
