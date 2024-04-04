package org.nl.javatetris.gameplay.tetromino;

import static org.nl.javatetris.config.constant.ModelConst.V;
public class ItemVerticalBomb extends AbstractTetromino{

    public ItemVerticalBomb(){ setShapes();}

    @Override
    protected void setShapes() {
        shapes = new int [][][]{
            {
                {V}
            }
        };
    }

    @Override
    public int getShapeNumber() { return V; }

    @Override
    public Tetromino getRotatedTetromino() {
        return null;
    }
}
