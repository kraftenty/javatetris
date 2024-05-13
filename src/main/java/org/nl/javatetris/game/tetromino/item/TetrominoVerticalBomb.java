package org.nl.javatetris.game.tetromino.item;

import org.nl.javatetris.game.tetromino.AbstractTetromino;
import org.nl.javatetris.game.tetromino.Tetromino;

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
