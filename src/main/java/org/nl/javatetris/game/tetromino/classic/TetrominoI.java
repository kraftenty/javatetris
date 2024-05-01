package org.nl.javatetris.game.tetromino.classic;

import org.nl.javatetris.game.tetromino.AbstractTetromino;
import org.nl.javatetris.game.tetromino.Tetromino;

import static org.nl.javatetris.config.constant.ModelConst.I;

public class TetrominoI extends AbstractTetromino {

    public TetrominoI() {
        setShapes();
    }

    @Override
    protected void setShapes() {
        shapes = new int[][][]{
                {
                        {0, 0, 0, 0},
                        {I, I, I, I},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0}
                },
                {
                        {0, 0, I, 0},
                        {0, 0, I, 0},
                        {0, 0, I, 0},
                        {0, 0, I, 0}
                },
                {
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {I, I, I, I},
                        {0, 0, 0, 0}
                },
                {
                        {0, I, 0, 0},
                        {0, I, 0, 0},
                        {0, I, 0, 0},
                        {0, I, 0, 0}
                }
        };
    }

    @Override
    public int getShapeNumber() {
        return I;
    }

    @Override
    public Tetromino getRotatedTetromino() {
        Tetromino tetromino = new TetrominoI();
        tetromino.setShapeIndex(this.getShapeIndex());
        tetromino.rotateRight();
        return tetromino;
    }

}
