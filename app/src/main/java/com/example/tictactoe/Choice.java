package com.example.tictactoe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
<<<<<<< HEAD
=======
import android.widget.TextView;
>>>>>>> origin/master

import androidx.appcompat.app.AppCompatActivity;

public class Choice extends AppCompatActivity {

<<<<<<< HEAD
    // Variable to track the user's choice (0 for X, 1 for O)
=======
>>>>>>> origin/master
    private int user_choice = 0;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
<<<<<<< HEAD

        // Initializing UI elements
=======
        TextView choice = findViewById(R.id.choice);
>>>>>>> origin/master
        ImageView btnX = findViewById(R.id.btnX);
        ImageView btnO = findViewById(R.id.btnO);
        Button start = findViewById(R.id.start);
        Button back = findViewById(R.id.back);
<<<<<<< HEAD

        // Creating an intent to navigate to the Bot class
        Intent intent = new Intent(Choice.this, Bot.class);

        // Click listener for X button
        btnX.setOnClickListener(v -> {
            user_choice = 0;
            // Highlighting the X button and unhighlighting the O button
            btnX.setBackgroundResource(R.drawable.cyan_border);
            btnO.setBackgroundResource(R.drawable.white_border);
        });

        // Click listener for O button
        btnO.setOnClickListener(v -> {
            user_choice = 1;
            // Highlighting the O button and unhighlighting the X button
=======
        Intent intent = new Intent(Choice.this, Bot.class);

        btnX.setOnClickListener(v -> {
            user_choice = 0;
            btnX.setBackgroundResource(R.drawable.cyan_border);
            btnO.setBackgroundResource(R.drawable.white_border);
        });
        btnO.setOnClickListener(v -> {
            user_choice = 1;
>>>>>>> origin/master
            btnX.setBackgroundResource(R.drawable.white_border);
            btnO.setBackgroundResource(R.drawable.cyan_border);
        });

<<<<<<< HEAD
        // Click listener for the start button
        start.setOnClickListener(v -> {
            // Setting player names based on user's choice and starting the Bot activity
=======
        start.setOnClickListener(v -> {
>>>>>>> origin/master
            if (user_choice == 0) {
                intent.putExtra("namePlayerX", "You");
                intent.putExtra("namePlayerO", "Bot");
            } else {
                intent.putExtra("namePlayerX", "Bot");
                intent.putExtra("namePlayerO", "You");
            }
            startActivity(intent);
        });

<<<<<<< HEAD
        // Click listener for the back button
        back.setOnClickListener(v -> {
            // Resetting user choice and finishing the activity
=======
        back.setOnClickListener(v -> {
>>>>>>> origin/master
            user_choice = 0;
            finish();
        });

    }
<<<<<<< HEAD
}
=======
}
>>>>>>> origin/master
