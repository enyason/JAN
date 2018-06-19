package com.nexdev.enyason.jan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MentorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor);
    }

    public void messageMentor(View view) {

        Toast.makeText(this,"Start Messaging Your Mentor",Toast.LENGTH_SHORT).show();
    }
}
