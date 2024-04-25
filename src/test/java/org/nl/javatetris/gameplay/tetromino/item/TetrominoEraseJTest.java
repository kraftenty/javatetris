package org.nl.javatetris.gameplay.tetromino.item;

import org.junit.jupiter.api.Test;
import org.nl.javatetris.gameplay.tetromino.Tetromino;

import static org.junit.jupiter.api.Assertions.*;
import static org.nl.javatetris.config.constant.ModelConst.*;

class TetrominoEraseJTest {
    @Test
    public void testSetShapes() {
        // Given
        TetrominoEraseJ tetromino = new TetrominoEraseJ();

        // When
        tetromino.setShapes();

        // Then
        // 첫 번째 모양 테스트
        assertArrayEquals(new int[][]{
                {E, 0, 0},
                {J, J, J},
                {0, 0, 0}}, tetromino.getShape());

        // 두 번째 모양 테스트
        tetromino.rotateRight(); // 회전
        assertArrayEquals(new int[][]{
                {0, J, E},
                {0, J, 0},
                {0, J, 0}}, tetromino.getShape());

        // 세 번째 모양 테스트
        tetromino.rotateRight(); // 회전
        assertArrayEquals(new int[][]{
                {0, 0, 0},
                {J, J, J},
                {0, 0, E}}, tetromino.getShape());

        // 네 번째 모양 테스트
        tetromino.rotateRight(); // 회전
        assertArrayEquals(new int[][]{
                {0, J, 0},
                {0, J, 0},
                {E, J, 0}}, tetromino.getShape());
    }

    @Test
    public void testGetShapeNumber() {
        TetrominoEraseJ tetromino = new TetrominoEraseJ();
        assertEquals(EJ, tetromino.getShapeNumber());
    }

    @Test
    public void testGetRotatedTetromino() {
        TetrominoEraseJ tetromino = new TetrominoEraseJ();
        Tetromino rotatedTetromino = tetromino.getRotatedTetromino();
        assertNotNull(rotatedTetromino);
        // 회전된 테트로미노의 모양이 올바른지 확인
        assertNotEquals(tetromino, rotatedTetromino);
    }

}