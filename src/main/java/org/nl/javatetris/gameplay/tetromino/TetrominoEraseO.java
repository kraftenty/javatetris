package org.nl.javatetris.gameplay.tetromino;

import static org.nl.javatetris.config.constant.ModelConst.*;

public class TetrominoEraseO extends AbstractTetromino {

    public static final int SHAPE_NUMBER = O;

    public TetrominoEraseO() {
        setShapes();
    }

    @Override
    protected void setShapes() {
        shapes = new int[][][]{
            {
                {E, O},
                {O, O}
            },
            {
                {O, E},
                {O, O}
            },
            {
                {O, O},
                {O, E}
            },
            {
                {O, O},
                {E, O}
            }
        };
    }

    @Override
    public int getShapeNumber() {
        return EO;
    }

    @Override
    public void rotateRight() {
        int nextIndex = (this.getShapeIndex() + 1) % 4;
        this.setShapeIndex(nextIndex);
    }

    @Override
    public Tetromino getRotatedTetromino() {
        Tetromino tetromino = new TetrominoEraseO();
        tetromino.setShapeIndex(this.getShapeIndex());
        tetromino.rotateRight();
        return tetromino;
    }

}
