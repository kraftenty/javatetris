package org.nl.javatetris.gameplay.tetromino;

import org.nl.javatetris.config.constant.ModelConst;

public class TetrominoS extends AbstractTetromino {

    public TetrominoS() {
        setShapes();
    }

    @Override
    protected void setShapes() {
        shapes = new int[][][]{
                {
                        {0, 1, 1},
                        {1, 1, 0},
                        {0, 0, 0}
                },
                {
                        {0, 1, 0},
                        {0, 1, 1},
                        {0, 0, 1}
                },
                {
                        {0, 0, 0},
                        {0, 1, 1},
                        {1, 1, 0}
                },
                {
                        {1, 0, 0},
                        {1, 1, 0},
                        {0, 1, 0}
                }
        };
    }

    @Override
    public int getShapeNumber() {
        return ModelConst.S;
    }

    @Override
    public Tetromino getRotatedTetromino() {
        Tetromino tetromino = new TetrominoS();
        tetromino.setShapeIndex(this.getShapeIndex());
        tetromino.rotateRight();
        return tetromino;
    }

}
