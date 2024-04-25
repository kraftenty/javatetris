package org.nl.javatetris.gameplay.tetromino.classic;
import org.junit.jupiter.api.Test;
import org.nl.javatetris.gameplay.tetromino.Tetromino;
import static org.junit.jupiter.api.Assertions.*;
import static org.nl.javatetris.config.constant.ModelConst.I;

class TetrominoITest {
    @Test
    public void testSetShapes() {
        // Given
        TetrominoI tetrominoI = new TetrominoI();

        // When
        tetrominoI.setShapes();

        // Then
        // 첫 번째 모양 테스트
        assertArrayEquals(new int[][]{
                {0, 0, 0, 0},
                {I, I, I, I},
                {0, 0, 0, 0},
                {0, 0, 0, 0}}, tetrominoI.getShape());

        // 두 번째 모양 테스트
        tetrominoI.rotateRight(); // 회전
        assertArrayEquals(new int[][]{
                {0, 0, I, 0},
                {0, 0, I, 0},
                {0, 0, I, 0},
                {0, 0, I, 0}}, tetrominoI.getShape());

        // 세 번째 모양 테스트
        tetrominoI.rotateRight(); // 회전
        assertArrayEquals(new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {I, I, I, I},
                {0, 0, 0, 0}}, tetrominoI.getShape());

        // 네 번째 모양 테스트
        tetrominoI.rotateRight(); // 회전
        assertArrayEquals(new int[][]{
                {0, I, 0, 0},
                {0, I, 0, 0},
                {0, I, 0, 0},
                {0, I, 0, 0}}, tetrominoI.getShape());
    }

    @Test
    public void testGetShapeNumber() {
        TetrominoI tetromino = new TetrominoI();
        assertEquals(I, tetromino.getShapeNumber());
    }

    @Test
    public void testGetRotatedTetromino() {
        TetrominoI tetromino = new TetrominoI();
        Tetromino rotatedTetromino = tetromino.getRotatedTetromino();
        assertNotNull(rotatedTetromino);
        // 회전된 테트로미노의 모양이 올바른지 확인
        assertNotEquals(tetromino, rotatedTetromino);
    }

   }