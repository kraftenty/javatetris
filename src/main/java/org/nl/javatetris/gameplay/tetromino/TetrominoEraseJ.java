package org.nl.javatetris.gameplay.tetromino;

import static org.nl.javatetris.config.constant.ModelConst.*;

public class TetrominoEraseJ extends AbstractTetromino {

    public TetrominoEraseJ() {
        setShapes();
    }

    @Override
    protected void setShapes() {
        shapes = new int[][][]{
                {
                        {E, 0, 0},
                        {J, J, J},
                        {0, 0, 0}
                },
                {
                        {0, J, E},
                        {0, J, 0},
                        {0, J, 0}
                },
                {
                        {0, 0, 0},
                        {J, J, J},
                        {0, 0, E}
                },
                {
                        {0, J, 0},
                        {0, J, 0},
                        {E, J, 0}
                },
                {
                        {J, 0, 0},
                        {E, J, J},
                        {0, 0, 0}
                },
                {
                        {0, E, J},
                        {0, J, 0},
                        {0, J, 0}
                },
                {
                        {0, 0, 0},
                        {J, J, E},
                        {0, 0, J}
                },
                {
                        {0, J, 0},
                        {0, J, 0},
                        {J, E, 0}
                },
                {
                        {J, 0, 0},
                        {J, E, J},
                        {0, 0, 0}
                },
                {
                        {0, J, J},
                        {0, E, 0},
                        {0, J, 0}
                },
                {
                        {0, 0, 0},
                        {J, E, J},
                        {0, 0, J}
                },
                {
                        {0, J, 0},
                        {0, E, 0},
                        {J, J, 0}
                },
                {
                        {J, 0, 0},
                        {J, J, E},
                        {0, 0, 0}
                },
                {
                        {0, J, J},
                        {0, J, 0},
                        {0, E, 0}
                },
                {
                        {0, 0, 0},
                        {E, J, J},
                        {0, 0, J}
                },
                {
                        {0, E, 0},
                        {0, J, 0},
                        {J, J, 0}
                }
        };
    }

    @Override
    public int getShapeNumber() {
        return EJ;
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
        Tetromino tetromino = new TetrominoEraseJ();
        tetromino.setShapeIndex(this.getShapeIndex());
        tetromino.rotateRight();
        return tetromino;
    }

}
