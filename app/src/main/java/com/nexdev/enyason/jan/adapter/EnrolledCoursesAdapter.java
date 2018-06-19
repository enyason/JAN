package com.nexdev.enyason.jan.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nexdev.enyason.jan.CourseDetailActivity;
import com.nexdev.enyason.jan.EnrolledCourses;
import com.nexdev.enyason.jan.JanCourses;
import com.nexdev.enyason.jan.LearnActivity;
import com.nexdev.enyason.jan.R;

import java.io.Serializable;
import java.util.List;

/**
 * Created by enyason on 6/9/18.
 */

public class EnrolledCoursesAdapter extends RecyclerView.Adapter<EnrolledCoursesAdapter.EnrolledCoursesViewHolder> {



    List<EnrolledCourses> janCoursesList;

    Context context;


    public EnrolledCoursesAdapter(List<EnrolledCourses> enrolledCoursesList, Context context) {

        this.janCoursesList = enrolledCoursesList;
        this.context = context;
    }

    @NonNull
    @Override
    public EnrolledCoursesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.jan_courses_layout,parent,false);

        return new EnrolledCoursesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EnrolledCoursesViewHolder holder, int position) {



        final EnrolledCourses  enrolledCourses = janCoursesList.get(position);

        holder.tvTitle.setText(enrolledCourses.getTitle());
        holder.tvDuration.setText(enrolledCourses.getDuration() + "weeks");

//        Picasso.get()
//                .load(janCourses.getImageUrl())
//                .into(holder.imageView);

        holder.rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,LearnActivity.class);
                intent.putExtra("courseId", enrolledCourses.getCourse_id());

                context.startActivity(intent);


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

            tvTitle = itemView.findViewById(R.id.text_view_title);
            tvDuration = itemView.findViewById(R.id.text_view_duration);
            imageView = itemView.findViewById(R.id.image_view_jan_courses);
            rowView = itemView;

        }
    }

}
