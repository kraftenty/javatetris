package org.nl.javatetris.game.tetromino.classic;

import org.junit.jupiter.api.Test;
import org.nl.javatetris.game.tetromino.Tetromino;

import static org.junit.jupiter.api.Assertions.*;
import static org.nl.javatetris.config.constant.ModelConst.Z;

class TetrominoZTest {
    @Test
    public void testSetShapes() {
        // Given
        TetrominoZ tetrominoZ = new TetrominoZ();

        // When
        tetrominoZ.setShapes();

        // Then
        assertArrayEquals(new int[][]{
                {Z, Z, 0},
                {0, Z, Z},
                {0, 0, 0}
        }, tetrominoZ.getShape());


        tetrominoZ.rotateRight();
        assertArrayEquals(new int[][]{
                {0, 0, Z},
                {0, Z, Z},
                {0, Z, 0}
        }, tetrominoZ.getShape());


        tetrominoZ.rotateRight();
        assertArrayEquals(new int[][]{
                {0, 0, 0},
                {Z, Z, 0},
                {0, Z, Z}
        }, tetrominoZ.getShape());

        tetrominoZ.rotateRight();
        assertArrayEquals(new int[][]{
                {0, Z, 0},
                {Z, Z, 0},
                {Z, 0, 0}
        }, tetrominoZ.getShape());
    }

    @Test
    public void testGetShapeNumber() {
        TetrominoZ tetromino = new TetrominoZ();
        assertEquals(Z, tetromino.getShapeNumber());
    }

    @Test
    public void testGetRotatedTetromino() {
        TetrominoZ tetromino = new TetrominoZ();
        Tetromino rotatedTetromino = tetromino.getRotatedTetromino();
        assertNotNull(rotatedTetromino);
        // 회전된 테트로미노의 모양이 올바른지 확인
        assertNotEquals(tetromino, rotatedTetromino);
    }


}