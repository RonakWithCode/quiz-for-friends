package com.crazyostudio.quizforfriends;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.crazyostudio.quizforfriends.databinding.ActivityMainBinding;
import com.crazyostudio.quizforfriends.databinding.UserquizinputBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.startQuiz.setOnClickListener(view -> ShowStartQuiz(0));
        binding.startQuizById.setOnClickListener(view -> ShowStartQuiz(1));
    }
    public void ShowStartQuiz(int id){
        UserquizinputBinding quiz;
        quiz = UserquizinputBinding.inflate(getLayoutInflater());
        Dialog dialog = new Dialog(this);
        dialog.setContentView(quiz.getRoot());
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.newcategoryboxbg);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.CategorySpinnerAnimation;
        quiz.cancel.setOnClickListener(view -> dialog.dismiss());

        if (id==1){
            quiz.id.setVisibility(View.VISIBLE);
           quiz.save.setOnClickListener(view -> {
               if (!quiz.name.getText().toString().isEmpty()) {
                   if (!quiz.id.getText().toString().isEmpty()) {
                       Intent intent = new Intent(this,StartMCQActivity.class);
                       intent.putExtra("id",quiz.id.getText().toString());
                       intent.putExtra("name",quiz.name.getText().toString());
                       startActivity(intent);
                   }
                   else {
                       quiz.id.setError("Input id ");
                   }
               }
               else {
                   quiz.name.setError("Input Name ");
               }
           });
        } else if (id == 0) {
            quiz.save.setOnClickListener(view -> {
                if (!quiz.name.getText().toString().isEmpty()) {
                    Intent intent = new Intent(this,MakeQuizActivity.class);
                    intent.putExtra("name",quiz.name.getText().toString());
                    startActivity(intent);
                }
            });
        }
        dialog.show();
    }

}