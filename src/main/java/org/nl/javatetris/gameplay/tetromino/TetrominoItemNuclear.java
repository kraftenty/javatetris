package org.nl.javatetris.gameplay.tetromino;

import static org.nl.javatetris.config.constant.ModelConst.*;
public class TetrominoItemNuclear extends AbstractTetromino {

    public static final int SHAPE_NUMBER = 0;
    public TetrominoItemNuclear() { setShapes();}

    @Override
    protected void setShapes() {
        shapes = new int [][][]{
                {
                        {N}
                }
        };
    }

    @Override
    public int getShapeNumber() { return N;}

    @Override
    public Tetromino getRotatedTetromino() {
        Tetromino tetromino = new TetrominoO();
        tetromino.setShapeIndex(this.getShapeIndex());
        tetromino.rotateRight();
        return tetromino;
    }
}