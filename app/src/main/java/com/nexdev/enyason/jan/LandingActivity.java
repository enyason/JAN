package com.nexdev.enyason.jan;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class LandingActivity extends AppCompatActivity {


    FirebaseFirestore firebaseFirestore;

    FirebaseAuth firebaseAuth;

    DocumentReference janCoursesRef;
    CollectionReference jaCouresWeekListRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);


        firebaseFirestore = FirebaseFirestore.getInstance();
//        janCoursesRef = firebaseFirestore.collection("jan_courses").document();
        jaCouresWeekListRef = firebaseFirestore.collection("week_list").document("courses").collection("c3");

        firebaseAuth = FirebaseAuth.getInstance();
    }


    public void goToSignUpPage(View view) {


//        String cousreUid = janCoursesRef.getId();
////
//        Map<String,Object> dMap = new HashMap<>();
//////
//        dMap.put("title", "JA Our Nation");
//        dMap.put("duration", "2 weeks");
//
//        dMap.put("courseUid",cousreUid);
//
//        janCoursesRef.getId();
//
//
////
//        janCoursesRef.set(dMap);
//


        Map<String,Object> preTest = new HashMap<>();

        preTest.put("a","it is a ");
        preTest.put("b","it is b");
        preTest.put("c","it is c");
        preTest.put("d","it is d");

////
        Map<String, Object> weekListMap = new HashMap<>();
////
        weekListMap.put("lesson_title", "Introduction to Entrepreneurship");
        weekListMap.put("lesson", "lesson 01");
        weekListMap.put("description", "2 weeks");
        weekListMap.put("videourl", "youtube.com");
        weekListMap.put("What is Jan",preTest);

        Map<String, Object> weekListMap1 = new HashMap<>();
////
        weekListMap1.put("lesson_title", "Introduction to Entrepreneurship");
        weekListMap1.put("lesson", "lesson 02");
        weekListMap1.put("description", "another one");
        weekListMap1.put("videourl", "youtube.com");
        weekListMap1.put("What is Jan",preTest);


        Map<String, Object> weekListMap2 = new HashMap<>();
////
        weekListMap2.put("lesson_title", "Introduction to Entrepreneurship");
        weekListMap2.put("lesson", "Oh yes");
        weekListMap2.put("description", "2 weeks");
        weekListMap2.put("videourl", "youtube.com");
        weekListMap2.put("What is Jan",preTest);



        jaCouresWeekListRef.add(weekListMap);

        jaCouresWeekListRef.add(weekListMap1);

        jaCouresWeekListRef.add(weekListMap2);



//        firebaseFirestore.collection("jan_courses").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//
//
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//
//
//                if (task.isSuccessful()) {
//                    for (DocumentSnapshot document : task.getResult()) {
//
//                        String title = document.getString("title");
//
//
//                        Log.i("Db",title);
//
//                    }
//
//
//
//                } else {
//                }
//
//            }
//        });


//        WriteToDataBase writeToDataBase = new WriteToDataBase(firebaseFirestore);

//        writeToDataBase.setData();

        //courses enrolled now working

//        Map<String,Object> dMap = new HashMap<>();
////
//        dMap.put("title", "JA Our Nation");
//        dMap.put("course_id","c1");
//        dMap.put("duration", "2 weeks");
//
//        Map<String,Object> preTest = new HashMap<>();




//        firebaseFirestore.collection("enrolled_courses").document(firebaseAuth.getUid()).collection("my_courses").add(dMap);


//        startActivity(new Intent(LandingActivity.this,SignUpActivity.class));
//        firebaseFirestore.collection("course").get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot documentSnapshots) {
//
//                        for (DocumentSnapshot doc : documentSnapshots) {
//
//                            String name = doc.getString("title");
//                            Log.i("TAG ",name);
//                        }
//
//                    }
//                });

//        Map<String,Object> dMap = new HashMap<>();
//
//        dMap.put("title", "JA Our Nation");
//        dMap.put("description","JA Our Nation introduces  students to the intersection of work readiness and upper elementary grades social studies learning objectives. Through hands-on classroom activities, the program provides students with practical information about the nation’s free market system and how it serves as an economic engine for businesses and careers. The curriculum also introduces the need for entrepreneurial and innovative thinking to meet the requirements of high-growth, high-demand careers and the concept of globalization in business.");
//
//        dMap.put("duration","6 Weeks");
//        dMap.put("program_level","Elementary School (Primary 4, Primary 5, Primary 6)");
////
////        firebaseFirestore.collection("course").document("course_details").set();
//
//        firebaseFirestore.collection("cour").document("1234").set(dMap);


    }

    public void goToSignInPage(View view) {
        startActivity(new Intent(LandingActivity.this, SignInActivity.class));

    }
}
