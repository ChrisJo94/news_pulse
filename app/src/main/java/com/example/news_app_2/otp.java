package com.example.news_app_2;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class otp extends AppCompatActivity {
    private EditText ipcode1,ipcode2,ipcode3,ipcode4,ipcode5,ipcode6;
    TextView err2,resend_otp;


    String verificationId,code;
    FirebaseAuth mAuth;

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    PhoneAuthProvider.ForceResendingToken mResendToken;

    PhoneAuthCredential credential;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_otp);

        err2=findViewById(R.id.err2);
        ProgressBar pbar2=findViewById(R.id.pbar2);

        Button verify_otp=findViewById(R.id.verify_otp);


        ipcode1=findViewById(R.id.ipcode1);
        ipcode2=findViewById(R.id.ipcode2);
        ipcode3=findViewById(R.id.ipcode3);
        ipcode4=findViewById(R.id.ipcode4);
        ipcode5=findViewById(R.id.ipcode5);
        ipcode6=findViewById(R.id.ipcode6);

        TextView pbnumber=findViewById(R.id.pbnumber);
        pbnumber.setText(String.format(
                "+91-%s",getIntent().getStringExtra("mobile")
        ));

        verificationId=getIntent().getStringExtra("verificationId");

        mAuth= FirebaseAuth.getInstance();


        setupOTPInputs();




        verify_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ipcode1.getText().toString().isEmpty()||ipcode2.getText().toString().isEmpty()||ipcode3.getText().toString().isEmpty()||ipcode4.getText().toString().isEmpty()||ipcode5.getText().toString().isEmpty()||ipcode6.getText().toString().isEmpty()){
                    Toast.makeText(otp.this, "Enter valid Code", Toast.LENGTH_SHORT).show();
                    return;
                }
                code=ipcode1.getText().toString()+ipcode2.getText().toString()+ipcode3.getText().toString()+ipcode4.getText().toString()+ipcode5.getText().toString()+ipcode6.getText().toString();
                credential = PhoneAuthProvider.getCredential(verificationId, code);
                verify_otp.setVisibility(View.GONE);
                pbar2.setVisibility(View.VISIBLE);


                signInWithPhoneAuthCredential(credential);




            }
        });


    }

    private void setupOTPInputs(){

        ipcode1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()) {
                    ipcode2.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        ipcode2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()) {
                    ipcode3.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        ipcode3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()) {
                    ipcode4.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        ipcode4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()) {
                    ipcode5.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        ipcode5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()) {
                    ipcode6.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {


                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
//                            Toast.makeText(otp.this, "OTP verified", Toast.LENGTH_SHORT).show();

                            FirebaseUser user = task.getResult().getUser();
                            Intent intent=new Intent(otp.this, MainActivity.class);
                            intent.putExtra("user",user.getUid());
                            startActivity(intent);
//                            Toast.makeText(otp.this, user+" has signed in", Toast.LENGTH_SHORT).show();
                            finish();
                            // Update UI
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(otp.this, "Sign in failed", Toast.LENGTH_SHORT).show();
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                err2.setText("Invalid verification code");

                            }
                        }
                    }
                });
    }


}