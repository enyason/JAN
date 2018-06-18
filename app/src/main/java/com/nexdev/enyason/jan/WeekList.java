package com.nexdev.enyason.jan;

/**
 * Created by enyason on 6/9/18.
 */

public class WeekList {

    String videoUrl;
    String lessonTitle;
    String currentWeek;
    String lessonId;
    boolean izPreTesttaken;

    public WeekList(String lessonId,String lessonTitle,String videoUrl, String currentWeek,boolean izPreTesttaken) {
        this.videoUrl = videoUrl;
        this.lessonTitle = lessonTitle;
        this.currentWeek = currentWeek;
        this.izPreTesttaken = izPreTesttaken;
        this.lessonId = lessonId;

    }


    public String getVideoUrl() {
        return videoUrl;
    }

    public String getCurrentWeek() {
        return currentWeek;
    }

    public String getLessonTitle() {
        return lessonTitle;
    }

    public String getLessonId() {
        return lessonId;
    }

    public boolean getIzPreTesttaken() {
        return izPreTesttaken;
    }


    public void setIzPreTesttaken(boolean izPreTesttaken) {
        this.izPreTesttaken = izPreTesttaken;
    }
}
