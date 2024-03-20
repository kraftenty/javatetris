package org.nl.javatetris.model.tetrominos;

public class TetrominoO implements Tetromino {

    private static int[][][] shapes = {
        {
            {1, 1},
            {1, 1}
        }
    };

    private int currentShapeIndex = 0;


    @Override
    public void rotateRight() {

    }

    @Override
    public void rotateLeft() {

    }

    @Override
    public int[][] getCurrentShape() {
        return shapes[currentShapeIndex];
    }
}
