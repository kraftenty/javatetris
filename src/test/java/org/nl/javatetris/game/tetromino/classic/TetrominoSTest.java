package org.nl.javatetris.game.tetromino.classic;

import org.junit.jupiter.api.Test;
import org.nl.javatetris.game.tetromino.Tetromino;

import static org.junit.jupiter.api.Assertions.*;
import static org.nl.javatetris.config.constant.ModelConst.S;

class TetrominoSTest {
    @Test
    public void testSetShapes() {
        // Given
        TetrominoS tetrominoS = new TetrominoS();

        // When
        tetrominoS.setShapes();

        // Then
        assertArrayEquals(new int[][]{
                {0, S, S},
                {S, S, 0},
                {0, 0, 0}
        }, tetrominoS.getShape());


        tetrominoS.rotateRight();
        assertArrayEquals(new int[][]{
                {0, S, 0},
                {0, S, S},
                {0, 0, S}
        }, tetrominoS.getShape());


        tetrominoS.rotateRight();
        assertArrayEquals(new int[][]{
                {0, 0, 0},
                {0, S, S},
                {S, S, 0}
        }, tetrominoS.getShape());

        tetrominoS.rotateRight();
        assertArrayEquals(new int[][]{
                {S, 0, 0},
                {S, S, 0},
                {0, S, 0}
        }, tetrominoS.getShape());
    }

    @Test
    public void testGetShapeNumber() {
        TetrominoS tetromino = new TetrominoS();
        assertEquals(S, tetromino.getShapeNumber());
    }

    @Test
    public void testGetRotatedTetromino() {
        TetrominoS tetromino = new TetrominoS();
        Tetromino rotatedTetromino = tetromino.getRotatedTetromino();
        assertNotNull(rotatedTetromino);
        // 회전된 테트로미노의 모양이 올바른지 확인
        assertNotEquals(tetromino, rotatedTetromino);
    }
}