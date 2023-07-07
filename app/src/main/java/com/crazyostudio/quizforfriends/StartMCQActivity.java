package com.crazyostudio.quizforfriends;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.crazyostudio.quizforfriends.Model.MCQModel;
import com.crazyostudio.quizforfriends.databinding.ActivityStartMcqactivityBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class StartMCQActivity extends AppCompatActivity {
    ActivityStartMcqactivityBinding binding;
    String MainSelectANS;
    int index = 0,Point = 0;
    boolean isFirstTime = false;

    ArrayList<MCQModel> models = new ArrayList<>();
    FirebaseDatabase users;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStartMcqactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        users = FirebaseDatabase.getInstance();
        getMCQs();



        binding.option1.setOnClickListener(view-> {
             change(binding.option1);
            //           Set Color to select one
          MainSelectANS = "a";
            Toast.makeText(this, MainSelectANS, Toast.LENGTH_SHORT).show();
        });
        binding.option2.setOnClickListener(view-> {
            change(binding.option2);
            MainSelectANS = "b";
            Toast.makeText(this, MainSelectANS, Toast.LENGTH_SHORT).show();
        });
        binding.option3.setOnClickListener(view-> {
            change(binding.option3);

            MainSelectANS = "c";
                    Toast.makeText(this, MainSelectANS, Toast.LENGTH_SHORT).show();
                });
        binding.option4.setOnClickListener(view-> {
            change(binding.option4);
            MainSelectANS = "d";
            Toast.makeText(this, MainSelectANS, Toast.LENGTH_SHORT).show();

        });
        binding.save.setOnClickListener(view->{
            if (index != 10) {
                index++;
                if (models.get(index).getCorrectAnswer().equals(MainSelectANS)) {
                    Point++;
                    Toast.makeText(this, "This "+models.get(index).getCorrectAnswer(), Toast.LENGTH_SHORT).show();
                }
                binding.QView.setText("question "+index);
                binding.question.setText(models.get(index).getQuestion());
                binding.option1.setText(models.get(index).getOptions().get(0));
                binding.option2.setText(models.get(index).getOptions().get(1));
                binding.option3.setText(models.get(index).getOptions().get(2));
                binding.option4.setText(models.get(index).getOptions().get(3));
                binding.option1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(models.get(index).getBorderColor())));
                binding.option2.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(models.get(index).getBorderColor())));
                binding.option3.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(models.get(index).getBorderColor())));
                binding.option4.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(models.get(index).getBorderColor())));

            }else {
                Toast.makeText(this, "Point == "+ Point, Toast.LENGTH_SHORT).show();
            }

        });
    }
    private void getMCQs(){
        users.getReference().child("questions").child(getIntent().getStringExtra("id")).child("mcq").addValueEventListener(new ValueEventListener() {
            @SuppressLint({"NotifyDataSetChanged", "SetTextI18n"})
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                models.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    MCQModel userInfo = snapshot1.getValue(MCQModel.class);
                    if (!Objects.equals(snapshot1.getKey(), "userName")) {
                        assert userInfo != null;
                        models.add(userInfo);
                        if (!isFirstTime) {
                            binding.QView.setText("question "+index);
                            binding.question.setText(models.get(index).getQuestion());
                            binding.option1.setText(models.get(index).getOptions().get(0));
                            binding.option2.setText(models.get(index).getOptions().get(1));
                            binding.option3.setText(models.get(index).getOptions().get(2));
                            binding.option4.setText(models.get(index).getOptions().get(3));
                            binding.option1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(models.get(index).getBorderColor())));
                            binding.option2.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(models.get(index).getBorderColor())));
                            binding.option3.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(models.get(index).getBorderColor())));
                            binding.option4.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(models.get(index).getBorderColor())));

                            isFirstTime = true;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        if (getIntent().getStringExtra("id").isEmpty()) {
//            Error Screen
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }
    }

    private void change(@NonNull TextView option){
        binding.option1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(models.get(index).getBorderColor())));
        binding.option1.setTextColor(Color.parseColor("#FFFFFF"));

        binding.option2.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(models.get(index).getBorderColor())));
        binding.option2.setTextColor(Color.parseColor("#FFFFFF"));

        binding.option3.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(models.get(index).getBorderColor())));
        binding.option3.setTextColor(Color.parseColor("#FFFFFF"));

        binding.option4.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(models.get(index).getBorderColor())));
        binding.option4.setTextColor(Color.parseColor("#FFFFFF"));

        option.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#fff")));
        option.setTextColor(Color.parseColor("#000000"));


    }

}