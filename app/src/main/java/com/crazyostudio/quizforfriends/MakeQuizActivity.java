package com.crazyostudio.quizforfriends;
import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.crazyostudio.quizforfriends.Model.MCQModel;
import com.crazyostudio.quizforfriends.Model.QuizModel;
import com.crazyostudio.quizforfriends.databinding.ActivityMakeQuizBinding;
import com.google.firebase.database.FirebaseDatabase;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
public class MakeQuizActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityMakeQuizBinding binding;
    FirebaseDatabase database;
    String ColorId;
    long time;
    int i = 0;
    ArrayList<QuizModel> quizzes = new ArrayList<>();
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMakeQuizBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        quizzes.add(new QuizModel("Which city was I born in?", "New York City", "Los Angeles", "Chicago", "Miami"));
        quizzes.add(new QuizModel("What is my favorite movie genre?", "Comedy", "Action", "Drama", "Science Fiction"));
        quizzes.add(new QuizModel("What is my favorite type of cuisine?", "Italian", "Mexican", "Chinese", "Indian"));
        quizzes.add(new QuizModel("What is my dream travel destination?", "Paris, France", "Tokyo, Japan", "Sydney, Australia", "Rio de Janeiro, Brazil"));
        quizzes.add(new QuizModel("What is my favorite season?", "Spring", "Summer", "Fall", "Winter"));
        quizzes.add(new QuizModel("Which musical instrument do I play?", "Piano", "Guitar", "Violin", "Drums"));
        quizzes.add(new QuizModel("What is my favorite hobby?", "Reading", "Painting", "Cooking", "Sports"));
        quizzes.add(new QuizModel("What is my favorite animal?", "Dog", "Cat", "Dolphin", "Elephant"));
        quizzes.add(new QuizModel("What is my favorite ice cream flavor?", "Chocolate", "Vanilla", "Strawberry", "Mint Chocolate Chip"));
        quizzes.add(new QuizModel("What is my preferred method of transportation?", "Car", "Bicycle", "Public transportation", "Walking"));

        binding.QView.setText("Question "+i);
        binding.question.setText(quizzes.get(i).getQuestion());
        binding.option1.setText(quizzes.get(i).getOptionA());
        binding.option2.setText(quizzes.get(i).getOptionB());
        binding.option3.setText(quizzes.get(i).getOptionC());
        binding.option4.setText(quizzes.get(i).getOptionD());

        ColorId = "#0084ff";
        time = System.currentTimeMillis();
        database = FirebaseDatabase.getInstance();
        binding.blue.setOnClickListener(this);
        binding.red.setOnClickListener(this);
        binding.green.setOnClickListener(this);
        binding.Yellow.setOnClickListener(this);
        List<String> categories = new ArrayList<>();
        categories.add("a");
        categories.add("b");
        categories.add("c");
        categories.add("d");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerAns.setAdapter(dataAdapter);
        binding.back.setOnClickListener(view->{
            onBackPressed();
        });
        binding.clearText.setOnClickListener(view->{
            binding.spinnerAns.setSelection(0);
            binding.question.setText("");
            binding.option1.setText("");
            binding.option2.setText("");
            binding.option3.setText("");
            binding.option4.setText("");
            ColorId = "#0084ff";
            binding.option1.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.Round_Button_Color_blue));
            binding.option2.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.Round_Button_Color_blue));
            binding.option3.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.Round_Button_Color_blue));
            binding.option4.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.Round_Button_Color_blue));
        });
        binding.save.setOnClickListener(view-> {
            if (!binding.question.getText().toString().isEmpty())
            {
                ArrayList<String> options = new ArrayList<>();
                options.add(binding.option1.getText().toString());
                options.add(binding.option2.getText().toString());
                options.add(binding.option3.getText().toString());
                options.add(binding.option4.getText().toString());
                MCQModel mcqModel = new MCQModel(binding.question.getText().toString(),binding.spinnerAns.getSelectedItem().toString(),String.valueOf(ColorId),options);
//                options.clear();
                database.getReference().child("questions").child(time+"").child("mcq").push().setValue(mcqModel).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        System.out.println("Value - " + i);
                        if (i==9) {
                            Intent i =  new Intent(this,ShowLink.class);
                            i.putExtra("link",""+time);
                            startActivity(i);
                        }else {
                            i++;
                            Toast.makeText(this, "Save", Toast.LENGTH_SHORT).show();
                            binding.spinnerAns.setSelection(0);

                            binding.question.setText(quizzes.get(i).getQuestion());
                            binding.option1.setText(quizzes.get(i).getOptionA());
                            binding.option2.setText(quizzes.get(i).getOptionB());
                            binding.option3.setText(quizzes.get(i).getOptionC());
                            binding.option4.setText(quizzes.get(i).getOptionD());
                            binding.QView.setText("Question " + i);
                        }
                    }
                }).addOnFailureListener(e -> {
                });
            }
            else {
                binding.question.setError("Input Error");
            }
        });
    }
    @SuppressLint({"ResourceAsColor", "NonConstantResourceId"})
    @Override
    public void onClick(View v) {
//        ImageView imageView = findViewById(v.getId());
        switch (v.getId()){
            case R.id.blue:
                ColorId = "#0084ff";
                binding.option1.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.Round_Button_Color_blue));
                binding.option2.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.Round_Button_Color_blue));
                binding.option3.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.Round_Button_Color_blue));
                binding.option4.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.Round_Button_Color_blue));
                break;
            case R.id.red:
                ColorId = "#ff0000";
                binding.option1.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.Round_Button_Color_red));
                binding.option2.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.Round_Button_Color_red));
                binding.option3.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.Round_Button_Color_red));
                binding.option4.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.Round_Button_Color_red));

                break;
            case R.id.green:
                ColorId = "#adff2f";
                binding.option1.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.Round_Button_Color_green));
                binding.option2.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.Round_Button_Color_green));
                binding.option3.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.Round_Button_Color_green));
                binding.option4.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.Round_Button_Color_green));

                break;
            case R.id.Yellow:
                ColorId = "#ffff00";
                binding.option1.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.Round_Button_Color_yellow));
                binding.option2.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.Round_Button_Color_yellow));
                binding.option3.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.Round_Button_Color_yellow));
                binding.option4.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.Round_Button_Color_yellow));
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}