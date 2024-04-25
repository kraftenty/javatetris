package org.nl.javatetris.gameplay.tetromino.item;

import org.junit.jupiter.api.Test;
import org.nl.javatetris.gameplay.tetromino.Tetromino;

import static org.junit.jupiter.api.Assertions.*;
import static org.nl.javatetris.config.constant.ModelConst.*;

class TetrominoEraseTTest {
    @Test
    public void testSetShapes() {
        // Given
        TetrominoEraseT tetromino = new TetrominoEraseT();

        // When
        tetromino.setShapes();

        // Then
        // 첫 번째 모양 테스트
        assertArrayEquals(new int[][]{
                {0, E, 0},
                {T, T, T},
                {0, 0, 0}}, tetromino.getShape());

        // 두 번째 모양 테스트
        tetromino.rotateRight(); // 회전
        assertArrayEquals(new int[][]{
                {0, T, 0},
                {0, T, E},
                {0, T, 0}}, tetromino.getShape());

        // 세 번째 모양 테스트
        tetromino.rotateRight(); // 회전
        assertArrayEquals(new int[][]{
                {0, 0, 0},
                {T, T, T},
                {0, E, 0}}, tetromino.getShape());

        // 네 번째 모양 테스트
        tetromino.rotateRight(); // 회전
        assertArrayEquals(new int[][]{
                {0, T, 0},
                {E, T, 0},
                {0, T, 0}}, tetromino.getShape());
    }

    @Test
    public void testGetShapeNumber() {
        TetrominoEraseT tetromino = new TetrominoEraseT();
        assertEquals(ET, tetromino.getShapeNumber());
    }

    @Test
    public void testGetRotatedTetromino() {
        TetrominoEraseT tetromino = new TetrominoEraseT();
        Tetromino rotatedTetromino = tetromino.getRotatedTetromino();
        assertNotNull(rotatedTetromino);
        // 회전된 테트로미노의 모양이 올바른지 확인
        assertNotEquals(tetromino, rotatedTetromino);
    }

}