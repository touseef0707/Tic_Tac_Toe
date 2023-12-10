package com.example.tictactoe;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tictactoe.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bot extends AppCompatActivity {

    ActivityMainBinding binding;
    private final List<int[]> winning_combos = new ArrayList<>();
    private int[] positions = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int activePlayer = 1;
    private int totalBoxesSelected = 1;
    private Boolean endGame = false;
    String nameX, nameO;
    int scoreX = 0;
    int scoreO = 0;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        winning_combos.add(new int[]{0, 1, 2});
        winning_combos.add(new int[]{3, 4, 5});
        winning_combos.add(new int[]{6, 7, 8});
        winning_combos.add(new int[]{0, 3, 6});
        winning_combos.add(new int[]{1, 4, 7});
        winning_combos.add(new int[]{2, 5, 8});
        winning_combos.add(new int[]{0, 4, 8});
        winning_combos.add(new int[]{2, 4, 6});

        nameX = getIntent().getStringExtra("namePlayerX");
        nameO = getIntent().getStringExtra("namePlayerO");

        binding.x.setText(nameX);
        binding.o.setText(nameO);
        binding.back.setOnClickListener(v -> finish());
        if (nameX.equals("Bot")) {
            turnCPU();
        } else if (nameX.equals("You")) {
            turnHuman();
        }
    }

    // method to check if a cell is empty
    public Boolean isCellEmpty(int position) {
        return positions[position] == 0;
    }

    public void turnHuman() {
        binding.image1.setOnClickListener(view -> {
            if (isCellEmpty(0)) {
                setInput((ImageView) view, 0);
                turnCPU();
            }
        });
        binding.image2.setOnClickListener(view -> {
            if (isCellEmpty(1)) {
                setInput((ImageView) view, 1);
                turnCPU();
            }
        });
        binding.image3.setOnClickListener(view -> {
            if (isCellEmpty(2)) {
                setInput((ImageView) view, 2);
                turnCPU();
            }
        });
        binding.image4.setOnClickListener(view -> {
            if (isCellEmpty(3)) {
                setInput((ImageView) view, 3);
                turnCPU();
            }
        });
        binding.image5.setOnClickListener(view -> {
            if (isCellEmpty(4)) {
                setInput((ImageView) view, 4);
                turnCPU();
            }
        });
        binding.image6.setOnClickListener(view -> {
            if (isCellEmpty(5)) {
                setInput((ImageView) view, 5);
                turnCPU();
            }
        });
        binding.image7.setOnClickListener(view -> {
            if (isCellEmpty(6)) {
                setInput((ImageView) view, 6);
                turnCPU();
            }
        });
        binding.image8.setOnClickListener(view -> {
            if (isCellEmpty(7)) {
                setInput((ImageView) view, 7);
                turnCPU();
            }
        });
        binding.image9.setOnClickListener(view -> {
            if (isCellEmpty(8)) {
                setInput((ImageView) view, 8);
                turnCPU();
            }
        });

    }

    public void turnCPU() {
        if (!endGame) {
            List<Integer> indexes = new ArrayList<>();

            for (int i = 0; i < 9; i++) {
                if (isCellEmpty(i)) {
                    indexes.add(i);
                }
            }
            Random random = new Random();
            int randomIndex = indexes.get(random.nextInt(indexes.size()));
            if (randomIndex == 0) {
                image = findViewById(R.id.image1);
            } else if (randomIndex == 1) {
                image = findViewById(R.id.image2);
            } else if (randomIndex == 2) {
                image = findViewById(R.id.image3);
            } else if (randomIndex == 3) {
                image = findViewById(R.id.image4);
            } else if (randomIndex == 4) {
                image = findViewById(R.id.image5);
            } else if (randomIndex == 5) {
                image = findViewById(R.id.image6);
            } else if (randomIndex == 6) {
                image = findViewById(R.id.image7);
            } else if (randomIndex == 7) {
                image = findViewById(R.id.image8);
            } else if (randomIndex == 8) {
                image = findViewById(R.id.image9);
            }
            setInput(image, randomIndex);
        }
        turnHuman();
    }


    // set the input according to the player.
    public void setInput(ImageView image, int position) {

        positions[position] = activePlayer; // mark the position the player selected

        if (activePlayer == 1) {
            image.setImageResource(R.drawable.ximage);
            if (result()) {
                endGame = true;
                displayWinner("win", nameX);
            } else if (totalBoxesSelected == 9) {
                endGame = true;
                displayWinner("draw", "");
            } else {
                changeTurn(2);
                totalBoxesSelected++;
            }
        } else {
            image.setImageResource(R.drawable.oimage);
            if (result()) {
                endGame = true;
                displayWinner("win", nameO);
            } else if (totalBoxesSelected == 9) {
                endGame = true;
                displayWinner("draw", "");
            } else {
                changeTurn(1);
                totalBoxesSelected++;
            }
        }
    }

    // display the result
    @SuppressLint("SetTextI18n")
    public void displayWinner(String msg, String player) {
        String win_message;
        String draw_message = "Match draw.";
        ResultBot result;
        if (msg.equals("win")) {
            if (player.equals("You")) {
                win_message = "Congratulations! You won.";
            } else {
                win_message = "You lost.";
            }
            result = new ResultBot(Bot.this, win_message, Bot.this);
            binding.xScore.setText("x: " + scoreX);
            binding.oScore.setText("o: " + scoreO);
        } else {
            result = new ResultBot(Bot.this, draw_message, Bot.this);
        }
        result.setCancelable(false);
        result.show();
    }

    // method to change player turn
    public void changeTurn(int next) {
        activePlayer = next; // change and set to next player's turn
        if (activePlayer == 1) {
            binding.layoutX.setBackgroundResource(R.drawable.cyan_border);
            binding.layoutO.setBackgroundResource(R.drawable.white_border);
        } else {
            binding.layoutX.setBackgroundResource(R.drawable.white_border);
            binding.layoutO.setBackgroundResource(R.drawable.cyan_border);
        }
    }

    // method to check if there is a winning combination
    public Boolean result() {
        int i = 0;
        while (i < winning_combos.size()) {
            final int[] combo = winning_combos.get(i);
            if (positions[combo[0]] == activePlayer && positions[combo[1]] == activePlayer &&
                    positions[combo[2]] == activePlayer) {
                if (activePlayer == 1) {
                    scoreX++;
                } else if (activePlayer == 2) {
                    scoreO++;
                }
                return true;
            }
            i++;
        }
        return false;
    }

    // restart game method
    public void restartGame() {
        positions = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        activePlayer = 1;
        endGame = false;
        totalBoxesSelected = 1;

        binding.image1.setImageResource(0);
        binding.image2.setImageResource(0);
        binding.image3.setImageResource(0);
        binding.image4.setImageResource(0);
        binding.image5.setImageResource(0);
        binding.image6.setImageResource(0);
        binding.image7.setImageResource(0);
        binding.image8.setImageResource(0);
        binding.image9.setImageResource(0);

        if (nameX.equals("Bot")) {
            turnCPU();
        } else if (nameX.equals("You")) {
            turnHuman();
        }
    }
}