package org.nl.javatetris.gameplay.tetromino.item;

import org.junit.jupiter.api.Test;
import org.nl.javatetris.gameplay.tetromino.Tetromino;

import static org.junit.jupiter.api.Assertions.*;
import static org.nl.javatetris.config.constant.ModelConst.*;

class TetrominoEraseOTest {
    @Test
    public void testSetShapes() {
        // Given
        TetrominoEraseO tetromino = new TetrominoEraseO();

        // When
        tetromino.setShapes();

        // Then
        // 첫 번째 모양 테스트
        assertArrayEquals(new int[][]{
                {E, O},
                {O, O}}, tetromino.getShape());

        // 두 번째 모양 테스트
        tetromino.rotateRight(); // 회전
        assertArrayEquals(new int[][]{
                {O, E},
                {O, O}}, tetromino.getShape());

        // 세 번째 모양 테스트
        tetromino.rotateRight(); // 회전
        assertArrayEquals(new int[][]{
                {O, O},
                {O, E}}, tetromino.getShape());

        // 네 번째 모양 테스트
        tetromino.rotateRight(); // 회전
        assertArrayEquals(new int[][]{
                {O, O},
                {E, O}}, tetromino.getShape());
    }

    @Test
    public void testGetShapeNumber() {
        TetrominoEraseO tetromino = new TetrominoEraseO();
        assertEquals(EO, tetromino.getShapeNumber());
    }

    @Test
    public void testGetRotatedTetromino() {
        TetrominoEraseO tetromino = new TetrominoEraseO();
        Tetromino rotatedTetromino = tetromino.getRotatedTetromino();
        assertNotNull(rotatedTetromino);
        // 회전된 테트로미노의 모양이 올바른지 확인
        assertNotEquals(tetromino, rotatedTetromino);
    }

}