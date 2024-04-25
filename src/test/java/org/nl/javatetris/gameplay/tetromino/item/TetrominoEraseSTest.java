package org.nl.javatetris.gameplay.tetromino.item;

import org.junit.jupiter.api.Test;
import org.nl.javatetris.gameplay.tetromino.Tetromino;

import static org.junit.jupiter.api.Assertions.*;
import static org.nl.javatetris.config.constant.ModelConst.*;

class TetrominoEraseSTest {
    @Test
    public void testSetShapes() {
        // Given
        TetrominoEraseS tetromino = new TetrominoEraseS();

        // When
        tetromino.setShapes();

        // Then
        // 첫 번째 모양 테스트
        assertArrayEquals(new int[][]{
                {0, S, E},
                {S, S, 0},
                {0, 0, 0}}, tetromino.getShape());

        // 두 번째 모양 테스트
        tetromino.rotateRight(); // 회전
        assertArrayEquals(new int[][]{
                {0, S, 0},
                {0, S, S},
                {0, 0, E}}, tetromino.getShape());

        // 세 번째 모양 테스트
        tetromino.rotateRight(); // 회전
        assertArrayEquals(new int[][]{
                {0, 0, 0},
                {0, S, S},
                {E, S, 0}}, tetromino.getShape());

        // 네 번째 모양 테스트
        tetromino.rotateRight(); // 회전
        assertArrayEquals(new int[][]{
                {E, 0, 0},
                {S, S, 0},
                {0, S, 0}}, tetromino.getShape());
    }

    @Test
    public void testGetShapeNumber() {
        TetrominoEraseS tetromino = new TetrominoEraseS();
        assertEquals(ES, tetromino.getShapeNumber());
    }

    @Test
    public void testGetRotatedTetromino() {
        TetrominoEraseS tetromino = new TetrominoEraseS();
        Tetromino rotatedTetromino = tetromino.getRotatedTetromino();
        assertNotNull(rotatedTetromino);
        // 회전된 테트로미노의 모양이 올바른지 확인
        assertNotEquals(tetromino, rotatedTetromino);
    }

}