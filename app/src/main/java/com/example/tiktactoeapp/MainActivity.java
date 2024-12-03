package com.example.tiktactoeapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private String currentPlayer = "X";
    private boolean gameActive = true;
    private String[] board = {"", "", "", "", "", "", "", "", ""};
    private TextView statusTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusTextView = findViewById(R.id.statusTextView);
        setupGame();
    }

    private void setupGame() {
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            Button button = (Button) gridLayout.getChildAt(i);
            int finalI = i;
            button.setOnClickListener(view -> makeMove(button, finalI));
        }

        findViewById(R.id.restartButton).setOnClickListener(view -> restartGame());
    }

    private void makeMove(Button button, int position) {
        if (!gameActive || !board[position].isEmpty()) return;

        board[position] = currentPlayer;
        button.setText(currentPlayer);

        if (checkWin()) {
            statusTextView.setText("Player " + currentPlayer + " Wins!");
            gameActive = false;
        } else if (isBoardFull()) {
            statusTextView.setText("It's a Draw!");
            gameActive = false;
        } else {
            currentPlayer = currentPlayer.equals("X") ? "O" : "X";
            statusTextView.setText("Player " + currentPlayer + "'s Turn");
        }
    }

    private boolean checkWin() {
        int[][] winPositions = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Rows
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Columns
                {0, 4, 8}, {2, 4, 6}            // Diagonals
        };

        for (int[] pos : winPositions) {
            if (board[pos[0]].equals(currentPlayer) &&
                    board[pos[1]].equals(currentPlayer) &&
                    board[pos[2]].equals(currentPlayer)) {
                return true;
            }
        }
        return false;
    }

    private boolean isBoardFull() {
        for (String cell : board) {
            if (cell.isEmpty()) return false;
        }
        return true;
    }

    private void restartGame() {
        currentPlayer = "X";
        gameActive = true;
        board = new String[]{"", "", "", "", "", "", "", "", ""};
        statusTextView.setText("Player X's Turn");

        GridLayout gridLayout = findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            Button button = (Button) gridLayout.getChildAt(i);
            button.setText("");
        }
    }
}
