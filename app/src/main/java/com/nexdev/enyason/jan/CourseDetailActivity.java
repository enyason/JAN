package com.nexdev.enyason.jan;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;


public class CourseDetailActivity extends AppCompatActivity {

    TextView textViewTitle, textViewDescription;

    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;

    JanCourses janCourses;

    ProgressDialog progressDialog;
    Button buttonEnrollCourse;

    int state = 0;

    CollectionReference reference;

    double duration;

    Map<String, Object> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);


        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();



        progressDialog = new ProgressDialog(this);
        buttonEnrollCourse = findViewById(R.id.btn_enroll_course);

        Intent intent = getIntent();

        janCourses = (JanCourses) intent.getSerializableExtra("janCourses");


        reference = firebaseFirestore.collection("students").document(firebaseAuth.getUid())
                .collection("week_list").document("course").collection(janCourses.getCourse_id());

         duration = janCourses.getDuration();

       map = new HashMap<>();
////


        textViewTitle = findViewById(R.id.text_view_title_detail);
        textViewDescription = findViewById(R.id.text_view_description_detail);


        textViewTitle.setText(janCourses.getTitle());
        textViewDescription.setText(janCourses.getDescription());


//
        buttonEnrollCourse.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                progressDialog.setMessage("Preparing Course Please wait...");
                progressDialog.show();


                Log.i("isId", "" + state);

                if (state == 1) {
                    progressDialog.dismiss();
                    Snackbar.make(view, "Already Enrolled for this course!", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                izStudentEnroll(view);


            }
        });


    }

    public void enrollStudent(final View view) {


//        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(CourseDetailActivity.this);
//        alertDialog.setMessage("You Have Registered for "+janCourses.getTitle());
////        alertDialog.setCancelable(false);
//
//        alertDialog.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//
//                dialog.dismiss();
//
//            }
//        });


        final Map<String, Object> progressMap = new HashMap<>();
////
        progressMap.put("course_title", janCourses.getTitle());
        progressMap.put("duration", janCourses.getDuration());
        progressMap.put("progress_percent",0);
        progressMap.put("number_of_lessons_taken",0);
        progressMap.put("number_of_lessons_left",janCourses.getDuration());


        Map<String, Object> dMap = new HashMap<>();
////
        dMap.put("title", janCourses.getTitle());
        dMap.put("duration", janCourses.getDuration());
        dMap.put("course_id", janCourses.getCourse_id());


        final Map<String, Object> objectMap = new HashMap<>();

        objectMap.put("student_id", firebaseAuth.getUid());

        firebaseFirestore.collection("enrolled_courses").document(firebaseAuth.getUid())
                .collection("my_courses").add(dMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {


                firebaseFirestore.collection("jan_courses").
                        document(janCourses.getCourse_id()).collection("student_enrolled")
                        .add(objectMap)
                        .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {


                                if (task.isSuccessful()) {

                                    firebaseFirestore.collection("students").
                                            document(firebaseAuth.getUid()).collection("course_progress")
                                            .document(janCourses.getCourse_id()).
                                            set(progressMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            progressDialog.dismiss();

                                            if (task.isSuccessful()) {

                                                map.put("izPreTestTaken", false);

                                                for (int i = 1; i<=duration;i++) {

                                                    reference.document("lesson_0"+i).set(map);

                                                }

                                                Snackbar.make(view, "You Have Enrolled for " + janCourses.getTitle(), Snackbar.LENGTH_LONG).show();


                                                buttonEnrollCourse.setOnClickListener(new View.OnClickListener() {

                                                    @Override
                                                    public void onClick(View v) {

                                                        Snackbar.make(v, "Already Enrolled for this course!", Snackbar.LENGTH_SHORT).show();

                                                    }
                                                });

                                            }
                                        }
                                    });




//                                    finish();

                                } else {

                                    progressDialog.dismiss();


                                }


                            }
                        });


            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {


                    }
                });
    }


    public void izStudentEnroll(final View view) {


        firebaseFirestore.collection("jan_courses").document(janCourses.getCourse_id()).collection("student_enrolled")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {


                if (task.isSuccessful()) {

                    for (DocumentSnapshot documentSnapshot : task.getResult()) {


                        String studentId = documentSnapshot.getString("student_id");

                        if (studentId.equals(firebaseAuth.getUid())) {

//                            idFromCourse = studentId;
                            state = 1;
                            Log.i("isId", "" + state);
                            Snackbar.make(view, "Already Enrolled for this course!", Snackbar.LENGTH_SHORT).show();

                        } else {
                            state = 0;
                        }

                        break;

                    }

                    if (state == 1) {
                        progressDialog.dismiss();
                    } else {

                        Log.i("isId", "" + state);

                        enrollStudent(view);


                    }


                } else {


                    //
                }

            }
        });


    }
}
