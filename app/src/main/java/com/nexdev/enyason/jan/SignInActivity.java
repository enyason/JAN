package com.nexdev.enyason.jan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {


    FirebaseAuth firebaseAuth;

    EditText etEmail,etPassword;
    Button buttonSignIn;

    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);



        firebaseAuth = FirebaseAuth.getInstance();

        dialog = new ProgressDialog(this);
        dialog.setMessage("Signin in...");
        dialog.setCanceledOnTouchOutside(false);

        etEmail = findViewById(R.id.et_email_sign_in);
        etPassword = findViewById(R.id.et_password_sign_in);


        buttonSignIn = findViewById(R.id.btn_sign_In);

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.show();

                String email = etEmail.getText().toString().trim();
                String passWord = etPassword.getText().toString().trim();

                firebaseAuth.signInWithEmailAndPassword(email,passWord).addOnCompleteListener(new OnCompleteListener<AuthResult>() {


                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            dialog.dismiss();
                            Toast.makeText(SignInActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignInActivity.this, HomeActivity.class));
                            finish();
                            //set Up profile
                        } else {

                            Toast.makeText(SignInActivity.this, "Log in fialed", Toast.LENGTH_SHORT).show();
                        }

                    }
                });


            }
        });



    }
}
