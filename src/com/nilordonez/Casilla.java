package com.nilordonez;

public class Casilla {
    int row;
    int col;
    private Seed content;

    public Seed getContent() {
        return content;
    }

    public void setContent(Seed content) {
        this.content = content;
    }

    public Casilla(Posicion posicion) {
        this.row = posicion.getX();
        this.col = posicion.getY();
    }

    public Casilla() {
        this.content = Seed.EMPTY;
    }

    /**
     * Clear the cell content to EMPTY
     */
    public void clear() {
        content = Seed.EMPTY;
    }

    public void paint() {
        switch (content) {
            case CROSS:
                System.out.print(" X ");
                break;
            case NOUGHT:
                System.out.print(" O ");
                break;
            case EMPTY:
                System.out.print("   ");
                break;
        }
    }


}
