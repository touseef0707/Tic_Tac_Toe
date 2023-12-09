package com.example.tictactoe;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class ResultBot extends Dialog {
    private final String message;
    private final Bot bot;

    public ResultBot(@NonNull Context context, String message, Bot bot) {
        super(context);
        this.message = message;
        this.bot = bot;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView result = findViewById(R.id.result);
        result.setText(message);

        Button restart = findViewById(R.id.restart);
        restart.setOnClickListener(view -> {
            bot.restartGame();
            dismiss();
        });
    }
}