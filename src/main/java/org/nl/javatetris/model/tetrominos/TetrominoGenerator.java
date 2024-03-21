package org.nl.javatetris.model.tetrominos;

import java.util.Random;

public class TetrominoGenerator {

    private static final int TETROMINO_TYPES = 7;

    public static Tetromino getRandomTetromino() {
        Random random = new Random();
        int randomInt = random.nextInt(TETROMINO_TYPES);
        switch (randomInt) {
            case 0:
                return new TetrominoI();
            case 1:
                return new TetrominoJ();
            case 2:
                return new TetrominoL();
            case 3:
                return new TetrominoO();
            case 4:
                return new TetrominoS();
            case 5:
                return new TetrominoT();
            case 6:
                return new TetrominoZ();
            default:
                return null;
        }
    }
}
