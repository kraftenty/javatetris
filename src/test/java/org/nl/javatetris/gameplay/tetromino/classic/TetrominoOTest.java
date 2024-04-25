package org.nl.javatetris.gameplay.tetromino.classic;

import org.junit.jupiter.api.Test;
import org.nl.javatetris.gameplay.tetromino.Tetromino;

import static org.junit.jupiter.api.Assertions.*;
import static org.nl.javatetris.config.constant.ModelConst.O;

class TetrominoOTest {
    @Test
    public void testSetShapes() {
        // Given
        TetrominoO tetrominoO = new TetrominoO();

        // When
        tetrominoO.setShapes();

        // Then
        assertArrayEquals(new int[][]{
                {O, O},
                {O, O}
        }, tetrominoO.getShape());
    }

    @Test
    public void testGetShapeNumber() {
        TetrominoO tetromino = new TetrominoO();
        assertEquals(O, tetromino.getShapeNumber());
    }

    @Test
    public void testGetRotatedTetromino() {
        TetrominoO tetromino = new TetrominoO();
        Tetromino rotatedTetromino = tetromino.getRotatedTetromino();
        assertNotNull(rotatedTetromino);
        assertArrayEquals(tetromino.getShape(), rotatedTetromino.getShape());
        assertNotSame(tetromino, rotatedTetromino);
    }

}