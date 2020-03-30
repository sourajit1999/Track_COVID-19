package com.applex.trackcovid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Spinner mSpinner;
    private EditText mPhoneNo;
    private EditText mPinCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSpinner = findViewById(R.id.spinnerCountries);
        mSpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, CountryData.countryNames));

        mPhoneNo = findViewById(R.id.phone);

        findViewById(R.id.buttonContinue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = CountryData.countryAreaCodes[mSpinner.getSelectedItemPosition()];

                String number = mPhoneNo.getText().toString().trim();

                if (number.isEmpty() || number.length() < 10) {
                    mPhoneNo.setError("Valid number is required");
                    mPhoneNo.requestFocus();
                    return;
                }

                String phonenumber = "+" + code + number;
//
//                Intent intent = new Intent(RegisterActivity.this, OtpActivity.class);
//                intent.putExtra("phonenumber", phonenumber);
//                startActivity(intent);

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
        }
    }
}

