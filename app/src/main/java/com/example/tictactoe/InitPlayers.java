package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InitPlayers extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init_players);

        // get player name field ids
        EditText playerOne = findViewById(R.id.playerOne);
        EditText playerTwo = findViewById(R.id.playerTwo);
        Button startButton = findViewById(R.id.startButton);

        // start game after getting names from corresponding ids
        startButton.setOnClickListener(view ->
                startGame(playerOne.getText().toString(), playerTwo.getText().toString())
        );
    }

    // method to start the game
    public void startGame(String namePlayerX, String namePlayerO){
        if (namePlayerX.isEmpty() || namePlayerO.isEmpty()){
            Toast.makeText(this, "Name fields cannot be empty!", Toast.LENGTH_SHORT).show();
        } else{
            Intent i = new Intent(InitPlayers.this, MainActivity.class);
            i.putExtra("namePlayerX", namePlayerX);
            i.putExtra("namePlayerO", namePlayerO);
            startActivity(i);
        }
    }
}