package com.applex.trackcovid_19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.applex.trackcovid_19.util.Utility;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationFormPost extends AppCompatActivity {
    EditText registrationID;
    Button go;
    String email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form_post);

        registrationID = findViewById(R.id.reg_id);
        go = findViewById(R.id.go);
        final FirebaseUser fireuser = FirebaseAuth.getInstance().getCurrentUser();
        final Intent i = getIntent();
        email = i.getStringExtra("email");
        password = i.getStringExtra("password");
        email = fireuser.getEmail();


        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            final String input_id= registrationID.getText().toString().trim();

            if(getIntent().getStringExtra("value").matches("loggedin")){
                if(input_id.matches("asdf34")){
                    Intent i=new Intent(getApplicationContext(), ChooseListActivity.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                    finish();
                }
                else
                    Utility.showToast(getApplicationContext(),"Enter a valid Registration ID!");

            }

            else if(getIntent().getStringExtra("value").matches("google")){
                if (fireuser.isEmailVerified()) {
                    if(input_id.matches("asdf34")){
                        Intent i=new Intent(getApplicationContext(), ChooseListActivity.class);
                        startActivity(i);
                        finish();
                    }
                    else
                        Utility.showToast(getApplicationContext(),"Enter a valid Registration ID!");
                }
                else
                    Utility.showToast(getApplicationContext(),"Inalid Registration ID!");
            }

            else {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(RegistrationFormPost.this, "User not verified. Try signing up first...", Toast.LENGTH_LONG).show();
                                }
                                else {

                                    if (fireuser.isEmailVerified()) {
                                        if(input_id.matches("asdf34")){
                                            Intent i=new Intent(getApplicationContext(), ChooseListActivity.class);
                                            startActivity(i);
                                            overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                                            finish();
                                        }
                                        else
                                            Utility.showToast(getApplicationContext(),"Enter a valid Registration ID!");
                                    }

                                    else {
                                        Toast.makeText(RegistrationFormPost.this, "Please verify your email and register", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            }
                        });
            }


            }
        });

    }
}
