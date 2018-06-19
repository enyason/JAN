package com.nexdev.enyason.jan.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.nexdev.enyason.jan.CoursePrgress;
import com.nexdev.enyason.jan.R;
import com.nexdev.enyason.jan.adapter.DashboardAdapter;

import java.util.ArrayList;
import java.util.List;

import jp.co.recruit_mp.android.circleprogressview.CircleProgressView;

/**
 * Created by enyason on 6/8/18.
 */

public class DashBoardFragment extends Fragment {


    ProgressBar progressBar;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter adapter;
    List<CoursePrgress> coursePrgressList;

    FirebaseAuth firebaseAuth;

    FirebaseFirestore firebaseFirestore;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        return inflater.inflate(R.layout.fragment_dash_board, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        progressBar = view.findViewById(R.id.pb_dashboard);
        mRecyclerView = view.findViewById(R.id.rv_dashboard);

        coursePrgressList = new ArrayList<>();
        adapter = new DashboardAdapter(coursePrgressList, getContext());
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));


        firebaseFirestore.collection("students").document(firebaseAuth.getUid()).collection("course_progress")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {


                if (task.isSuccessful()) {

                    progressBar.setVisibility(View.GONE);

                    for (DocumentSnapshot documentSnapshot : task.getResult()) {

                        String title = documentSnapshot.getString("course_title");
                        Double duration = documentSnapshot.getDouble("duration");
                        Double lessonsTaken = documentSnapshot.getDouble("number_of_lessons_taken");
//                        Double lessonsLeft = documentSnapshot.getDouble("number_of_lessons_left");


                        Log.i("title ", title);
                        coursePrgressList.add(new CoursePrgress(title,duration,lessonsTaken));
                        adapter.notifyDataSetChanged();

                    }
                }


            }
        });


        mRecyclerView.setAdapter(adapter);


    }
}
