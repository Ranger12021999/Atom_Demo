package com.bhai.aman.atomdemo;
//created by AMan KUmar SHarma

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PassForgetActivity extends AppCompatActivity {

    private ImageView ivLogo;
    private TextView tvInfo, tvSignin, tvPWreset;
    private AutoCompleteTextView atvEmail;
    private Button btnReset;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_forget);

        initializeGUI();

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = atvEmail.getText().toString();

                if (email.isEmpty()) {
                    atvEmail.setError("Please, fill the email field.",null);
                }
                else if (!AppStatus.getInstance(PassForgetActivity.this).isOnline()) {

                    Toast.makeText(PassForgetActivity.this,"Check Internet Connection",Toast.LENGTH_LONG).show();

                }
                else {
                    firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(PassForgetActivity.this, "Email has been sent successfully.", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(PassForgetActivity.this, LoginActivity.class));
                            }else {
                                Toast.makeText(PassForgetActivity.this, "Invalid email address.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }

            }
        });


        tvSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }


    private void initializeGUI(){

        Typeface typeface1 = ResourcesCompat.getFont(this, R.font.acme_regular);
        ivLogo = findViewById(R.id.ivLogLogo);
        tvPWreset = findViewById(R.id.ivPassReset);
        tvInfo = findViewById(R.id.tvPWinfo);
        tvSignin = findViewById(R.id.tvGoBack);
        atvEmail = findViewById(R.id.atvEmailRes);
        btnReset = findViewById(R.id.btnReset);
        tvPWreset.setTypeface(typeface1);
        tvInfo.setTypeface(typeface1);
        tvSignin.setTypeface(typeface1);

        firebaseAuth = FirebaseAuth.getInstance();

    }
}