package com.example.tiktaktoe;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private boolean playerXTurn = true;
    private int moveCount = 0;
    private final Button[][] buttons = new Button[3][3];

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView playerTurn = findViewById(R.id.playerTurn);
        GridLayout gridLayout = findViewById(R.id.gridLayout);

        // Initialize buttons
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int buttonId = getResources().getIdentifier("button" + (i * 3 + j + 1), "id", getPackageName());
                buttons[i][j] = findViewById(buttonId);
                buttons[i][j].setOnClickListener(v -> {
                    Button button = (Button) v;

                    // If the button is already clicked, do nothing
                    if (!button.getText().toString().isEmpty()) return;

                    // Mark the button
                    button.setText(playerXTurn ? "X" : "O");
                    moveCount++;

                    // Check for a winner
                    if (checkWinner()) {
                        playerTurn.setText((playerXTurn ? "Player X" : "Player O") + " Wins!");
                        disableButtons();
                        return;
                    }

                    // Check for a draw
                    if (moveCount == 9) {
                        playerTurn.setText("It's a Draw!");
                        return;
                    }

                    // Switch turn
                    playerXTurn = !playerXTurn;
                    playerTurn.setText(playerXTurn ? "Player X's Turn" : "Player O's Turn");
                });
            }
        }
    }

    private boolean checkWinner() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().toString().equals(buttons[i][1].getText().toString()) &&
                    buttons[i][1].getText().toString().equals(buttons[i][2].getText().toString()) &&
                    !buttons[i][0].getText().toString().isEmpty()) {
                return true;
            }

            if (buttons[0][i].getText().toString().equals(buttons[1][i].getText().toString()) &&
                    buttons[1][i].getText().toString().equals(buttons[2][i].getText().toString()) &&
                    !buttons[0][i].getText().toString().isEmpty()) {
                return true;
            }
        }

        // Check diagonals
        if (buttons[0][0].getText().toString().equals(buttons[1][1].getText().toString()) &&
                buttons[1][1].getText().toString().equals(buttons[2][2].getText().toString()) &&
                !buttons[0][0].getText().toString().isEmpty()) {
            return true;
        }

        return buttons[0][2].getText().toString().equals(buttons[1][1].getText().toString()) &&
                buttons[1][1].getText().toString().equals(buttons[2][0].getText().toString()) &&
                !buttons[0][2].getText().toString().isEmpty();
    }

    private void disableButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }
}
