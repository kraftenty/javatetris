package org.nl.javatetris.model.tetrominos;

import org.nl.javatetris.model.ModelConst;

public class TetrominoI extends AbstractTetromino {

    public TetrominoI() {
        setShapes();
    }

    @Override
    protected void setShapes() {
        shapes = new int[][][]{
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
    }

    @Override
    public int getShapeNumber() {
        return ModelConst.I;
    }

    @Override
    public Tetromino getRotatedTetromino() {
        Tetromino tetromino = new TetrominoI();
        tetromino.setShapeIndex(this.getShapeIndex());
        tetromino.rotateRight();
        return tetromino;
    }

}
