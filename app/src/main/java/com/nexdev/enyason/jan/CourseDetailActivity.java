package com.nexdev.enyason.jan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CourseDetailActivity extends AppCompatActivity {

    TextView textViewTitle,textViewDescription;

    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;

    JanCourses janCourses;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);


        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        Intent intent = getIntent();

        janCourses = (JanCourses) intent.getSerializableExtra("janCourses");

        textViewTitle = findViewById(R.id.text_view_title_detail);
        textViewDescription = findViewById(R.id.text_view_description_detail);


        textViewTitle.setText(janCourses.getTitle());
        textViewDescription.setText(janCourses.getDescription());


    }

    public void enrollForCourse(View view) {

        progressDialog.setMessage("Preparing Cousere Pleas wait...");
        progressDialog.show();

        Map<String,Object> dMap = new HashMap<>();
////
        dMap.put("title", janCourses.getTitle());
        dMap.put("duration", "2 weeks");
        dMap.put("course_id",janCourses.course_id);

        firebaseFirestore.collection("enrolled_courses").document(firebaseAuth.getUid())
                .collection("my_courses").add(dMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {

                progressDialog.dismiss();

            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {


                    }
                });



    }
}
