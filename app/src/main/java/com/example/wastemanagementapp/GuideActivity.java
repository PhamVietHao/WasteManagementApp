package com.example.wastemanagementapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class GuideActivity extends AppCompatActivity {

    private boolean isAnswerVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        // Question 1
        LinearLayout question1 = findViewById(R.id.question_1);
        TextView answer1 = findViewById(R.id.answer_1);

        question1.setOnClickListener(v -> {
            if (!isAnswerVisible) {
                answer1.setVisibility(View.VISIBLE);
                answer1.setTranslationY(answer1.getHeight()); // Start below the view
                answer1.animate().translationY(0).setDuration(300).start();
                isAnswerVisible = true;
            } else {
                answer1.animate().translationY(answer1.getHeight()).setDuration(300).withEndAction(() -> {
                    answer1.setVisibility(View.GONE);
                    isAnswerVisible = false;
                }).start();
            }
        });

        // Question 2
        LinearLayout question2 = findViewById(R.id.question_2);
        TextView answer2 = findViewById(R.id.answer_2);

        question2.setOnClickListener(v -> {
            if (!isAnswerVisible) {
                answer2.setVisibility(View.VISIBLE);
                answer2.setTranslationY(answer2.getHeight()); // Start below the view
                answer2.animate().translationY(0).setDuration(300).start();
                isAnswerVisible = true;
            } else {
                answer2.animate().translationY(answer2.getHeight()).setDuration(300).withEndAction(() -> {
                    answer2.setVisibility(View.GONE);
                    isAnswerVisible = false;
                }).start();
            }
        });

        // Bottom navigation
        ImageButton navHome = findViewById(R.id.nav_home);
        ImageButton navBinPage = findViewById(R.id.nav_bin_page);
        ImageButton navGuidePage = findViewById(R.id.nav_guide_page);

        navHome.setSelected(false);
        navBinPage.setSelected(false); // This is for GuidePage, so BinPage button should be selected
        navGuidePage.setSelected(true);

        navHome.setOnClickListener(v -> {
            Intent intent = new Intent(GuideActivity.this, LandingPageActivity.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
        });

        navBinPage.setOnClickListener(v -> {
            Intent intent = new Intent(GuideActivity.this, BinPageActivity.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
        });

        navGuidePage.setOnClickListener(v -> {
            navHome.setSelected(false);
            navBinPage.setSelected(false);
            navGuidePage.setSelected(true);
            // We are already in GuideActivity, no need to start a new one
        });
    }
}
