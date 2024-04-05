package org.nl.javatetris.gameplay.tetromino;

import static org.nl.javatetris.config.constant.ModelConst.W;
public class ItemWeight extends AbstractTetromino{
    public ItemWeight() { setShapes();}
    @Override
    protected void setShapes() {
        shapes = new int[][][]{
                {
                        {0, W, W, 0},
                        {W, W, W, W}
                }
        };
    }

    @Override
    public int getShapeNumber() { return W; }

    @Override
    public Tetromino getRotatedTetromino() {
        Tetromino tetromino = new TetrominoI();
        tetromino.setShapeIndex(this.getShapeIndex());
        tetromino.rotateRight();
        return tetromino;
    }
}
