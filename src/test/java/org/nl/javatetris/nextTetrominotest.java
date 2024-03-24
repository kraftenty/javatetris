package org.nl.javatetris;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nl.javatetris.model.Board;
import org.nl.javatetris.model.tetrominos.Tetromino;

import static org.junit.jupiter.api.Assertions.*;

public class nextTetrominotest {
    private Board board;
    @BeforeEach
    public void setUp() {
        board = new Board();
    }

    @Test
    public void testNextTetrominoGeneration() {
        Tetromino nextTetromino = board.getNextTetromino();
        assertNotNull(nextTetromino); // 다음 테트로미노가 null이 아닌지 확인
    }

    @Test
    public void testSpawnTetromino() {
        board.spawnTetromino();
        Tetromino currentTetromino = board.getTet();
        assertNotNull(currentTetromino); // 현재 테트로미노가 null이 아닌지 확인
        assertTrue(board.canSpawn()); // 테트로미노를 스폰할 수 있는지 확인
    }

}
