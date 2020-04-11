package com.applex.trackcovid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.applex.trackcovid_19.util.Utility;

public class RegistrationFormPost extends AppCompatActivity {
    EditText registrationID;
    Button go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form_post);
        registrationID=findViewById(R.id.reg_id);
        go=findViewById(R.id.go);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              final String input_id= registrationID.getText().toString().trim();
              if(input_id.matches("asdf34")){
                    Intent i=new Intent(getApplicationContext(),DisplayActivity.class);
                    startActivity(i);
                     overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                     finish();
                }
                else
                    Utility.showToast(getApplicationContext(),"Enter a valid Registration ID!");
            }
        });

    }
}
