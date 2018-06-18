package com.nexdev.enyason.jan.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.nexdev.enyason.jan.EnrolledCourses;
import com.nexdev.enyason.jan.JanCourses;
import com.nexdev.enyason.jan.R;
import com.nexdev.enyason.jan.adapter.EnrolledCoursesAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by enyason on 6/8/18.
 */

public class EnrolledCoursesFragment extends Fragment {


    RecyclerView recyclerViewEnrolledCousres;
    RecyclerView.Adapter adapter;

    ProgressBar progressBar;

    List<EnrolledCourses> enrolledCoursesList;

    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        return inflater.inflate(R.layout.fragment_enrolled_courses, null);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        enrolledCoursesList = new ArrayList<>();

//        enrolledCoursesList.add(new JanCourses("ITS TYME","The ITS TYME initiative builds on the highly acclaimed JA Company Program® designed to provide basic business education to young people through a variety of hands-on activities supporting a diverse range of learners.  Through the Company Program, students develop a better understanding of the relationship between what they learn at school and their successful participation in a worldwide economy.  The experience enhances students’ understanding of the classroom curriculum and encourages them to use innovative thinking to acquire business skills, build positive attitudes, expand and enhance their career options and aspirations.","2 weeks","Enya Emmanuel"));
//
//        enrolledCoursesList.add(new JanCourses("JA OUR NATION","JA Our Nation introduces  students to the intersection of work readiness and upper elementary grades social studies learning objectives. Through hands-on classroom activities, the program provides students with practical information about the nation’s free market system and how it serves as an economic engine for businesses and careers. The curriculum also introduces the need for entrepreneurial and innovative thinking to meet the requirements of high-growth, high-demand careers and the concept of globalization in business.","6 Weeks","Nsikak Nyong"));

        recyclerViewEnrolledCousres = view.findViewById(R.id.recycler_view_enrolled_courses);
        progressBar = view.findViewById(R.id.pb_enrolled_courses);


        firebaseFirestore.collection("enrolled_courses").document(firebaseAuth.getUid())
                .collection("my_courses").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {


                if (task.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);

                    for (DocumentSnapshot documentSnapshot : task.getResult()) {


                        String title = documentSnapshot.getString("title");
                        String description = documentSnapshot.getString("description");
                        String duration = documentSnapshot.getString("duration");
                        String courseId = documentSnapshot.getString("course_id");
                        Log.i("Db ", title);

                        enrolledCoursesList.add(new EnrolledCourses(title, description, duration,courseId ));
                        adapter.notifyDataSetChanged();
                    }


                } else {
                    progressBar.setVisibility(View.GONE);
                }

            }
        });


        adapter = new EnrolledCoursesAdapter(enrolledCoursesList, getContext());


        recyclerViewEnrolledCousres.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerViewEnrolledCousres.setAdapter(adapter);


    }


}
