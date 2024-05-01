package org.nl.javatetris.game.tetromino.classic;

import org.junit.jupiter.api.Test;
import org.nl.javatetris.game.tetromino.Tetromino;

import static org.junit.jupiter.api.Assertions.*;
import static org.nl.javatetris.config.constant.ModelConst.T;

class TetrominoTTest {
    @Test
    public void testSetShapes() {
        // Given
        TetrominoT tetrominoT = new TetrominoT();

        // When
        tetrominoT.setShapes();

        // Then
        assertArrayEquals(new int[][]{
                {0, T, 0},
                {T, T, T},
                {0, 0, 0}
        }, tetrominoT.getShape());

        tetrominoT.rotateRight();
        assertArrayEquals(new int[][]{
                {0, T, 0},
                {0, T, T},
                {0, T, 0}
        }, tetrominoT.getShape());


        tetrominoT.rotateRight();
        assertArrayEquals(new int[][]{
                {0, 0, 0},
                {T, T, T},
                {0, T, 0}
        }, tetrominoT.getShape());

        tetrominoT.rotateRight();
        assertArrayEquals(new int[][]{
                {0, T, 0},
                {T, T, 0},
                {0, T, 0}
        }, tetrominoT.getShape());
    }

    @Test
    public void testGetShapeNumber() {
        TetrominoT tetromino = new TetrominoT();
        assertEquals(T, tetromino.getShapeNumber());
    }

    @Test
    public void testGetRotatedTetromino() {
        TetrominoT tetromino = new TetrominoT();
        Tetromino rotatedTetromino = tetromino.getRotatedTetromino();
        assertNotNull(rotatedTetromino);
        // 회전된 테트로미노의 모양이 올바른지 확인
        assertNotEquals(tetromino, rotatedTetromino);
    }

}