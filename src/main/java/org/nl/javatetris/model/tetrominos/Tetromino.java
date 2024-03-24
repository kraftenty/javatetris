package org.nl.javatetris.model.tetrominos;

public interface Tetromino {
    void rotateRight();

    int[][] getShape();

    int getShapeIndex();

    int getShapeWidth();

    int getShapeHeight();

    int getShapeNumber();

    Tetromino getRotatedTetromino();

    void setShapeIndex(int currentShapeIndex);
}
