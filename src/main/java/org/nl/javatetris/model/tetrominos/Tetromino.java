package org.nl.javatetris.model.tetrominos;

public interface Tetromino {
    void rotateRight();

    int[][] getShape();

    int getShapeIndex();  // 블록 회전 인덱스

    int getShapeWidth();

    int getShapeHeight();

    int getShapeNumber();   // 블록 종류 넘버

    Tetromino getRotatedTetromino();

    void setShapeIndex(int currentShapeIndex);
}
