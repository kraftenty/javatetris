package org.nl.javatetris.gameplay.tetromino;

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
        tetromino.setShapeIndex(this.getShapeIndex());
        tetromino.rotateRight();
        return tetromino;
    }

}
