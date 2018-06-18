package com.nexdev.enyason.jan;

import java.io.Serializable;

/**
 * Created by enyason on 6/9/18.
 */

public class EnrolledCourses implements Serializable{

    String title;
//    String description;
    String duration;
    String course_id;


    public EnrolledCourses(String title, String description, String duration, String course_id) {
        this.title = title;
//        this.description = description;
        this.duration = duration;
        this.course_id = course_id;
    }


    public EnrolledCourses(String title) {
        this.title = title;

    }


    public String getTitle() {
        return title;
    }

//    public String getDescription() {
//        return description;
//    }

    public String getDuration() {
        return duration;
    }

    public String getCourse_id() {
        return course_id;
    }
}
