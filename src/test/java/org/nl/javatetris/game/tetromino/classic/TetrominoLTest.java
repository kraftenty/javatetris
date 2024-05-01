package org.nl.javatetris.game.tetromino.classic;

import org.junit.jupiter.api.Test;
import org.nl.javatetris.game.tetromino.Tetromino;

import static org.junit.jupiter.api.Assertions.*;
import static org.nl.javatetris.config.constant.ModelConst.L;

class TetrominoLTest {
    @Test
    public void testSetShapes() {
        // Given
        TetrominoL tetrominoL = new TetrominoL();

        // When
        tetrominoL.setShapes();

        // Then
        assertArrayEquals(new int[][]{
                {0, 0, L},
                {L, L, L},
                {0, 0, 0}
        }, tetrominoL.getShape());

        tetrominoL.rotateRight(); // 회전
        assertArrayEquals(new int[][]{
                {0, L, 0},
                {0, L, 0},
                {0, L, L}
        }, tetrominoL.getShape());

        tetrominoL.rotateRight(); // 회전
        assertArrayEquals(new int[][]{
                {0, 0, 0},
                {L, L, L},
                {L, 0, 0}
        }, tetrominoL.getShape());

        tetrominoL.rotateRight(); // 회전
        assertArrayEquals(new int[][]{
                {L, L, 0},
                {0, L, 0},
                {0, L, 0}
        }, tetrominoL.getShape());
    }

    @Test
    public void testGetShapeNumber() {
        TetrominoL tetromino = new TetrominoL();
        assertEquals(L, tetromino.getShapeNumber());
    }

    @Test
    public void testGetRotatedTetromino() {
        TetrominoL tetromino = new TetrominoL();
        Tetromino rotatedTetromino = tetromino.getRotatedTetromino();
        assertNotNull(rotatedTetromino);
        // 회전된 테트로미노의 모양이 올바른지 확인
        assertNotEquals(tetromino, rotatedTetromino);
    }

}