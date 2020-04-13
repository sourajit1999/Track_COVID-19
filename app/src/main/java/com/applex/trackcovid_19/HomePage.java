package com.applex.trackcovid_19;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static java.lang.Boolean.FALSE;

public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

//    private Spinner mSpinner;
    private EditText mPhoneNo,mCode;
    private EditText mPinCode;
    private Spinner mSpinnerBlood;
    Button twitter;


    Dialog myDialogue;
    String verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        twitter = findViewById(R.id.twitterbtn);

//        mSpinner = findViewById(R.id.spinnerCountries);
//        mSpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, CountryData.countryNames));

        mCode = findViewById(R.id.code);
        mPhoneNo = findViewById(R.id.phone);
        mPinCode = findViewById(R.id.pin_code);
        mSpinnerBlood = findViewById(R.id.spinnerBlood);
        myDialogue = new Dialog(HomePage.this);

        myDialogue.setContentView(R.layout.dialog_otp_progress);
        myDialogue.setCanceledOnTouchOutside(FALSE);
        myDialogue.findViewById(R.id.verify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = myDialogue.findViewById(R.id.otp_dialog);
                if(et.getText().toString().length()==6){
                    verifyCode(et.getText().toString());
                }
                else
                    Toast.makeText(getApplicationContext(),"Invalid OTP",Toast.LENGTH_SHORT).show();
            }
        });
        Objects.requireNonNull(myDialogue.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        findViewById(R.id.otp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = mCode.getText().toString();

                String number = mPhoneNo.getText().toString().trim();

                if (number.isEmpty() || number.length() < 10) {
                    mPhoneNo.setError("Valid number is required");
                    mPhoneNo.requestFocus();
                    return;
                }
                if (mPinCode.getText().toString().isEmpty() || number.length() < 6) {
                    mPinCode.setError("Valid pin code is required");
                    mPinCode.requestFocus();
                    return;
                }
                if(FirebaseAuth.getInstance().getCurrentUser() != null){
                    if(number.matches(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber().replace("+91",""))){
                        Intent intent = new Intent(getApplicationContext(), InputDetails.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.putExtra("bloodgroup",mSpinnerBlood.getSelectedItem().toString());
                        intent.putExtra("pincode",mPinCode.getText().toString().trim());
                        intent.putExtra("phone",mPhoneNo.getText().toString().trim());
                        startActivity(intent);
                    }
                    else {
                        FirebaseAuth.getInstance().signOut();
                        String phoneNumber = code + number;
                        myDialogue.show();
                        sendVerificationCode(phoneNumber);
                    }
                }
                else {
                    String phoneNumber = code + number;
                    myDialogue.show();
                    sendVerificationCode(phoneNumber);
                }

            }
        });


        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(HomePage.this, TwitterActivity.class);
                startActivity(i);
            }
        });






        //////////DRAWER////////////////////

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setItemIconTintList(null);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        findViewById(R.id.details).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, TwitterActivity.class));
            }
        });
    }
    //////////DRAWER////////////////////

    /////////////////SEND NO FOR VERIFICATION/////////////
    private void sendVerificationCode(String number){
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallBack
        );
    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId = s;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null){
//                progressBar.setVisibility(View.VISIBLE);
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            myDialogue.dismiss();
        }
    };
    /////////////////SEND NO FOR VERIFICATION/////////////

    private void verifyCode(String code){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(getApplicationContext(), InputDetails.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.putExtra("bloodgroup",mSpinnerBlood.getSelectedItem().toString());
                            intent.putExtra("pincode",mPinCode.getText().toString().trim());
                            intent.putExtra("phone",mPhoneNo.getText().toString().trim());
                            startActivity(intent);
                            myDialogue.dismiss();

                        } else {
                            Toast.makeText(getApplicationContext(), Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_LONG).show();
                            myDialogue.dismiss();
                        }
                    }

                });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.home_page, menu);
        return true;
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.nav_twitter) {
            // Handle the camera action
            Intent intent=new Intent(HomePage.this, TwitterActivity.class);
            startActivity(intent);
        }
        if (id == R.id.nav_login) {
            FirebaseUser fireUser = FirebaseAuth.getInstance().getCurrentUser();
            if(fireUser!=null) {
                if(fireUser.isEmailVerified()){
                    Intent intent=new Intent(HomePage.this, RegistrationFormPost.class);
                    intent.putExtra("value", "loggedin");
                    startActivity(intent);
                }
            }
            else {
                Intent intent=new Intent(HomePage.this, LoginActivity.class);
                startActivity(intent);
            }
        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                super.onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);

    }


}
