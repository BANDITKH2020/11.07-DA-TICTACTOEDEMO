package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

public class TicTacToeController {

    @FXML private Button button00;
    @FXML private Button button01;
    @FXML private Button button02;
    @FXML private Button button10;
    @FXML private Button button11;
    @FXML private Button button12;
    @FXML private Button button20;
    @FXML private Button button21;
    @FXML private Button button22;
    @FXML private Button resetButton;

    private boolean isXTurn = true;  // true = X's turn, false = O's turn

    @FXML
    private void initialize() {
        // Add action listeners for all buttons
        resetButton.setOnAction(this::handleResetButtonClick);
    }

    @FXML
    private void handleButtonClick(ActionEvent event) {
        Button button = (Button) event.getSource();
        if (button.getText().isEmpty()) {
            button.setText(isXTurn ? "X" : "O");
            isXTurn = !isXTurn;
            if (checkForWinner()) {
                return;
            }
        }
    }

    @FXML
    private void handleResetButtonClick(ActionEvent event) {
        button00.setText("");
        button01.setText("");
        button02.setText("");
        button10.setText("");
        button11.setText("");
        button12.setText("");
        button20.setText("");
        button21.setText("");
        button22.setText("");
        button00.setStyle(""); // Reset background color
        button01.setStyle(""); // Reset background color
        button02.setStyle(""); // Reset background color
        button10.setStyle(""); // Reset background color
        button11.setStyle(""); // Reset background color
        button12.setStyle(""); // Reset background color
        button20.setStyle(""); // Reset background color
        button21.setStyle(""); // Reset background color
        button22.setStyle(""); // Reset background color
        resetButton.setDisable(true); // Disable the Reset button
        isXTurn = true;  // X starts the game
    }

    private boolean checkForWinner() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (checkLine(button00, button01, button02) ||
                    checkLine(button10, button11, button12) ||
                    checkLine(button20, button21, button22) ||
                    checkLine(button00, button10, button20) ||
                    checkLine(button01, button11, button21) ||
                    checkLine(button02, button12, button22) ||
                    checkLine(button00, button11, button22) ||
                    checkLine(button02, button11, button20)) {
                resetButton.setDisable(false); // Enable Reset button when game ends
                return true;
            }
        }

        // Check for a draw
        boolean isDraw = true;
        for (Button[] row : new Button[][]{{button00, button01, button02}, {button10, button11, button12}, {button20, button21, button22}}) {
            for (Button btn : row) {
                if (btn.getText().isEmpty()) {
                    isDraw = false;
                    break;
                }
            }
        }
        if (isDraw) {
            System.out.println("It's a draw!");
            resetButton.setDisable(false); // Enable Reset button when game ends
        }
        return false;
    }

    private boolean checkLine(Button b1, Button b2, Button b3) {
        if (!b1.getText().isEmpty() && b1.getText().equals(b2.getText()) && b1.getText().equals(b3.getText())) {
            b1.setStyle("-fx-background-color: lightgreen;");
            b2.setStyle("-fx-background-color: lightgreen;");
            b3.setStyle("-fx-background-color: lightgreen;");
            System.out.println(b1.getText() + " wins!");
            return true;
        }
        return false;
    }
}
