package com.nilordonez;

public class Tablero {
    private int rows,cols;
    private Casilla[][] tablero;
    int currentRow, currentCol;  // the current seed's row and column


    public Tablero(Casilla[][] tablero) {
        this.tablero = tablero;
    }

    /** Constructor to initialize the game board */
    public Tablero(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        tablero = new Casilla[rows][cols];  // allocate the array
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                tablero[row][col] = new Casilla(new Posicion(row,col)); // allocate element of the array
            }
        }
    }

    /** Initialize (or re-initialize) the contents of the game board */
    public void init() {
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                tablero[row][col].clear();  // clear the cell content
            }
        }
    }

    /** Return true if it is a draw (i.e., no more EMPTY cell) */
    public boolean isDraw() {
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                if (tablero[row][col].getContent() == Seed.EMPTY) {
                    return false; // an empty seed found, not a draw, exit
                }
            }
        }
        return true; // no empty cell, it's a draw
    }

    /** Return true if the player with "theSeed" has won after placing at
     (currentRow, currentCol) */
    public boolean hasWon(Seed theSeed) {
        return (tablero[currentRow][0].getContent() == theSeed         // 3-in-the-row
                && tablero[currentRow][1].getContent() == theSeed
                && tablero[currentRow][2].getContent() == theSeed
                || tablero[0][currentCol].getContent() == theSeed      // 3-in-the-column
                && tablero[1][currentCol].getContent() == theSeed
                && tablero[2][currentCol].getContent() == theSeed
                || currentRow == currentCol            // 3-in-the-diagonal
                && tablero[0][0].getContent() == theSeed
                && tablero[1][1].getContent() == theSeed
                && tablero[2][2].getContent() == theSeed
                || currentRow + currentCol == 2    // 3-in-the-opposite-diagonal
                && tablero[0][2].getContent() == theSeed
                && tablero[1][1].getContent() == theSeed
                && tablero[2][0].getContent() == theSeed);
    }

    /** Paint itself */
    public void paint() {
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                tablero[row][col].paint();   // each cell paints itself
                if (col < cols - 1) System.out.print("|");
            }
            System.out.println();
            if (row < rows - 1) {
                System.out.println("-----------");
            }
        }
    }

    public Casilla getCasilla(Posicion posicion){
        return tablero[posicion.getX()][posicion.getY()];
    }

    public void setCasilla(Casilla casilla, Posicion posicion){
        this.tablero[posicion.getX()][posicion.getY()] = casilla;
    }

    public Casilla[][] getTablero() {
        return tablero;
    }
}
