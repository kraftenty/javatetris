package org.nl.javatetris.gameplay.tetromino;

import static org.nl.javatetris.config.constant.ModelConst.*;
public class ItemNuclear extends AbstractTetromino {
    public ItemNuclear() { setShapes();}

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