package org.nl.javatetris.gameplay.tetromino;

import org.nl.javatetris.config.constant.ModelConst;

public class TetrominoZ extends AbstractTetromino {

    public TetrominoZ() {
        setShapes();
    }

    @Override
    protected void setShapes() {
        shapes = new int[][][]{
                {
                        {1, 1, 0},
                        {0, 1, 1},
                        {0, 0, 0}
                },
                {
                        {0, 0, 1},
                        {0, 1, 1},
                        {0, 1, 0}
                },
                {
                        {0, 0, 0},
                        {1, 1, 0},
                        {0, 1, 1}
                },
                {
                        {0, 1, 0},
                        {1, 1, 0},
                        {1, 0, 0}
                }
        };
    }

    @Override
    public int getShapeNumber() {
        return ModelConst.Z;
    }

    @Override
    public Tetromino getRotatedTetromino() {
        Tetromino tetromino = new TetrominoZ();
        tetromino.setShapeIndex(this.getShapeIndex());
        tetromino.rotateRight();
        return tetromino;
    }

}
