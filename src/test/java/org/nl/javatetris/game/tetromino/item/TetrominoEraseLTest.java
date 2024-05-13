package org.nl.javatetris.game.tetromino.item;

import org.junit.jupiter.api.Test;
import org.nl.javatetris.game.tetromino.Tetromino;

import static org.junit.jupiter.api.Assertions.*;
import static org.nl.javatetris.config.constant.ModelConst.*;

class TetrominoEraseLTest {
    @Test
    public void testSetShapes() {
        // Given
        TetrominoEraseL tetromino = new TetrominoEraseL();

        // When
        tetromino.setShapes();

        // Then
        // 첫 번째 모양 테스트
        assertArrayEquals(new int[][]{
                {0, 0, E},
                {L, L, L},
                {0, 0, 0}}, tetromino.getShape());

        // 두 번째 모양 테스트
        tetromino.rotateRight(); // 회전
        assertArrayEquals(new int[][]{
                {0, L, 0},
                {0, L, 0},
                {0, L, E}}, tetromino.getShape());

        // 세 번째 모양 테스트
        tetromino.rotateRight(); // 회전
        assertArrayEquals(new int[][]{
                {0, 0, 0},
                {L, L, L},
                {E, 0, 0}}, tetromino.getShape());

        // 네 번째 모양 테스트
        tetromino.rotateRight(); // 회전
        assertArrayEquals(new int[][]{
                {E, L, 0},
                {0, L, 0},
                {0, L, 0}}, tetromino.getShape());
    }

    @Test
    public void testGetShapeNumber() {
        TetrominoEraseL tetromino = new TetrominoEraseL();
        assertEquals(EL, tetromino.getShapeNumber());
    }

    @Test
    public void testGetRotatedTetromino() {
        TetrominoEraseL tetromino = new TetrominoEraseL();
        Tetromino rotatedTetromino = tetromino.getRotatedTetromino();
        assertNotNull(rotatedTetromino);
        // 회전된 테트로미노의 모양이 올바른지 확인
        assertNotEquals(tetromino, rotatedTetromino);
    }

}