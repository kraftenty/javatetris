package org.nl.javatetris.game.tetromino.classic;

import org.junit.jupiter.api.Test;
import org.nl.javatetris.game.tetromino.Tetromino;

import static org.junit.jupiter.api.Assertions.*;
import static org.nl.javatetris.config.constant.ModelConst.J;

class TetrominoJTest {
    @Test
    public void testSetShapes() {
        // Given
        TetrominoJ tetrominoJ = new TetrominoJ();

        // When
        tetrominoJ.setShapes();

        // Then
        assertArrayEquals(new int[][]{
                {J, 0, 0},
                {J, J, J},
                {0, 0, 0}
        }, tetrominoJ.getShape());

        tetrominoJ.rotateRight();
        assertArrayEquals(new int[][]{
                {0, J, J},
                {0, J, 0},
                {0, J, 0}
        }, tetrominoJ.getShape());


        tetrominoJ.rotateRight(); // 회전
        assertArrayEquals(new int[][]{
                {0, 0, 0},
                {J, J, J},
                {0, 0, J}
        }, tetrominoJ.getShape());

        // 네 번째 모양 테스트
        tetrominoJ.rotateRight(); // 회전
        assertArrayEquals(new int[][]{
                {0, J, 0},
                {0, J, 0},
                {J, J, 0}
        }, tetrominoJ.getShape());
    }

    @Test
    public void testGetShapeNumber() {
        TetrominoJ tetromino = new TetrominoJ();
        assertEquals(J, tetromino.getShapeNumber());
    }

    @Test
    public void testGetRotatedTetromino() {
        TetrominoJ tetromino = new TetrominoJ();
        Tetromino rotatedTetromino = tetromino.getRotatedTetromino();
        assertNotNull(rotatedTetromino);
        // 회전된 테트로미노의 모양이 올바른지 확인
        assertNotEquals(tetromino, rotatedTetromino);
    }

}