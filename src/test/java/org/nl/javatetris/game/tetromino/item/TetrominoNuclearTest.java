package org.nl.javatetris.game.tetromino.item;
import org.junit.jupiter.api.Test;
import org.nl.javatetris.game.tetromino.Tetromino;

import static org.junit.jupiter.api.Assertions.*;
import static org.nl.javatetris.config.constant.ModelConst.N;

class TetrominoNuclearTest {
    @Test
    void testTetrominoNuclearCreation() {
        // Given
        TetrominoNuclear nuclear = new TetrominoNuclear();

        // Then
        assertNotNull(nuclear);
    }

    @Test
    void testTetrominoNuclearShape() {
        // Given
        TetrominoNuclear nuclear = new TetrominoNuclear();

        // When
        int[][] shape = nuclear.getShape();

        // Then
        assertArrayEquals(new int[][]{{N}}, shape);
    }

    @Test
    void testTetrominoNuclearRotation() {
        // Given
        TetrominoNuclear nuclear = new TetrominoNuclear();

        // When
        Tetromino rotatedNuclear = nuclear.getRotatedTetromino();

        // Then
        assertEquals(nuclear.getShapeIndex(), rotatedNuclear.getShapeIndex());
    }
}