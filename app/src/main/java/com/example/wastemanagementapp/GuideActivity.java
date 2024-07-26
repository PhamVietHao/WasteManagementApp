package com.example.wastemanagementapp;

import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class GuideActivity extends AppCompatActivity {

    private TextView answer1;
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

        // Ensure height is measured before animation
        answer1.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                answer1.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                answer1.setTranslationY(answer1.getHeight()); // Ensure start position is below
            }
        });
    }
}
