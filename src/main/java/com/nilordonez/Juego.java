package com.nilordonez;

import java.util.Scanner;

public class Juego {

    private Tablero tablero;
    private State currentState;
    private Seed currentPlayer;

    private static Scanner in = new Scanner(System.in);  // input Scanner

    /** Constructor to setup the game */
    public Juego() {
        tablero = new Tablero(3,3);  // allocate game-board

        // Initialize the game-board and current status
        initGame();
        // Play the game once. Players CROSS and NOUGHT move alternately.
        do {
            playerMove(currentPlayer); // update the content, currentRow and currentCol
            tablero.paint();             // ask the board to paint itself
            updateGame(currentPlayer); // update currentState
            // Print message if game-over
            if (currentState == State.CROSSWON) {
                System.out.println("'X' won! Bye!");
            } else if (currentState == State.NOUGHTWON) {
                System.out.println("'O' won! Bye!");
            } else if (currentState == State.DRAW) {
                System.out.println("It's Draw! Bye!");
            }
            // Switch player
            currentPlayer = (currentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
        } while (currentState == State.PLAYING);  // repeat until game-over
    }

    /** Initialize the game-board contents and the current states */
    public void initGame() {
        tablero.init();  // clear the board contents
        currentPlayer = Seed.CROSS;       // CROSS plays first
        currentState = State.PLAYING; // ready to play
    }

    /** The player with "theSeed" makes one move, with input validation.
     Update Cell's content, Board's currentRow and currentCol. */
    public void playerMove(Seed theSeed) {
        boolean validInput = false;  // for validating input
        do {
            if (theSeed == Seed.CROSS) {
                System.out.print("Player 'X', enter your move (row[1-3] column[1-3]): ");
            } else {
                System.out.print("Player 'O', enter your move (row[1-3] column[1-3]): ");
            }
            int row = in.nextInt() - 1;
            int col = in.nextInt() - 1;
            if (row >= 0 && row < 3 && col >= 0 && col < 3
                    && tablero.getTablero()[row][col].getContent() == Seed.EMPTY) {
                tablero.getTablero()[row][col].setContent(theSeed);
                tablero.currentRow = row;
                tablero.currentCol = col;
                validInput = true; // input okay, exit loop
            } else {
                System.out.println("This move at (" + (row + 1) + "," + (col + 1)
                        + ") is not valid. Try again...");
            }
        } while (!validInput);   // repeat until input is valid
    }

    /** Update the currentState after the player with "theSeed" has moved */
    public void updateGame(Seed theSeed) {
        if (tablero.hasWon(theSeed)) {  // check for win
            currentState = (theSeed == Seed.CROSS) ? State.CROSSWON : State.NOUGHTWON;
        } else if (tablero.isDraw()) {  // check for draw
            currentState = State.DRAW;
        }
        // Otherwise, no change to current state (still GameState.PLAYING).
    }


    /** The entry main() method */
    public static void main(String[] args) {
        new Juego();  // Let the constructor do the job
    }


}
