package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    Button cpu, human;
    Intent intent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        cpu = findViewById(R.id.cpu);
        human = findViewById(R.id.human);
        cpu.setOnClickListener(v -> chooseSymbol());
        human.setOnClickListener(v -> initializePlayers());
    }

    public void chooseSymbol() {
        intent = new Intent(Home.this, Choice.class);
        startActivity(intent);
    }

    public void initializePlayers() {
        intent = new Intent(Home.this, InitPlayers.class);
        startActivity(intent);
    }
}