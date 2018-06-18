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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.nexdev.enyason.jan.JanCourses;
import com.nexdev.enyason.jan.R;
import com.nexdev.enyason.jan.adapter.JanCoursesAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by enyason on 6/8/18.
 */

public class JanCoursesFragment extends Fragment {


    RecyclerView recyclerViewJanCousres;
    RecyclerView.Adapter adapter;

    List<JanCourses> janCoursesList;

    FirebaseFirestore firebaseFirestore;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        firebaseFirestore = FirebaseFirestore.getInstance();

        return inflater.inflate(R.layout.fragment_jan_courses, null);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        janCoursesList = new ArrayList<>();

        final ProgressBar progressBar = view.findViewById(R.id.pb_jan_courses);


        recyclerViewJanCousres = view.findViewById(R.id.recycler_view_jan_courses);



        firebaseFirestore.collection("jan_courses").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                progressBar.setVisibility(View.INVISIBLE);

                for (DocumentSnapshot documentSnapshot : task.getResult()) {

                    String title = documentSnapshot.getString("title");
                    String description = documentSnapshot.getString("description");
                    String duration = documentSnapshot.getString("duration");
                    String courseId = documentSnapshot.getString("course_id");

                    Log.i("Db ",courseId);

                            janCoursesList.add(new JanCourses(title,description,duration,courseId));
                            adapter.notifyDataSetChanged();
                }

            }
        });



//        janCoursesList.add(new JanCourses("ITS TYME","The ITS TYME initiative builds on the highly acclaimed JA Company Program® designed to provide basic business education to young people through a variety of hands-on activities supporting a diverse range of learners.  Through the Company Program, students develop a better understanding of the relationship between what they learn at school and their successful participation in a worldwide economy.  The experience enhances students’ understanding of the classroom curriculum and encourages them to use innovative thinking to acquire business skills, build positive attitudes, expand and enhance their career options and aspirations.","2 weeks","Enya Emmanuel"));
//
//        janCoursesList.add(new JanCourses("JA OUR NATION","JA Our Nation introduces  students to the intersection of work readiness and upper elementary grades social studies learning objectives. Through hands-on classroom activities, the program provides students with practical information about the nation’s free market system and how it serves as an economic engine for businesses and careers. The curriculum also introduces the need for entrepreneurial and innovative thinking to meet the requirements of high-growth, high-demand careers and the concept of globalization in business.","6 Weeks","Nsikak Nyong"));
//
//        janCoursesList.add(new JanCourses("JA IT’S MY BUSINESS","ABOUT THE PROGRAM\n" +
//                "JA It’s My Business! encourages students to use entrepreneurial thinking as they explore higher education and career choices. Students participate in fun, challenging activities such as an entrepreneurial quiz game, completing a blueprint for a teen club, participating in an auction of businesses, and creating entrepreneur profile cards.\n" +
//                "\n" +
//                "The program targets students in Junior Secondary school (JSS 1-3). It is composed of six sessions, each 60 minutes in length. Materials are packaged in a self-contained kit that includes detailed plans for the volunteer and materials for 30 students.","6 Weeks","Divine Edung"));
//        janCoursesList.add(new JanCourses("JA BE ENTREPRENEURIAL","JA Be Entrepreneurial introduces students to the essential elements of a practical business plan and then challenges them to start an entrepreneurial venture while still in Senior Secondary School.\n" +
//                "\n" +
//                "Students will learn about advertising, competitive advantages, financing, marketing, and product development.\n" +
//                "\n","7 Weeks","Samuel Umoren"));
//
//        janCoursesList.add(new JanCourses("JA CAREER SUCCESS","A Career Success equips students with the tools and skills required to earn and keep a job in high-growth career industries.","7 weeks","Edet Emmanuel"));



        adapter = new JanCoursesAdapter(janCoursesList,getContext());


        recyclerViewJanCousres.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerViewJanCousres.setAdapter(adapter);




    }
}
