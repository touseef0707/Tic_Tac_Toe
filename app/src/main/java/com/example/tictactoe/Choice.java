package com.example.tictactoe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Choice extends AppCompatActivity {

    private int user_choice = 0;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        TextView choice = findViewById(R.id.choice);
        ImageView btnX = findViewById(R.id.btnX);
        ImageView btnO = findViewById(R.id.btnO);
        Button start = findViewById(R.id.start);
        Button back = findViewById(R.id.back);
        Intent intent = new Intent(Choice.this, Bot.class);

        btnX.setOnClickListener(v -> {
            user_choice = 0;
            btnX.setBackgroundResource(R.drawable.cyan_border);
            btnO.setBackgroundResource(R.drawable.white_border);
        });
        btnO.setOnClickListener(v -> {
            user_choice = 1;
            btnX.setBackgroundResource(R.drawable.white_border);
            btnO.setBackgroundResource(R.drawable.cyan_border);
        });

        start.setOnClickListener(v -> {
            if (user_choice == 0) {
                intent.putExtra("namePlayerX", "You");
                intent.putExtra("namePlayerO", "Bot");
            } else {
                intent.putExtra("namePlayerX", "Bot");
                intent.putExtra("namePlayerO", "You");
            }
            startActivity(intent);
        });

        back.setOnClickListener(v -> {
            user_choice = 0;
            finish();
        });

    }
}