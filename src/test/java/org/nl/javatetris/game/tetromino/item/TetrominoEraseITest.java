package org.nl.javatetris.game.tetromino.item;

import org.junit.jupiter.api.Test;
import org.nl.javatetris.game.tetromino.Tetromino;

import static org.junit.jupiter.api.Assertions.*;
import static org.nl.javatetris.config.constant.ModelConst.*;

class TetrominoEraseITest {
    @Test
    public void testSetShapes() {
        // Given
        TetrominoEraseI tetromino = new TetrominoEraseI();

        // When
        tetromino.setShapes();

        // Then
        // 첫 번째 모양 테스트
        assertArrayEquals(new int[][]{
                {0, 0, 0, 0},
                {E, I, I, I},
                {0, 0, 0, 0},
                {0, 0, 0, 0}}, tetromino.getShape());

        // 두 번째 모양 테스트
        tetromino.rotateRight(); // 회전
        assertArrayEquals(new int[][]{
                {0, 0, E, 0},
                {0, 0, I, 0},
                {0, 0, I, 0},
                {0, 0, I, 0}}, tetromino.getShape());

        // 세 번째 모양 테스트
        tetromino.rotateRight(); // 회전
        assertArrayEquals(new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {I, I, I, E},
                {0, 0, 0, 0}}, tetromino.getShape());

        // 네 번째 모양 테스트
        tetromino.rotateRight(); // 회전
        assertArrayEquals(new int[][]{
                {0, I, 0, 0},
                {0, I, 0, 0},
                {0, I, 0, 0},
                {0, E, 0, 0}}, tetromino.getShape());
    }

    @Test
    public void testGetShapeNumber() {
        TetrominoEraseI tetromino = new TetrominoEraseI();
        assertEquals(EI, tetromino.getShapeNumber());
    }

    @Test
    public void testGetRotatedTetromino() {
        TetrominoEraseI tetromino = new TetrominoEraseI();
        Tetromino rotatedTetromino = tetromino.getRotatedTetromino();
        assertNotNull(rotatedTetromino);
        // 회전된 테트로미노의 모양이 올바른지 확인
        assertNotEquals(tetromino, rotatedTetromino);
    }

}