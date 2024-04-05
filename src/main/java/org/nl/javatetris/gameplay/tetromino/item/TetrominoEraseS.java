package org.nl.javatetris.gameplay.tetromino.item;

import org.nl.javatetris.gameplay.tetromino.AbstractTetromino;
import org.nl.javatetris.gameplay.tetromino.Tetromino;

import static org.nl.javatetris.config.constant.ModelConst.*;

public class TetrominoEraseS extends AbstractTetromino {

    public TetrominoEraseS() {
        setShapes();
    }

    @Override
    protected void setShapes() {
        shapes = new int[][][]{
                {
                        {0, S, E},
                        {S, S, 0},
                        {0, 0, 0}
                },
                {
                        {0, S, 0},
                        {0, S, S},
                        {0, 0, E}
                },
                {
                        {0, 0, 0},
                        {0, S, S},
                        {E, S, 0}
                },
                {
                        {E, 0, 0},
                        {S, S, 0},
                        {0, S, 0}
                },
                {
                        {0, E, S},
                        {S, S, 0},
                        {0, 0, 0}
                },
                {
                        {0, S, 0},
                        {0, S, E},
                        {0, 0, S}
                },
                {
                        {0, 0, 0},
                        {0, S, S},
                        {S, E, 0}
                },
                {
                        {S, 0, 0},
                        {E, S, 0},
                        {0, S, 0}
                },
                {
                        {0, S, S},
                        {S, E, 0},
                        {0, 0, 0}
                },
                {
                        {0, S, 0},
                        {0, E, S},
                        {0, 0, S}
                },
                {
                        {0, 0, 0},
                        {0, E, S},
                        {S, S, 0}
                },
                {
                        {S, 0, 0},
                        {S, E, 0},
                        {0, S, 0}
                },
                {
                        {0, S, S},
                        {E, S, 0},
                        {0, 0, 0}
                },
                {
                        {0, E, 0},
                        {0, S, S},
                        {0, 0, S}
                },
                {
                        {0, 0, 0},
                        {0, S, E},
                        {S, S, 0}
                },
                {
                        {S, 0, 0},
                        {S, S, 0},
                        {0, E, 0}
                },
        };
    }

    @Override
    public int getShapeNumber() {
        return ES;
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
        Tetromino tetromino = new TetrominoEraseS();
        tetromino.setShapeIndex(this.getShapeIndex());
        tetromino.rotateRight();
        return tetromino;
    }

}
