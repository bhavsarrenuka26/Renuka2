package com.example.renuka;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;
public class Videoplayer extends AppCompatActivity {
    Button btnPlay, btnPause, btnStop, btnForward, btnRewind;
    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_videoplayer);



                // Initialize views
                videoView = findViewById(R.id.video_view);
                btnPlay = findViewById(R.id.btn_play);
                btnPause = findViewById(R.id.btn_pause);
                btnStop = findViewById(R.id.btn_stop);
                btnForward = findViewById(R.id.btn_forward);
                btnRewind = findViewById(R.id.btn_rewind);

                // Get the video file name from the intent
                String videoName = getIntent().getStringExtra("video_name");
                String path = "android.resource://" + getPackageName() + "/raw/" + videoName;
                Uri uri = Uri.parse(path);
                videoView.setVideoURI(uri);

                // Set up controls
                btnPlay.setOnClickListener(v -> videoView.start());
                btnPause.setOnClickListener(v -> videoView.pause());
                btnStop.setOnClickListener(v -> {
                    videoView.stopPlayback();
                    videoView.setVideoURI(uri); // Reload video
                });
                btnForward.setOnClickListener(v -> {
                    int current = videoView.getCurrentPosition();
                    int forwardTime = 5000; // 5 seconds forward
                    videoView.seekTo(current + forwardTime);
                });
                btnRewind.setOnClickListener(v -> {
                    int current = videoView.getCurrentPosition();
                    int rewindTime = 5000; // 5 seconds backward
                    videoView.seekTo(current - rewindTime);
                });
            }
        }

