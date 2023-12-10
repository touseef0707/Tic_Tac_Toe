package com.example.tictactoe;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tictactoe.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private final List<int[]> winning_combos = new ArrayList<>();
    private int[] positions = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int activePlayer = 1;
    private int totalBoxesSelected = 1;
    int scoreX = 0;
    int scoreO = 0;
    String nameX, nameO;

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
        binding.image1.setOnClickListener(view -> {
            if (isCellEmpty(0)) {
                setInput((ImageView) view, 0);
            }
        });
        binding.image2.setOnClickListener(view -> {
            if (isCellEmpty(1)) {
                setInput((ImageView) view, 1);
            }
        });
        binding.image3.setOnClickListener(view -> {
            if (isCellEmpty(2)) {
                setInput((ImageView) view, 2);
            }
        });
        binding.image4.setOnClickListener(view -> {
            if (isCellEmpty(3)) {
                setInput((ImageView) view, 3);
            }
        });
        binding.image5.setOnClickListener(view -> {
            if (isCellEmpty(4)) {
                setInput((ImageView) view, 4);
            }
        });
        binding.image6.setOnClickListener(view -> {
            if (isCellEmpty(5)) {
                setInput((ImageView) view, 5);
            }
        });
        binding.image7.setOnClickListener(view -> {
            if (isCellEmpty(6)) {
                setInput((ImageView) view, 6);
            }
        });
        binding.image8.setOnClickListener(view -> {
            if (isCellEmpty(7)) {
                setInput((ImageView) view, 7);
            }
        });
        binding.image9.setOnClickListener(view -> {
            if (isCellEmpty(8)) {
                setInput((ImageView) view, 8);
            }
        });

    }

    // method to check if a cell is empty
    public Boolean isCellEmpty(int position) {
        return positions[position] == 0;
    }

    // set the input according to the player.
    public void setInput(ImageView image, int position) {

        positions[position] = activePlayer; // mark the position the player selected

        if (activePlayer == 1) {
            image.setImageResource(R.drawable.ximage);
            if (result()) {
                displayWinner("win", nameX);
            } else if (totalBoxesSelected == 9) {
                displayWinner("draw", "");
            } else {
                changeTurn(2);
                totalBoxesSelected++;
            }
        } else {
            image.setImageResource(R.drawable.oimage);
            if (result()) {
                displayWinner("win", nameO);
            } else if (totalBoxesSelected == 9) {
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
        String win_message = player + " is the winner";
        String draw_message = "Match draw.";
        Result result;
        if (msg.equals("win")) {
            result = new Result(MainActivity.this, win_message, MainActivity.this);
            binding.xScore.setText("x: " + scoreX);
            binding.oScore.setText("o: " + scoreO);
        } else {
            result = new Result(MainActivity.this, draw_message, MainActivity.this);
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
        changeTurn(1);
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
    }
}