package com.example.renuka;

import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class SuggestionActivity extends AppCompatActivity {
    CardView cardDaily, cardMedium, cardSerious;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_suggestion);
                // Initialize the cards by ID
                cardDaily = findViewById(R.id.card_daily);
                cardMedium = findViewById(R.id.card_medium);
                cardSerious = findViewById(R.id.card_serious);

                // Set click listeners for each card
                cardDaily.setOnClickListener(v -> openVideoPlayer("yoga2"));
                cardMedium.setOnClickListener(v -> openVideoPlayer("yoga1"));
                cardSerious.setOnClickListener(v -> openVideoPlayer("yoga3"));
            }

            // Open VideoPlayerActivity and pass the video file name
            private void openVideoPlayer(String videoFileName) {
                Intent intent = new Intent(SuggestionActivity.this, Videoplayer.class);
                intent.putExtra("video_name", videoFileName);  // Pass video name
                startActivity(intent);
            }
        }
