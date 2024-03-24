package org.nl.javatetris;

import org.junit.jupiter.api.Test;
import org.nl.javatetris.model.Board;
import org.nl.javatetris.model.ModelConst;
import org.nl.javatetris.model.tetrominos.Tetromino;

import static org.junit.jupiter.api.Assertions.*;

public class nextTetrominotest {

    @Test
    public void testNextTetromino() {
        // Given
        Board board = new Board();

        // When
        Tetromino nextTetromino = board.getNextTetromino();
        board.spawnTetromino();
        Tetromino currentTetromino = board.getTet();

        // Then
        int[][] nextBoard = board.getNextBoard();
        assertNotNull(nextBoard); // 다음 테트로미노 보드가 null이 아닌지 확인

        // 다음 테트로미노가 제대로 nextBoard에 저장되었는지 확인
        int[][] nextShape = nextTetromino.getShape();
        for (int y = 0; y < nextShape.length; y++) {
            for (int x = 0; x < nextShape[y].length; x++) {

                int boardX = x + nextShape[y].length;
                int boardY = y + nextShape.length;


                if (nextShape[y][x] != 0) {
                    assertEquals(nextShape[y][x], nextBoard[boardY][boardX]);
                }
            }
        }
        // 현재 테트로미노가 다음 테트로미노로 업데이트되었는지 확인
        assertEquals(nextTetromino, currentTetromino);


    }
}