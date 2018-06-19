package com.nexdev.enyason.jan.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nexdev.enyason.jan.CoursePrgress;
import com.nexdev.enyason.jan.R;

import java.util.List;

import jp.co.recruit_mp.android.circleprogressview.CircleProgressView;


/**
 * Created by enyason on 6/18/18.
 */

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.DashboardAdapterViewHolder> {


    List<CoursePrgress> mCourseprogress;


    double progressFromDb;
    double numberOfWeeksFromDb;
    double myProgress = 0;
    Context context;

    //calculation of progress

    String percentageView;


    public DashboardAdapter(List<CoursePrgress> coursePrgressList, Context context) {

        this.mCourseprogress = coursePrgressList;
        this.context = context;
    }


    @NonNull
    @Override
    public DashboardAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        Context context = parent.getContext();
//        int dashboardXml = R.layout.dashboard_layout;
//        mInflater = LayoutInflater.from(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dashboard_layout, parent, false);
        return new DashboardAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardAdapterViewHolder holder, int position) {

//
        CoursePrgress coursePrgress = mCourseprogress.get(position);
//
        numberOfWeeksFromDb = coursePrgress.getNumberOfWeeks();
        progressFromDb = coursePrgress.getmProgress();
        myProgress = progressFromDb / numberOfWeeksFromDb;
        double collectedProgress = myProgress * 100;
        int collectedProgInt = ((int) collectedProgress);
        String prog = String.valueOf(collectedProgInt);

        // Set progress
        holder.mCircleProgress.setProgress(0.0f);
// Set stroke width
        holder.mCircleProgress.setStrokeWidthPx(40);
// Set progress animation
        holder.mCircleProgress.setProgressAnimation(((float) myProgress), 3000);

 //Set colors

//        holder.mCircleProgress.setForegroundColor(R.color.colorPrimary);
        holder.mPercentageCount.setText(prog+"%");
        holder.mCourseTitle.setText(coursePrgress.getTitle());
        

        holder.tvTaken.setText(((int) progressFromDb) + " lessons taken");

       int weekLeft = (int) (numberOfWeeksFromDb - progressFromDb);

        holder.tvLeft.setText( weekLeft + " lessons remaining");

    }

    @Override
    public int getItemCount() {

        if (mCourseprogress != null) {
            return mCourseprogress.size();
        }
        return 0;
    }

    class DashboardAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView mCourseTitle, mPercentageCount,tvTaken,tvLeft;
        CircleProgressView mCircleProgress;



        public DashboardAdapterViewHolder(View itemView) {
            super(itemView);
            mCourseTitle = itemView.findViewById(R.id.tv_course_title);
            mPercentageCount = itemView.findViewById(R.id.percentCount);
            mCircleProgress = itemView.findViewById(R.id.circleprogressview);

            tvTaken = itemView.findViewById(R.id.tv_taken);
            tvLeft = itemView.findViewById(R.id.tv_left);
        }
    }

}
