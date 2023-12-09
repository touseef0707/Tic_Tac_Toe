package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

<<<<<<< HEAD
    // Buttons for choosing game mode
    Button cpu, human;

    // Intent to navigate to other activities
=======
    Button cpu, human;
>>>>>>> origin/master
    Intent intent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

<<<<<<< HEAD
        // Initializing UI elements
        cpu = findViewById(R.id.cpu);
        human = findViewById(R.id.human);

        // Click listener for the CPU button
        cpu.setOnClickListener(v -> chooseSymbol());

        // Click listener for the Human button
        human.setOnClickListener(v -> initializePlayers());
    }

    // Method to navigate to the ChooseSymbol activity
=======
        cpu = findViewById(R.id.cpu);
        human = findViewById(R.id.human);
        cpu.setOnClickListener(v -> chooseSymbol());
        human.setOnClickListener(v -> initializePlayers());
    }

>>>>>>> origin/master
    public void chooseSymbol() {
        intent = new Intent(Home.this, Choice.class);
        startActivity(intent);
    }

<<<<<<< HEAD
    // Method to navigate to the InitPlayers activity
=======
>>>>>>> origin/master
    public void initializePlayers() {
        intent = new Intent(Home.this, InitPlayers.class);
        startActivity(intent);
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> origin/master
