package com.crazyostudio.quizforfriends;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.crazyostudio.quizforfriends.databinding.ActivityShowLinkBinding;

public class ShowLink extends AppCompatActivity {
    ActivityShowLinkBinding binding;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowLinkBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.CopyLinks.setText(getIntent().getStringExtra("link")+"");

        binding.back.setOnClickListener(view-> onBackPressed());
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (getIntent().getStringExtra("link").isEmpty()) {
//            Start Error Activity
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }

//        int currentMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
//        if (currentMode == Configuration.UI_MODE_NIGHT_YES) {
//            binding.back.setImageResource(R.drawable.ic_baseline_arrow_back_24dark);
//        } else {
//            binding.back.setImageResource(R.drawable.ic_baseline_arrow_back_24);
//        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}