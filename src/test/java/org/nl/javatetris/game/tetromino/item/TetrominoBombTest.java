package org.nl.javatetris.game.tetromino.item;

import org.junit.jupiter.api.Test;
import org.nl.javatetris.game.tetromino.Tetromino;

import static org.junit.jupiter.api.Assertions.*;
import static org.nl.javatetris.config.constant.ModelConst.B;

class TetrominoBombTest {

    @Test
    public void testTetrominoBombCreation() {
        // Given
        TetrominoBomb bomb = new TetrominoBomb();

        // Then
        assertNotNull(bomb);
    }

    @Test
    public void testTetrominoBombShape() {
        // Given
        TetrominoBomb bomb = new TetrominoBomb();

        // When
        int[][] shape = bomb.getShape();

        // Then
        assertArrayEquals(new int[][]{{B}
        }, bomb.getShape());

    }

    @Test
    public void testTetrominoBombRotation() {
        // Given
        TetrominoBomb bomb = new TetrominoBomb();

        // When
        Tetromino rotatedBomb = bomb.getRotatedTetromino();

        // Then
        assertEquals(bomb.getShapeIndex(), rotatedBomb.getShapeIndex());
    }

}