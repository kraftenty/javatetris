package org.nl.javatetris.model.tetrominos;

public interface Tetromino {
    void rotateRight();
    void rotateLeft();
    int[][] getCurrentShape();
}