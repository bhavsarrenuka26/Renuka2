package com.example.renuka;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {

        CardView cardProfile, cardRealtime, cardWeekly, cardMonthly, cardSuggestions;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            cardProfile = findViewById(R.id.card_profile);
            cardRealtime = findViewById(R.id.card_realtime);
            cardWeekly = findViewById(R.id.card_weekly);
            cardMonthly = findViewById(R.id.card_monthly);
            cardSuggestions = findViewById(R.id.card_suggestions);

            cardProfile.setOnClickListener(view -> startActivity(new Intent(this, ProfilePage.class)));
            cardRealtime.setOnClickListener(view -> startActivity(new Intent(this,GraphActivity.class)));
            cardWeekly.setOnClickListener(view -> startActivity(new Intent(this, WeeklyAnalysis.class)));
            cardMonthly.setOnClickListener(view -> startActivity(new Intent(this, MonthlyAnalysis.class)));
            cardSuggestions.setOnClickListener(view -> startActivity(new Intent(this, SuggestionActivity.class)));
        }
    }
