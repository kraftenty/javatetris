package org.nl.javatetris.gameplay.tetromino;

import org.nl.javatetris.config.constant.ModelConst;

public class TetrominoJ extends AbstractTetromino {

    public TetrominoJ() {
        setShapes();
    }

    @Override
    protected void setShapes() {
        shapes = new int[][][]{
                {
                        {1, 0, 0},
                        {1, 1, 1},
                        {0, 0, 0}
                },
                {
                        {0, 1, 1},
                        {0, 1, 0},
                        {0, 1, 0}
                },
                {
                        {0, 0, 0},
                        {1, 1, 1},
                        {0, 0, 1}
                },
                {
                        {0, 1, 0},
                        {0, 1, 0},
                        {1, 1, 0}
                }
        };
    }

    @Override
    public int getShapeNumber() {
        return ModelConst.J;
    }

    @Override
    public Tetromino getRotatedTetromino() {
        Tetromino tetromino = new TetrominoJ();
        tetromino.setShapeIndex(this.getShapeIndex());
        tetromino.rotateRight();
        return tetromino;
    }

}
