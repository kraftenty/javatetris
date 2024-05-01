package org.nl.javatetris.game.tetromino.classic;

import org.nl.javatetris.game.tetromino.AbstractTetromino;
import org.nl.javatetris.game.tetromino.Tetromino;

import static org.nl.javatetris.config.constant.ModelConst.O;

public class TetrominoO extends AbstractTetromino {

    public static final int SHAPE_NUMBER = O;

    public TetrominoO() {
        setShapes();
    }

    @Override
    protected void setShapes() {
        shapes = new int[][][]{
            {
                {O, O},
                {O, O}
            }
        };
    }

    @Override
    public int getShapeNumber() {
        return O;
    }

    @Override
    public Tetromino getRotatedTetromino() {
        Tetromino tetromino = new TetrominoO();
        return tetromino;
    }

}
