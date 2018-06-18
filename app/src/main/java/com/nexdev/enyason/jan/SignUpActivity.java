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

public class SignUpActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    EditText etEmail, etPassword;
    Button buttonSignUp;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firebaseAuth = FirebaseAuth.getInstance();

        dialog = new ProgressDialog(this);
        dialog.setMessage("Creating Account...");
        dialog.setCanceledOnTouchOutside(false);


        etEmail = findViewById(R.id.et_email_sign_up);
        etPassword = findViewById(R.id.et_password_sign_up);


        buttonSignUp = findViewById(R.id.btn_sign_up);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.show();

                final String email = etEmail.getText().toString().trim();
                final String passWord = etPassword.getText().toString().trim();


                firebaseAuth.createUserWithEmailAndPassword(email, passWord).addOnCompleteListener(new OnCompleteListener<AuthResult>() {


                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            final String uid = firebaseAuth.getUid();
                            dialog.dismiss();

                            Toast.makeText(SignUpActivity.this, "id "+ uid, Toast.LENGTH_SHORT).show();

                            Toast.makeText(SignUpActivity.this, "Registration Complete", Toast.LENGTH_SHORT).show();

//                            buttonSignUp.setText("Proceed With Account Set Up");

//                            buttonSignUp.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {

                                    Intent intent = new Intent(SignUpActivity.this, SetUpProfileActivity.class);
                                    intent.putExtra("id",uid);
                                    intent.putExtra("password",passWord);
                                    intent.putExtra("email",email);
                                    startActivity(intent);
                                    finish();
//                                }
//                            });
//                            Toast.makeText(SignUpActivity.this, "Registration Complete", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(SignUpActivity.this, Set.class));
//                            finish();
                            //set Up profile
                        } else {
                            Toast.makeText(SignUpActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();

                        }

                    }
                });

            }
        });


    }
}
