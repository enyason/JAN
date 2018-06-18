package com.nexdev.enyason.jan;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by enyason on 6/17/18.
 */

public class WriteToDataBase {


    private  FirebaseFirestore firebaseFirestore;

    public WriteToDataBase(FirebaseFirestore firebaseFirestore) {

        this.firebaseFirestore = firebaseFirestore;
    }

    public void setData(){

        CollectionReference reference = firebaseFirestore.collection("jan_courses");


        Map<String, Object> nestedData = new HashMap<>();
        nestedData.put("a", 5);
        nestedData.put("b", true);

       ;

        Map<String,Object> a = new HashMap<>();
//
        a.put("title", "JA Our Nation");
        a.put("description","JA Our Nation introduces  students to the intersection of work readiness and upper elementary grades social studies learning objectives. Through hands-on classroom activities, the program provides students with practical information about the nation’s free market system and how it serves as an economic engine for businesses and careers. The curriculum also introduces the need for entrepreneurial and innovative thinking to meet the requirements of high-growth, high-demand careers and the concept of globalization in business.");

        a.put("duration","6 Weeks");

        a.put("program_level","Elementary School (Primary 4, Primary 5, Primary 6)");
//        a.put("course_id",reference.getId())

        reference.document("c1").set(a);
//
//        dMap.put("objectExample",nestedData);

//        firebaseFirestore.collection("cour").document("1234").set(dMap);


        Map<String,Object> b = new HashMap<>();
//
        b.put("title","JA OUR CITY");
        b.put("description","JA Our City introduces students to the basics of financial literacy, the characteristics of cities, and how people and businesses in cities manage their money.\n" +
                "\n" +
                "Students will explore zoning found within a city; the importance of money to a city, paying taxes, and how people use different methods to pay for goods and services. They also will explore how financial institutions, entrepreneurs, and news media contribute to the financial well-being of a city.");
        b.put("duration","5 Weeks");

        b.put("program_level","Elementary School (Primary 4, Primary 5, Primary 6)");

        reference.document("c2").set(b);


        Map<String,Object> c = new HashMap<>();
//
        c.put("title","JA IT’S MY BUSINESS");
        c.put("description","JA It’s My Business! encourages students to use entrepreneurial thinking as they explore higher education and career choices. Students participate in fun, challenging activities such as an entrepreneurial quiz game, completing a blueprint for a teen club, participating in an auction of businesses, and creating entrepreneur profile cards.\n" +
                "\n" +
                "The program targets students in Junior Secondary school (JSS 1-3). It is composed of six sessions, each 60 minutes in length. Materials are packaged in a self-contained kit that includes detailed plans for the volunteer and materials for 30 students.");
        c.put("duration","5 Weeks");

        c.put("program_level","Junior Secondary School");

        reference.document("c3").set(c);



        Map<String,Object> d = new HashMap<>();
//
        d.put("title","JA BE ENTREPRENEURIAL");
        d.put("description","JA Be Entrepreneurial introduces students to the essential elements of a practical business plan and then challenges them to start an entrepreneurial venture while still in Senior Secondary School.\n" +
                "\n" +
                "Students will learn about advertising, competitive advantages, financing, marketing, and product development.");

        d.put("duration","7 Weeks");

        d.put("program_level","Senior Secondary School");

        reference.document("c4").set(d);

    }
}
