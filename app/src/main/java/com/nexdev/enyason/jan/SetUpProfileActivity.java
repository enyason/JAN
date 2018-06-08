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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SetUpProfileActivity extends AppCompatActivity {


    EditText etSurName, etOtherNAmes, etPhone, etAddress, etSchool;
    Button btnSetUpProfile;
    ProgressDialog progressDialog;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    FirebaseFirestore db;


    String userUid;
    String email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up_profile);


        db = FirebaseFirestore.getInstance();


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Setting up account...");
        progressDialog.setCanceledOnTouchOutside(false);

        btnSetUpProfile = findViewById(R.id.btn_set_up_profile);


        etSurName = findViewById(R.id.et_sur_name_set_up);
        etOtherNAmes = findViewById(R.id.et_first_name_set_up);
        etPhone = findViewById(R.id.et_phone_set_up);
        etAddress = findViewById(R.id.et_adress_set_up);
        etSchool = findViewById(R.id.et_school_set_up);


        firebaseAuth = FirebaseAuth.getInstance();

//        firebaseUser = firebaseAuth.getCurrentUser();
//        firebaseUser.getUid();

        Intent intent = getIntent();

        userUid = intent.getStringExtra("id");
        email = intent.getStringExtra("email");
        password = intent.getStringExtra("password");


        btnSetUpProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String surNamae = etSurName.getText().toString().trim();
                String otherNames = etOtherNAmes.getText().toString().trim();
                String phone = etPhone.getText().toString().trim();
                String address = etAddress.getText().toString().trim();
                String school = etSchool.getText().toString().trim();


                // Create a new user with a first and last name
                Map<String, Object> user = new HashMap<>();
                user.put("sur", surNamae);
                user.put("other_names",otherNames );
                user.put("phone", phone);
                user.put("address",address);
                user.put("school",school);

                progressDialog.show();


//
//                db.collection("users")
//                        .add(user)
//                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                            @Override
//                            public void onSuccess(DocumentReference documentReference) {
//
//
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                            }
//                        });
//





                db.collection("students").document(userUid).set(user)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

//                                progressDialog.dismiss();
                                progressDialog.setMessage("Preparing to Log In");
                                Toast.makeText(SetUpProfileActivity.this,"Set up complete",Toast.LENGTH_SHORT).show();
//                                startActivity(new Intent(SetUpProfileActivity.this,SignInActivity.class));
//                                finish();

//                                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//
//
//                                    @Override
//                                    public void onComplete(@NonNull Task<AuthResult> task) {
//
//                                        if (task.isSuccessful()) {
//                                            progressDialog.dismiss();
//                                            startActivity(new Intent(SetUpProfileActivity.this,HomeActivity.class));
//
//                                        }
//
//
//
//                                    }
//                                });

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {


                            @Override
                            public void onFailure(@NonNull Exception e) {


                                progressDialog.dismiss();
                                Toast.makeText(SetUpProfileActivity.this,"Set up failed",Toast.LENGTH_SHORT).show();


                            }
                        });















            }
        });
    }
}
