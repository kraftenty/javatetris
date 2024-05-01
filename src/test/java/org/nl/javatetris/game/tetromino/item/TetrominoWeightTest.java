package org.nl.javatetris.game.tetromino.item;

import org.junit.jupiter.api.Test;
import org.nl.javatetris.game.tetromino.Tetromino;

import static org.junit.jupiter.api.Assertions.*;
import static org.nl.javatetris.config.constant.ModelConst.W;

class TetrominoWeightTest {
    @Test
    public void testSetShapes() {
        // Given
        TetrominoWeight tetromino = new TetrominoWeight();

        // When
        tetromino.setShapes();

        // Then
        assertArrayEquals(new int[][]{
                {0, W, W, 0},
                {W, W, W, W}
        }, tetromino.getShape());
    }

    @Test
    public void testGetShapeNumber() {
        TetrominoWeight tetromino = new TetrominoWeight();
        assertEquals(W, tetromino.getShapeNumber());
    }

    @Test
    public void testGetRotatedTetromino() {
        TetrominoWeight tetromino = new TetrominoWeight();
        Tetromino rotatedTetromino = tetromino.getRotatedTetromino();
        assertNotNull(rotatedTetromino);
        // 회전된 테트로미노의 모양이 올바른지 확인
        assertNotEquals(tetromino, rotatedTetromino);
    }

}