package org.nl.javatetris.game.tetromino.generator;

import org.nl.javatetris.game.tetromino.Tetromino;

public interface TetrominoGenerator {

    public Tetromino peekNextTetromino();

    public Tetromino getNextTetromino(boolean isItItem);

}
