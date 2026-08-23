package com.example.news_app_2;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class loginotp extends AppCompatActivity {
    String phoneNumber,mVerificationId;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    PhoneAuthProvider.ForceResendingToken mResendToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_loginotp);
        EditText inputmobile=findViewById(R.id.ipnb);
        inputmobile.requestFocus();



        FirebaseAuth mAuth= FirebaseAuth.getInstance();

        ProgressBar pbar=findViewById(R.id.pbar);

        TextView err=findViewById(R.id.err);

        Button getotp=findViewById(R.id.get_otp);

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {

                Log.d(TAG, "onVerificationCompleted:" + credential);
                err.setText("This likely won't happen");

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {

                pbar.setVisibility(View.GONE);
                getotp.setVisibility(View.VISIBLE);
                Log.w(TAG, "onVerificationFailed", e);

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    err.setText("Invalid request");
                    // Invalid request
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    err.setText("The SMS quota for the project has been exceeded");
                    // The SMS quota for the project has been exceeded
                } else if (e instanceof FirebaseAuthMissingActivityForRecaptchaException) {
                    err.setText("reCAPTCHA verification attempted with null Activity");
                    // reCAPTCHA verification attempted with null Activity
                }
                Toast.makeText(loginotp.this, "Verification Failed", Toast.LENGTH_SHORT).show();
                // Show a message and update the UI
            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                pbar.setVisibility(View.VISIBLE);
                getotp.setVisibility(View.GONE);
                Log.d(TAG, "onCodeSent:" + verificationId);

                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;
                Intent intent = new Intent(loginotp.this, otp.class);
                intent.putExtra("mobile", inputmobile.getText().toString());
                intent.putExtra("verificationId", mVerificationId);
                startActivity(intent);
                finish();
            }
        };


        getotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(inputmobile.getText().toString().isEmpty()){
                    Toast.makeText(loginotp.this, "Mobile Number required", Toast.LENGTH_SHORT).show();
                    return;

                }
                getotp.setVisibility(View.GONE);
                pbar.setVisibility(View.VISIBLE);
                phoneNumber="+91"+inputmobile.getText().toString();
                    PhoneAuthOptions options =
                            PhoneAuthOptions.newBuilder(mAuth)
                                    .setPhoneNumber(phoneNumber)       // Phone number to verify
                                    .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                                    .setActivity(loginotp.this)                 // (optional) Activity for callback binding
                                    // If no activity is passed, reCAPTCHA verification can not be used.
                                    .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                                    .build();
                    PhoneAuthProvider.verifyPhoneNumber(options);

            }


            });









    }
}