package com.applex.trackcovid_19;


import androidx.annotation.NonNull;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.applex.trackcovid_19.util.InternetConnection;
import com.applex.trackcovid_19.util.Utility;
import com.google.android.gms.common.SignInButton;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.view.View.GONE;

public class LoginActivity extends AppCompatActivity {


    static final int GOOGLE_SIGN = 123;
    EditText etEmail;
    EditText etPass;
    Button login;
    Button signup;
    private SignInButton signin_google;

    private FirebaseAuth mAuth;
    LottieAnimationView progress;

    DatabaseReference ref;
    Dialog mydialogue;

    TextView logintext;
    TextView signuptext;
    TextView acctInfo;
    GoogleSignInClient mGooglesigninclient;
    public FirebaseUser fireuser ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth= FirebaseAuth.getInstance();
        fireuser = mAuth.getCurrentUser();

        etEmail = findViewById(R.id.email);
        etPass = findViewById(R.id.password);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);

        signin_google = findViewById(R.id.signin_google);

        progress =findViewById(R.id.progressAnim);
        logintext = findViewById(R.id.login_text);
        signuptext = findViewById(R.id.signup_text);
        acctInfo = findViewById(R.id.account_info);

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGooglesigninclient = GoogleSignIn.getClient(this, googleSignInOptions);


        signin_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                 Toast.makeText(Login.this,"hi",Toast.LENGTH_SHORT).show();
                if(!InternetConnection.checkConnection(getApplicationContext())){
                    Toast.makeText(getApplicationContext(),"Please check internet connection...",Toast.LENGTH_LONG).show();
                }
                else {
                    LoginActivity.this.SignInGoogle();
                }
            }
        });


        ////////////////LOGIN/////////////////////

        signuptext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logintext.setVisibility(View.VISIBLE);
                signuptext.setVisibility(GONE);
                acctInfo.setText("Already have an account?");
                login.setVisibility(GONE);
                signup.setVisibility(View.VISIBLE);
                etPass.setText("");
            }
        });

        logintext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logintext.setVisibility(GONE);
                signuptext.setVisibility(View.VISIBLE);
                acctInfo.setText("Don't have an account yet?");
                login.setVisibility(View.VISIBLE);
                signup.setVisibility(GONE);
                etPass.setText("");

            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(InternetConnection.checkConnection(LoginActivity.this)){
                    final String password = etPass.getText().toString().trim();
                    final String email = etEmail.getText().toString().trim();
                    if(email.isEmpty()||!Patterns.EMAIL_ADDRESS.matcher(email).matches()||password.isEmpty()) {
                        if (email.isEmpty()) {
                            etEmail.setError("Email missing");
                            etEmail.requestFocus();
                        }
                        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                            etEmail.setError("Please enter a valid email");
                            etEmail.requestFocus();
                        }

                    }
                    else if (password.length()<8) {
                        etPass.setError("Password must contain at least 8 characters");
                        etPass.requestFocus();
                    }
                    else {
                        progress.setVisibility(View.VISIBLE);
                        mAuth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            mAuth.getCurrentUser()
                                                    .sendEmailVerification()
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful())
                                                            { Toast.makeText(LoginActivity.this, "Please verify your email and register", Toast.LENGTH_LONG).show();
                                                                progress.setVisibility(GONE);
                                                                Intent intent = new Intent(LoginActivity.this, RegistrationFormPost.class);
                                                                intent.putExtra("value","emailpass");
                                                                intent.putExtra("email",email);
                                                                intent.putExtra("password",password);

                                                                startActivity(intent);
                                                                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                                                                finish();
                                                            }
                                                            else {
                                                                Toast.makeText(LoginActivity.this, "Email verification failed", Toast.LENGTH_SHORT).show();
                                                            }

                                                        }
                                                    });

                                        } else {
                                            Toast.makeText(LoginActivity.this, "Account already exists!", Toast.LENGTH_SHORT).show();
                                            progress.setVisibility(GONE);
                                        }
                                    }
                                });
                    }
                }
                else {
                    Utility.showToast(LoginActivity.this,"Please check internet connection...");
                }

            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(InternetConnection.checkConnection(LoginActivity.this)){

                    final String password = etPass.getText().toString().trim();
                    final String email = etEmail.getText().toString().trim();

                    if(email.isEmpty()||!Patterns.EMAIL_ADDRESS.matcher(email).matches()||email.isEmpty()) {
                        if (email.isEmpty()) {
                            etEmail.setError("Email missing");
                            etEmail.requestFocus();
                        }
                        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                            etEmail.setError("Please enter a valid email");
                            etEmail.requestFocus();
                        }
                    }
                    else if (password.length()<8) {
                        etPass.setError("Password must contain at least 8 characters");
                        etPass.requestFocus();
                    }
                    else {
                        progress.setVisibility(View.VISIBLE);
                        mAuth.signInWithEmailAndPassword(email,password)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (!task.isSuccessful()) {
                                            Toast.makeText(LoginActivity.this, "User not verified... Try signing up first", Toast.LENGTH_LONG).show();
                                            progress.setVisibility(GONE);
                                        }
                                        else {
                                            FirebaseUser fireuser = mAuth.getCurrentUser();
                                            if (fireuser.isEmailVerified()) {
                                                progress.setVisibility(GONE);
                                                Intent intent = new Intent(LoginActivity.this, RegistrationFormPost.class);
                                                intent.putExtra("value","emailpass");
                                                intent.putExtra("email",email);
                                                intent.putExtra("password",password);
                                                startActivity(intent);
                                                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                                                finish();
                                            }
                                            else {
                                                Toast.makeText(LoginActivity.this, "Email verification incomplete!", Toast.LENGTH_SHORT).show();
                                                etPass.setText("");
                                                etPass.setHint("password");
                                                progress.setVisibility(GONE);
                                            }
                                        }
                                    }
                                });
                    }
                }

                else {
                    Utility.showToast(LoginActivity.this,"Please check internet connection...");
                }

            }
        });

    }

    void SignInGoogle() {
        Intent signintent = mGooglesigninclient.getSignInIntent();
        startActivityForResult(signintent, GOOGLE_SIGN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GOOGLE_SIGN) {
            Task<GoogleSignInAccount> task = GoogleSignIn
                    .getSignedInAccountFromIntent(data);
            try
            {   GoogleSignInAccount account = task.getResult(ApiException.class);
                if (account != null){
                    progress.setVisibility(View.VISIBLE);
                    firebaseAuthWithGoogle(account);
                }
            } catch (ApiException e) {
                Toast.makeText(LoginActivity.this, "Sign in failed..", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }

    private void firebaseAuthWithGoogle(final GoogleSignInAccount account) {
        Log.d("TAG", "firebaseAuthWithGoogle: " + account.getId());
        AuthCredential credential = GoogleAuthProvider
                .getCredential(account.getIdToken(), "null");

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("TAG", "signin successful");
                            Toast.makeText(LoginActivity.this,"Signed in successfully",Toast.LENGTH_SHORT).show();
                            progress.setVisibility(GONE);
                            ref= FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getUid());
                            ref.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if(dataSnapshot.getValue()==null){
                                        Intent i = new Intent(LoginActivity.this, RegistrationFormPost.class);
                                        i.putExtra("value", "google");
                                        i.putExtra("email", account.getEmail());
                                        i.putExtra("name", account.getDisplayName());
                                        startActivity(i);
                                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                                        finish();
                                    }
                                    else {
                                        Intent i = new Intent(LoginActivity.this, ChooseListActivity.class);
                                        startActivity(i);
                                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                                        finish();
                                    }
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });



//                                Intent i = new Intent(Login.this, RegistrationFormPost.class);
//                                i.putExtra("value", "google");
//                                i.putExtra("email", account.getEmail());
//                                i.putExtra("name", account.getDisplayName());
//                                startActivity(i);
//                                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
//                                finish();
//                            }
//                            else {
//                                Intent i = new Intent(Login.this, MainActivity.class);
//                                startActivity(i);
//                                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
//                                finish();
//                            }
                        }

                        else {
                            Log.w("TAG", "sign in failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Sign in failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
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
