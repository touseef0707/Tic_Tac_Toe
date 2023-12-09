package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    // Buttons for choosing game mode
    Button cpu, human;

    // Intent to navigate to other activities
    Intent intent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initializing UI elements
        cpu = findViewById(R.id.cpu);
        human = findViewById(R.id.human);

        // Click listener for the CPU button
        cpu.setOnClickListener(v -> chooseSymbol());

        // Click listener for the Human button
        human.setOnClickListener(v -> initializePlayers());
    }

    // Method to navigate to the ChooseSymbol activity
    public void chooseSymbol() {
        intent = new Intent(Home.this, Choice.class);
        startActivity(intent);
    }

    // Method to navigate to the InitPlayers activity
    public void initializePlayers() {
        intent = new Intent(Home.this, InitPlayers.class);
        startActivity(intent);
    }
}
