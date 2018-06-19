package com.nexdev.enyason.jan.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nexdev.enyason.jan.CourseDetailActivity;
import com.nexdev.enyason.jan.JanCourses;
import com.nexdev.enyason.jan.R;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

/**
 * Created by enyason on 6/9/18.
 */

public class JanCoursesAdapter extends RecyclerView.Adapter<JanCoursesAdapter.JanCoursesViewHolder> {


    List<JanCourses> janCoursesList;

    Context context;
    public JanCoursesAdapter(List<JanCourses> janCoursesList, Context context) {
        this.janCoursesList = janCoursesList;
        this.context = context;
    }

    @NonNull
    @Override
    public JanCoursesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.jan_courses_layout,parent,false);

        return new JanCoursesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JanCoursesViewHolder holder, int position) {



        final JanCourses janCourses = janCoursesList.get(position);

        holder.tvTitle.setText(janCourses.getTitle());
        holder.tvDuration.setText(janCourses.getDuration()+" weeks");

//        Picasso.get()
//                .load(janCourses.getImageUrl())
//                .into(holder.imageView);

        holder.rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,CourseDetailActivity.class);
                intent.putExtra("janCourses", (Serializable) janCourses);

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





    class JanCoursesViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle;
        TextView tvDuration;
        ImageView imageView;

        View rowView;

        public JanCoursesViewHolder(View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.text_view_title);
            tvDuration = itemView.findViewById(R.id.text_view_duration);
            imageView = itemView.findViewById(R.id.image_view_jan_courses);
            rowView = itemView;

        }
    }
}
