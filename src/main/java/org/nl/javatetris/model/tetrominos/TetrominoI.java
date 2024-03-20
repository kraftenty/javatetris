package org.nl.javatetris.model.tetrominos;

public class TetrominoI implements Tetromino {

    private static int[][][] shapes = {
        {
                {0, 0, 0, 0},
                {1, 1, 1, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        },
        {
                {0, 0, 1, 0},
                {0, 0, 1, 0},
                {0, 0, 1, 0},
                {0, 0, 1, 0}
        },
        {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 1, 1, 1},
                {0, 0, 0, 0}
        },
        {
                {0, 1, 0, 0},
                {0, 1, 0, 0},
                {0, 1, 0, 0},
                {0, 1, 0, 0}
        }
    };

    private int currentShapeIndex = 0;


    @Override
    public void rotateRight() {
        currentShapeIndex = (currentShapeIndex + 1) % shapes.length;
    }

    @Override
    public void rotateLeft() {
        currentShapeIndex = (currentShapeIndex - 1 + shapes.length) % shapes.length;
    }

    @Override
    public int[][] getCurrentShape() {
        return shapes[currentShapeIndex];
    }
}
