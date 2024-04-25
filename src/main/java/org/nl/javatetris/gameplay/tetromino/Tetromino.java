package org.nl.javatetris.gameplay.tetromino;

public interface Tetromino {
    void rotateRight();

    int[][] getShape();

    int getShapeIndex();

    int getShapeWidth();

    int getShapeHeight();

    int getShapeNumber();

    int getTetrominoBlock(int y, int x);

    Tetromino getRotatedTetromino();

    void setShapeIndex(int currentShapeIndex);

    boolean getReservedFlag();

    void setReservedFlag(boolean reservedFlag);
}
