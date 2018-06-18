package com.nexdev.enyason.jan;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class ClassContentActivity extends YouTubeBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_content);


        Intent intent = getIntent();

        final String url = intent.getStringExtra("videoUrl");
        String lessonNote = intent.getStringExtra("content");
        YouTubePlayerView youTubePlayerView =
                (YouTubePlayerView) findViewById(R.id.player);

        youTubePlayerView.initialize("AIzaSyDcN9yyCeQDY1AjrRjp2swdEXWu4OxGETI",
                new YouTubePlayer.OnInitializedListener() {

                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                        youTubePlayer.loadVideo(url);

                    }

                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                    }
                });

    }

    public void clickToDownload(View view) {

        Toast.makeText(this,"File saved to storage ",Toast.LENGTH_SHORT).show();
    }

    public void clickToPostPreview(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("what did you Learn");

        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();

            }
        });

//        builder.setView()

    }
}