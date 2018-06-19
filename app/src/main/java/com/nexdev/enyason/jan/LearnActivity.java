package com.nexdev.enyason.jan;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.nexdev.enyason.jan.adapter.WeekListCoursesAdapter;
import com.nexdev.enyason.jan.fragment.PreTestFragment;

import java.util.ArrayList;
import java.util.List;

public class LearnActivity extends AppCompatActivity implements CustomClickListener {

    RecyclerView recyclerViewEnrolledCousres;
    public static RecyclerView.Adapter adapter;

    List<WeekList> weekListCoursesList;
    List<WeekListCheck> weekListCheck;
    public static WeekListCheck weekListCheckFlag;

    Toolbar toolbar;
    ProgressBar progressBar;

    FirebaseFirestore firebaseFirestore;

    public static String cid;
    public static String lessonId;

    WeekList weekList;

    CollectionReference reference;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        progressBar = findViewById(R.id.pb_learn);
        toolbar = findViewById(R.id.tool_bar_h);
        setSupportActionBar(toolbar);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();


        Intent intent = getIntent();

        cid = intent.getStringExtra("courseId");


        reference = firebaseFirestore.collection("week_list").document("courses").collection(cid);


        weekListCoursesList = new ArrayList<>();
        weekListCheck = new ArrayList<>();


        getPreTestCheckList(); //get checks for weekly lessons


        reference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {


                if (task.isSuccessful()) {

                    progressBar.setVisibility(View.GONE);
                    for (DocumentSnapshot documentSnapshot : task.getResult()) {

                        String id = documentSnapshot.getString("lesson_id");
                        String title = documentSnapshot.getString("lesson_title");
                        String video = documentSnapshot.getString("videourl");
                        String lesson = documentSnapshot.getString("lesson");

                        if (id != null) {
                            Log.i("id ", id);

                        }

                        weekListCoursesList.add(new WeekList(id, title, video, lesson));
                        adapter.notifyDataSetChanged();
                    }


                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                }

            }
        });


