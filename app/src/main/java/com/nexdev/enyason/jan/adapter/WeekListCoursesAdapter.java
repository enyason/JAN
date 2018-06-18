package com.nexdev.enyason.jan.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nexdev.enyason.jan.ClassContentActivity;
import com.nexdev.enyason.jan.CustomClickListener;
import com.nexdev.enyason.jan.JanCourses;
import com.nexdev.enyason.jan.LearnActivity;
import com.nexdev.enyason.jan.R;
import com.nexdev.enyason.jan.WeekList;

import java.util.List;

/**
 * Created by enyason on 6/9/18.
 */

public class WeekListCoursesAdapter extends RecyclerView.Adapter<WeekListCoursesAdapter.EnrolledCoursesViewHolder> {



    List<WeekList> janCoursesList;

    Context context;


    CustomClickListener clickListener;
    public WeekListCoursesAdapter(List<WeekList> enrolledCoursesList, Context context,CustomClickListener clickListener) {

        this.janCoursesList = enrolledCoursesList;
        this.context = context;
        this.clickListener = clickListener;
    }



    @NonNull
    @Override
    public EnrolledCoursesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.week_list_layout,parent,false);

        return new EnrolledCoursesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EnrolledCoursesViewHolder holder, final int position) {



        final WeekList weekList = janCoursesList.get(position);

        holder.tvTitle.setText(weekList.getLessonTitle());
        holder.tvDuration.setText(weekList.getCurrentWeek());

        final Boolean testFlag = weekList.getIzPreTesttaken();
        final String lessonId = weekList.getLessonId();



//        Picasso.get()
//                .load(janCourses.getImageUrl())
//                .into(holder.imageView);

        holder.rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clickListener.onItemClicked(testFlag,position,lessonId);

            }
        });

    }



    @Override
    public int getItemCount() {
        if (janCoursesList != null) {
            return janCoursesList.size();
        }

        return 0;
    }


    class EnrolledCoursesViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle;
        TextView tvDuration;
        ImageView imageView;

        View rowView;
        public EnrolledCoursesViewHolder(View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.text_view_lesson_title);
            tvDuration = itemView.findViewById(R.id.text_view_week);
            rowView = itemView;

        }
    }

}
