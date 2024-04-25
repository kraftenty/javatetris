package org.nl.javatetris.gameplay.tetromino.item;

import org.nl.javatetris.gameplay.tetromino.AbstractTetromino;
import org.nl.javatetris.gameplay.tetromino.Tetromino;

import static org.nl.javatetris.config.constant.ModelConst.V;
public class TetrominoVerticalBomb extends AbstractTetromino {

    public TetrominoVerticalBomb(){ setShapes();}

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
        return new TetrominoVerticalBomb();
    }
}
