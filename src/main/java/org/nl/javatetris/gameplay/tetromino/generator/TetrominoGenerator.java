package org.nl.javatetris.gameplay.tetromino.generator;

import org.nl.javatetris.gameplay.tetromino.Tetromino;

public interface TetrominoGenerator {

    public Tetromino peekNextTetromino();

    public Tetromino getNextTetromino();

}
