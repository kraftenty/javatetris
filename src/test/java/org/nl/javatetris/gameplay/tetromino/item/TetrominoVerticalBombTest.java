package org.nl.javatetris.gameplay.tetromino.item;

import org.junit.jupiter.api.Test;
import org.nl.javatetris.gameplay.tetromino.Tetromino;

import static org.junit.jupiter.api.Assertions.*;
import static org.nl.javatetris.config.constant.ModelConst.V;
import static org.nl.javatetris.config.constant.ModelConst.W;

class TetrominoVerticalBombTest {

    @Test
    public void testSetShapes() {
        // Given
        TetrominoVerticalBomb tetromino = new TetrominoVerticalBomb();

        // When
        tetromino.setShapes();

        // Then
        assertArrayEquals(new int[][]{{V}}, tetromino.getShape());
    }

    @Test
    public void testGetShapeNumber() {
        TetrominoVerticalBomb tetromino = new TetrominoVerticalBomb();
        assertEquals(V, tetromino.getShapeNumber());
    }

    @Test
    public void testGetRotatedTetromino() {
        TetrominoVerticalBomb tetromino = new TetrominoVerticalBomb();
        Tetromino rotatedTetromino = tetromino.getRotatedTetromino();
        assertNull(rotatedTetromino);
    }

}