//        firebaseFirestore.collection("week_list").document("courses").collection(cid).addSnapshotListener(this,new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
//
//
//                //                    progressBar.setVisibility(View.GONE);
//
//                for (DocumentChange doc: documentSnapshots.getDocumentChanges()) {
//
//
//                    if (doc.getType() == DocumentChange.Type.ADDED) {
//
//                        String id = doc.getDocument().getString("lesson_id");
//                        String title = doc.getDocument().getString("lesson_title");
//                        String video = doc.getDocument().getString("videourl");
//                        String lesson = doc.getDocument().getString("lesson");
//                    boolean isPreTestTaken = doc.getDocument().getBoolean("izPreTestTaken");
//
//                        if (id != null) {
//                            Log.i("id ", id);
//
//                        }
//
//
//                        weekListCoursesList.add(new WeekList(id,title, video, lesson,isPreTestTaken));
//                        adapter.notifyDataSetChanged();
//
//                    } else if (doc.getType() == DocumentChange.Type.MODIFIED) {
//
//                        weekListCoursesList.clear();
//
//                        String id = doc.getDocument().getString("lesson_id");
//                        String title = doc.getDocument().getString("lesson_title");
//                        String video = doc.getDocument().getString("videourl");
//                        String lesson = doc.getDocument().getString("lesson");
//                        boolean isPreTestTaken = doc.getDocument().getBoolean("izPreTestTaken");
//
//                        if (id != null) {
//                            Log.i("id ", id);
//
//                        }
//
//
//
//
//                        weekListCoursesList.add(new WeekList(id,title, video, lesson,isPreTestTaken));
//                        adapter.notifyDataSetChanged();
//
//
//
//                    }
//
//
//                }
//
//
//            }
//        });


        recyclerViewEnrolledCousres = findViewById(R.id.recycler_view_week_list);


        adapter = new WeekListCoursesAdapter(weekListCoursesList, this, this);


        recyclerViewEnrolledCousres.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewEnrolledCousres.setAdapter(adapter);


    }

    public void getPreTestCheckList() {


        firebaseFirestore.collection("students").document(firebaseAuth.getUid())
                .collection("week_list").document("course").collection(cid).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {


                        for (DocumentSnapshot documentSnapshot : task.getResult()) {

                            boolean isPreTestTaken = documentSnapshot.getBoolean("izPreTestTaken");

                            weekListCheck.add(new WeekListCheck(isPreTestTaken));

                            Log.i("WeaekListCheck size ", "" + weekListCheck.size());

                        }
                    }
                });


    }

    @Override
    protected void onStart() {
        super.onStart();


//
//        firebaseFirestore.collection("week_list").document("courses").collection(cid).addSnapshotListener(this,new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
//
//
//                //                    progressBar.setVisibility(View.GONE);
//
////                for (DocumentChange doc: documentSnapshots.getDocumentChanges()) {
////
////
////                    if (doc.getType() == DocumentChange.Type.ADDED) {
////
////                        String id = doc.getDocument().getString("lesson_id");
////                        String title = doc.getDocument().getString("lesson_title");
////                        String video = doc.getDocument().getString("videourl");
////                        String lesson = doc.getDocument().getString("lesson");
////                    boolean isPreTestTaken = doc.getDocument().getBoolean("izPreTestTaken");
////
////                        if (id != null) {
////                            Log.i("id ", id);
////
////                        }
////
////
////                        weekListCoursesList.add(new WeekList(id,title, video, lesson,isPreTestTaken));
////                        adapter.notifyDataSetChanged();
////
////                    } else if (doc.getType() == DocumentChange.Type.MODIFIED) {
////
////                        weekListCoursesList.clear();
////
////                        String id = doc.getDocument().getString("lesson_id");
////                        String title = doc.getDocument().getString("lesson_title");
////                        String video = doc.getDocument().getString("videourl");
////                        String lesson = doc.getDocument().getString("lesson");
////                        boolean isPreTestTaken = doc.getDocument().getBoolean("izPreTestTaken");
////
////                        if (id != null) {
////                            Log.i("id ", id);
////
////                        }
//
//
//                for (DocumentSnapshot documentSnapshot : documentSnapshots) {
//
//                    String id = documentSnapshot.getString("lesson_id");
//                    String title = documentSnapshot.getString("lesson_title");
//                    String video = documentSnapshot.getString("videourl");
//                    String lesson = documentSnapshot.getString("lesson");
//                    boolean isPreTestTaken = documentSnapshot.getBoolean("izPreTestTaken");
//
//                    if (id != null) {
//                        Log.i("id ", id);
//
//                    }
//                    weekListCoursesList.add(new WeekList(id,title, video, lesson,isPreTestTaken));
//                    adapter.notifyDataSetChanged();
//                }
//
//
//
//
//
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_mentor:
                startActivity(new Intent(this, MentorActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    private void showEditDialog() {
        FragmentManager fm = getSupportFragmentManager();
        PreTestFragment preTestFragment = PreTestFragment.newInstance("Some Title");
        preTestFragment.show(fm, "fragment_edit_name");
    }

    @Override
    public void onItemClicked(int itemId, String lessonId) {

        this.lessonId = lessonId;

//        reference.document(lessonId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//
//                Boolean bol = task.getResult().getBoolean("izPreTestTaken");
//
//
//            }
//        });


        weekListCheckFlag = weekListCheck.get(itemId);

        Log.i("flag1", "" + (weekListCheckFlag.getIzPreTesttaken()));


        weekList = weekListCoursesList.get(itemId);
        if (weekListCheckFlag.getIzPreTesttaken()) {

            Toast.makeText(this, lessonId, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(LearnActivity.this, ClassContentActivity.class);
            intent.putExtra("videoUrl", weekList.getVideoUrl());
            startActivity(intent);

        } else {
            Toast.makeText(this, lessonId, Toast.LENGTH_SHORT).show();
            showEditDialog();

        }
    }
}
