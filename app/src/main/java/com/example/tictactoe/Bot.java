package com.example.tictactoe;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Bot extends AppCompatActivity {

    private int[][] board = new int[3][3];
    private int activePlayer;
    private int human = 1;
    private int ai = 2;
    private final int[] pruner = {0, 10, -10};
    private boolean endGame = false;
    String nameX, nameO;
    int scoreX = 0;
    int scoreO = 0;
    ImageView image;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameX = getIntent().getStringExtra("namePlayerX");
        nameO = getIntent().getStringExtra("namePlayerO");
        back = findViewById(R.id.back);
        back.setOnClickListener(v -> finish());
        initializeGame();

        if (nameX.equals("Bot")) {
            turnCPU();
        } else if (nameX.equals("You")) {
            turnHuman();
        }
    }

    private void initializeGame() {
        // Additional initialization code, if any...
        if (!nameX.equals("You")){
            ai = 1;
            human = 2;
            activePlayer = ai;
        }else{
            activePlayer = human;
        }

        ((TextView) findViewById(R.id.x)).setText(nameX);
        ((TextView) findViewById(R.id.o)).setText(nameO);

    }

    private void setClickListener(int position, ImageView imageView) {
        imageView.setOnClickListener(view -> {
            int row = position / 3;
            int col = position % 3;

            if (isCellEmpty(row, col) && !endGame) {
                setInput(imageView, row, col);
                turnCPU();
            }
        });
    }

    public void turnHuman() {
        for (int i = 0; i < 9; i++) {
            ImageView imageView = getImageByIndex(i / 3, i % 3);
            if (imageView != null) {
                setClickListener(i, imageView);
            }
        }
    }

    private void disableHumanInput() {
        for (int i = 0; i < 9; i++) {
            ImageView imageView = getImageByIndex(i / 3, i % 3);
            if (imageView != null) {
                imageView.setOnClickListener(null);
            }
        }
    }

    private void enableHumanInput() {
        for (int i = 0; i < 9; i++) {
            ImageView imageView = getImageByIndex(i / 3, i % 3);
            if (imageView != null) {
                setClickListener(i, imageView);
            }
        }
    }

    public void turnCPU() {
        if (!endGame) {
            disableHumanInput();

            new Handler().postDelayed(() -> {
                int[] bestMove = findBestMove();
                image = getImageByIndex(bestMove[0], bestMove[1]);
                setInput(image, bestMove[0], bestMove[1]);

                enableHumanInput();
            }, 300);
        }
    }

    private int[] findBestMove() {
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = {-1, -1};

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (isCellEmpty(i, j)) {
                    board[i][j] = ai;
                    int score = minimax(board, 0, false);
                    board[i][j] = 0;

                    if (score > bestScore) {
                        bestScore = score;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                }
            }
        }
        return bestMove;
    }

    private int minimax(int[][] board, int depth, boolean isMaximizing) {
        int result = checkWinner();
        if (result!=-1){
            return pruner[result];
        }

        int bestScore = (isMaximizing) ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (isCellEmpty(i, j)) {
                    board[i][j] = (isMaximizing) ? ai : human;
                    int score = minimax(board, depth + 1, !isMaximizing);
                    board[i][j] = 0;

                    if ((isMaximizing && score > bestScore) || (!isMaximizing && score < bestScore)) {
                        bestScore = score;
                    }
                }
            }
        }

        return bestScore;
    }

    public int checkWinner() {
        int winner = -1;

        // Horizontal
        for (int i = 0; i < 3; i++) {
            if (equals3(board[i][0], board[i][1], board[i][2])) {
                winner = board[i][0];
            }
        }

        // Vertical
        for (int i = 0; i < 3; i++) {
            if (equals3(board[0][i], board[1][i], board[2][i])) {
                winner = board[0][i];
            }
        }

        // Diagonal
        if (equals3(board[0][0], board[1][1], board[2][2])) {
            winner = board[0][0];
        }
        if (equals3(board[2][0], board[1][1], board[0][2])) {
            winner = board[2][0];
        }


        if (winner == -1 && isBoardFull()) {
            return 0;
        } else {
            return winner;
        }
    }

    private boolean equals3(int a, int b, int c) {
        return a==b & b==c & a!=0;
    }


    private ImageView getImageByIndex(int row, int col) {
        switch (row * 3 + col) {
            case 0:
                return findViewById(R.id.image1);
            case 1:
                return findViewById(R.id.image2);
            case 2:
                return findViewById(R.id.image3);
            case 3:
                return findViewById(R.id.image4);
            case 4:
                return findViewById(R.id.image5);
            case 5:
                return findViewById(R.id.image6);
            case 6:
                return findViewById(R.id.image7);
            case 7:
                return findViewById(R.id.image8);
            case 8:
                return findViewById(R.id.image9);
            default:
                return null;
        }
    }

    public void setInput(ImageView image, int row, int col) {
        board[row][col] = activePlayer;
        int result = checkWinner();
        if (activePlayer == 1) {
            image.setImageResource(R.drawable.ximage);
            if (result==1) {
                endGame = true;
                scoreX++;
                displayWinner("win");
            } else if (result == 0) {
                endGame = true;
                displayWinner("draw");
            } else {
                changeTurn(2);
            }
        } else {
            image.setImageResource(R.drawable.oimage);
            if (result==2) {
                endGame = true;
                scoreO++;
                displayWinner("win");
            } else if (result == 0) {
                endGame = true;
                displayWinner("draw");
            } else {
                changeTurn(1);
            }
        }
    }


    public void displayWinner(String msg) {
        String winMessage;
        String drawMessage = "Match draw.";
        ResultBot result;

        if (msg.equals("win")) {
            if (activePlayer == human) {
                winMessage = "Congratulations! You won.";
            } else {
                winMessage = "You lost.";
            }
            result = new ResultBot(Bot.this, winMessage, Bot.this);
            updateScores(); // Update score text...
        } else {
            result = new ResultBot(Bot.this, drawMessage, Bot.this);
        }
        result.setCancelable(false);
        result.show();
    }

    public void changeTurn(int next) {
        activePlayer = next;
        updateUI();
    }
    private void updateUI() {
        if (activePlayer == 1) {
            findViewById(R.id.layoutX).setBackgroundResource(R.drawable.cyan_border);
            findViewById(R.id.layoutO).setBackgroundResource(R.drawable.white_border);
        } else {
            findViewById(R.id.layoutX).setBackgroundResource(R.drawable.white_border);
            findViewById(R.id.layoutO).setBackgroundResource(R.drawable.cyan_border);
        }
    }

    public void restartGame() {
        board = new int[3][3];
        activePlayer = 1;
        endGame = false;

        resetImages(); // Reset image resources...

        if (nameX.equals("Bot")) {
            turnCPU();
        } else if (nameX.equals("You")) {
            turnHuman();
        }
    }

    private void resetImages() {
        // Reset the images to default state...

        ((ImageView) findViewById(R.id.image1)).setImageResource(0);
        ((ImageView) findViewById(R.id.image2)).setImageResource(0);
        ((ImageView) findViewById(R.id.image3)).setImageResource(0);
        ((ImageView) findViewById(R.id.image4)).setImageResource(0);
        ((ImageView) findViewById(R.id.image5)).setImageResource(0);
        ((ImageView) findViewById(R.id.image6)).setImageResource(0);
        ((ImageView) findViewById(R.id.image7)).setImageResource(0);
        ((ImageView) findViewById(R.id.image8)).setImageResource(0);
        ((ImageView) findViewById(R.id.image9)).setImageResource(0);

    }

    private boolean isCellEmpty(int row, int col) {
        return board[row][col] == 0;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (isCellEmpty(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    @SuppressLint("SetTextI18n")
    private void updateScores() {
        // Update the score text...
        TextView xScore = findViewById(R.id.xScore);
        TextView oScore = findViewById(R.id.oScore);
        xScore.setText("x: " + scoreX);
        oScore.setText("o: " + scoreO);
    }
}
