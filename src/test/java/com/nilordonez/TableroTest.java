package com.nilordonez;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class TableroTest {

    /**
     * El metodo isDraw solo comprueba si hay alguna casilla vacía y devuelve true si todas estan llenas. Sin importar el contenido
     * Ya que en el juego ya se encarga de comprobar si gana alguien antes de hacer call a isDraw.
     */
    @Test
    public void isDrawWhenItsTrullyDraw() {
        Tablero tablero = new Tablero(3,3);

        Casilla[][] tableroCasillas = new Casilla[3][3];

        tableroCasillas[0][0] = Mockito.mock(Casilla.class);
        Casilla casilla1 = tableroCasillas[0][0];
        Mockito.when(casilla1.getContent()).thenReturn(Seed.NOUGHT);

        tableroCasillas[0][1] = Mockito.mock(Casilla.class);
        Casilla casilla2 = tableroCasillas[0][1];
        Mockito.when(casilla2.getContent()).thenReturn(Seed.CROSS);

        tableroCasillas[0][2] = Mockito.mock(Casilla.class);
        Casilla casilla3 = tableroCasillas[0][2];
        Mockito.when(casilla3.getContent()).thenReturn(Seed.NOUGHT);

        tableroCasillas[1][0] = Mockito.mock(Casilla.class);
        Casilla casilla4 = tableroCasillas[1][0];
        Mockito.when(casilla4.getContent()).thenReturn(Seed.NOUGHT);

        tableroCasillas[1][1] = Mockito.mock(Casilla.class);
        Casilla casilla5 = tableroCasillas[1][1];
        Mockito.when(casilla5.getContent()).thenReturn(Seed.CROSS);

        tableroCasillas[1][2] = Mockito.mock(Casilla.class);
        Casilla casilla6 = tableroCasillas[1][2];
        Mockito.when(casilla6.getContent()).thenReturn(Seed.CROSS);

        tableroCasillas[2][0] = Mockito.mock(Casilla.class);
        Casilla casilla7 = tableroCasillas[2][0];
        Mockito.when(casilla7.getContent()).thenReturn(Seed.CROSS);

        tableroCasillas[2][1] = Mockito.mock(Casilla.class);
        Casilla casilla8 = tableroCasillas[2][1];
        Mockito.when(casilla8.getContent()).thenReturn(Seed.NOUGHT);

        tableroCasillas[2][2] = Mockito.mock(Casilla.class);
        Casilla casilla9 = tableroCasillas[2][2];
        Mockito.when(casilla9.getContent()).thenReturn(Seed.CROSS);

        tablero.setTablero(tableroCasillas);
        Assert.assertTrue(tablero.isDraw());
    }

    @Test
    public void isDrawReturnsFalseWhenItsNotADraw(){
        Tablero tablero = new Tablero(3,3);

        Casilla[][] tableroCasillas = new Casilla[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Casilla mock = Mockito.mock(Casilla.class);
                Mockito.when(mock.getContent()).thenReturn(Seed.CROSS);
                tableroCasillas[i][j]  = mock;
            }
        }

        tablero.setTablero(tableroCasillas);

        Assert.assertFalse(tablero.isDraw());
    }

    @Test
    public void crossHasWonIfWinnerLineIsInARow() {

        Tablero tablero = new Tablero(3,3);

        Casilla[][] tableroCasillas = new Casilla[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Casilla mock = Mockito.mock(Casilla.class);
                if ( i == 0){ Mockito.when(mock.getContent()).thenReturn(Seed.CROSS); }
                tableroCasillas[i][j]  = mock;
            }
        }
        tablero.setTablero(tableroCasillas);

        Assert.assertTrue(tablero.hasWon(Seed.CROSS));
    }

    @Test
    public void crossHasWonIfWinnerLineIsInAColumn(){
        Tablero tablero = new Tablero(3,3);

        Casilla[][] tableroCasillas = new Casilla[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Casilla mock = Mockito.mock(Casilla.class);
                if ( j == 0){ Mockito.when(mock.getContent()).thenReturn(Seed.CROSS); }
                tableroCasillas[i][j]  = mock;
            }
        }
        tablero.setTablero(tableroCasillas);

        Assert.assertTrue(tablero.hasWon(Seed.CROSS));
    }

    @Test
    public void crossHasWonIfWinnerLineIsDiagonal(){
        Tablero tablero = new Tablero(3,3);

        Casilla[][] tableroCasillas = new Casilla[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tableroCasillas[i][j] = Mockito.mock(Casilla.class);
            }
        }

        Casilla mockedCell = tableroCasillas[0][0];
        Mockito.when(mockedCell.getContent()).thenReturn(Seed.CROSS);

        Casilla mockedCell2 = tableroCasillas[1][1];
        Mockito.when(mockedCell2.getContent()).thenReturn(Seed.CROSS);

        Casilla mockedCell3 = tableroCasillas[2][2];
        Mockito.when(mockedCell3.getContent()).thenReturn(Seed.CROSS);

        tablero.setTablero(tableroCasillas);

        Assert.assertTrue(tablero.hasWon(Seed.CROSS));
    }

    @Test
    public void crossHasWonIfWinnerLineIsInvertedDiagonal(){
        Tablero tablero = new Tablero(3,3);

        Casilla[][] tableroCasillas = new Casilla[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tableroCasillas[i][j]  = Mockito.mock(Casilla.class);
            }
        }

        Casilla mockedCell = tableroCasillas[0][2];
        Mockito.when(mockedCell.getContent()).thenReturn(Seed.CROSS);

        Casilla mockedCell2 = tableroCasillas[1][1];
        Mockito.when(mockedCell2.getContent()).thenReturn(Seed.CROSS);

        Casilla mockedCell3 = tableroCasillas[2][0];
        Mockito.when(mockedCell3.getContent()).thenReturn(Seed.CROSS);

        tablero.setTablero(tableroCasillas);

        Assert.assertTrue(tablero.hasWon(Seed.CROSS));
    }




}