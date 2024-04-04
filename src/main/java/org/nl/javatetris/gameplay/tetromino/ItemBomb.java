package org.nl.javatetris.gameplay.tetromino;

import static org.nl.javatetris.config.constant.ModelConst.B;
public class ItemBomb extends AbstractTetromino{
    public ItemBomb() { setShapes(); }

    @Override
    protected void setShapes() {
        shapes = new int [][][]{
                {
                        {B}
                }
        };
    }

    @Override
    public int getShapeNumber() {
        return B;
    }

    @Override
    public Tetromino getRotatedTetromino() {
        Tetromino tetromino = new TetrominoO();
        tetromino.setShapeIndex(this.getShapeIndex());
        tetromino.rotateRight();
        return tetromino;
    }
}
