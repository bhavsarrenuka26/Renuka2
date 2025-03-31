package com.example.renuka;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FrontPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_front_page);



            Animation scaleFade = AnimationUtils.loadAnimation(this, R.anim.fade_in);

            ImageView splashLogo = findViewById(R.id.splash_logo);
            TextView splashTitle = findViewById(R.id.splash_title);
            TextView splashQuote = findViewById(R.id.splash_quote);

            splashLogo.startAnimation(scaleFade);
            splashTitle.startAnimation(scaleFade);
            splashQuote.startAnimation(scaleFade);
            new Handler().postDelayed(() -> {
                startActivity(new Intent(FrontPage.this, RegisterPage.class));
                finish();
            }, 3500);  // 3.5 seconds


        }
    }

