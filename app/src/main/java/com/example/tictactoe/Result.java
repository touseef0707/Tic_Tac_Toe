package com.example.tictactoe;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class Result extends Dialog {
    private final String message;
    private final MainActivity mainActivity;

    public Result(@NonNull Context context, String message, MainActivity mainActivity){
        super(context);
        this.message = message;
        this.mainActivity = mainActivity;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView result = findViewById(R.id.result);
        result.setText(message);

        Button restart = findViewById(R.id.restart);
        restart.setOnClickListener(view -> {
            mainActivity.restartGame();
            dismiss();
        });
    }
}