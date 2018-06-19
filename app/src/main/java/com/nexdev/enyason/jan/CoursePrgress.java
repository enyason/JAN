package com.nexdev.enyason.jan;

/**
 * Created by enyason on 6/18/18.
 */

public class CoursePrgress {


    String title;
    //    String description;
    Double mNunberOfWeeks;
    Double mProgress;


    public CoursePrgress(String title, Double numberOfWeeks,  Double mProgress) {
        this.title = title;
//        this.description = description;
       this.mNunberOfWeeks = numberOfWeeks;
        this.mProgress = mProgress;
    }


    public CoursePrgress(String title) {
        this.title = title;

    }


    public String getTitle() {
        return title;
    }

//    public String getDescription() {
//        return description;
//    }

    public Double getNumberOfWeeks() {
        return mNunberOfWeeks;
    }

    public Double getmProgress() {
        return mProgress;
    }
}
