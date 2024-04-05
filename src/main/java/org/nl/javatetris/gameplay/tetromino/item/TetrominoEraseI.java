package org.nl.javatetris.gameplay.tetromino.item;

import org.nl.javatetris.gameplay.tetromino.AbstractTetromino;
import org.nl.javatetris.gameplay.tetromino.Tetromino;

import static org.nl.javatetris.config.constant.ModelConst.*;

public class TetrominoEraseI extends AbstractTetromino {

    public TetrominoEraseI() {
        setShapes();
    }

    @Override
    protected void setShapes() {
        shapes = new int[][][]{
                {
                        {0, 0, 0, 0},
                        {E, I, I, I},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0}
                },
                {
                        {0, 0, E, 0},
                        {0, 0, I, 0},
                        {0, 0, I, 0},
                        {0, 0, I, 0}
                },
                {
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {I, I, I, E},
                        {0, 0, 0, 0}
                },
                {
                        {0, I, 0, 0},
                        {0, I, 0, 0},
                        {0, I, 0, 0},
                        {0, E, 0, 0}
                },
                {
                        {0, 0, 0, 0},
                        {I, E, I, I},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0}
                },
                {
                        {0, 0, I, 0},
                        {0, 0, E, 0},
                        {0, 0, I, 0},
                        {0, 0, I, 0}
                },
                {
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {I, I, E, I},
                        {0, 0, 0, 0}
                },
                {
                        {0, I, 0, 0},
                        {0, I, 0, 0},
                        {0, E, 0, 0},
                        {0, I, 0, 0}
                },
                {
                        {0, 0, 0, 0},
                        {I, I, E, I},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0}
                },
                {
                        {0, 0, I, 0},
                        {0, 0, I, 0},
                        {0, 0, E, 0},
                        {0, 0, I, 0}
                },
                {
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {I, E, I, I},
                        {0, 0, 0, 0}
                },
                {
                        {0, I, 0, 0},
                        {0, E, 0, 0},
                        {0, I, 0, 0},
                        {0, I, 0, 0}
                },
                {
                        {0, 0, 0, 0},
                        {I, I, I, E},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0}
                },
                {
                        {0, 0, I, 0},
                        {0, 0, I, 0},
                        {0, 0, I, 0},
                        {0, 0, E, 0}
                },
                {
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {E, I, I, I},
                        {0, 0, 0, 0}
                },
                {
                        {0, E, 0, 0},
                        {0, I, 0, 0},
                        {0, I, 0, 0},
                        {0, I, 0, 0}
                }
        };
    }

    @Override
    public int getShapeNumber() {
        return EI;
    }

    @Override
    public void rotateRight() {
        int nextIndex = this.getShapeIndex() + 1;
        if (nextIndex == (this.getShapeIndex() / 4) * 4 + 4)
            nextIndex = (this.getShapeIndex() / 4) * 4;
        this.setShapeIndex(nextIndex);
    }

    @Override
    public Tetromino getRotatedTetromino() {
        Tetromino tetromino = new TetrominoEraseI();
        tetromino.setShapeIndex(this.getShapeIndex());
        tetromino.rotateRight();
        return tetromino;
    }

}
