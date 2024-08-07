package com.example.wastemanagementapp;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class GuideActivity extends AppCompatActivity {

    private TextView answer1;
    private TextView answer2;
    private boolean isAnswerVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        // Question 1
        LinearLayout question1 = findViewById(R.id.question_1);
        answer1 = findViewById(R.id.answer_1);
        TextView questionTitle1 = findViewById(R.id.question_title_1);

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
        answer2 = findViewById(R.id.answer_2);
        TextView questionTitle2 = findViewById(R.id.question_title_2);

        question1.setOnClickListener(v -> {
            if (!isAnswerVisible) {
                answer2.setVisibility(View.VISIBLE);
                answer2.setTranslationY(answer1.getHeight()); // Start below the view
                answer2.animate().translationY(0).setDuration(300).start();
                isAnswerVisible = true;
            } else {
                answer2.animate().translationY(answer2.getHeight()).setDuration(300).withEndAction(() -> {
                    answer2.setVisibility(View.GONE);
                    isAnswerVisible = false;
                }).start();
            }
        });

        // Ensure height is measured before animation
        answer2.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                answer2.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                answer2.setTranslationY(answer2.getHeight()); // Ensure start position is below
            }
        });

        // Bottom navigation
        ImageButton navHome = findViewById(R.id.nav_home);
        ImageButton navBinPage = findViewById(R.id.nav_bin_page);
        ImageButton navGuidePage = findViewById(R.id.nav_guide_page);

        navHome.setOnClickListener(v -> {
            Intent intent = new Intent(GuideActivity.this, LandingPageActivity.class);
            startActivity(intent);
        });

        navBinPage.setOnClickListener(v -> {
            Intent intent = new Intent(GuideActivity.this, BinPageActivity.class);
            startActivity(intent);
        });

        navGuidePage.setOnClickListener(v -> {
            // We are already in GuideActivity, no need to start a new one
        });
    }
}
