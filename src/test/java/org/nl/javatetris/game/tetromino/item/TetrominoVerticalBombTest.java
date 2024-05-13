package org.nl.javatetris.game.tetromino.item;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.nl.javatetris.config.constant.ModelConst.V;

class TetrominoVerticalBombTest {

    @Test
    public void testSetShapes() {
        // Given
        TetrominoVerticalBomb tetromino = new TetrominoVerticalBomb();

        // When
        tetromino.setShapes();

        // Then
        assertArrayEquals(new int[][]{{V}}, tetromino.getShape());
    }

    @Test
    public void testGetShapeNumber() {
        TetrominoVerticalBomb tetromino = new TetrominoVerticalBomb();
        assertEquals(V, tetromino.getShapeNumber());
    }

}