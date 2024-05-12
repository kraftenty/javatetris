package org.nl.javatetris.game.tetromino.generator;

import org.nl.javatetris.game.tetromino.*;
import org.nl.javatetris.game.tetromino.classic.*;
import org.nl.javatetris.game.tetromino.item.*;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import static org.nl.javatetris.config.constant.ModelConst.EO;
import static org.nl.javatetris.config.constant.ModelConst.TETROMINO_TYPES;

//commit 된 부분 가져옴
public class ItemModeTetrominoGenerator implements TetrominoGenerator {

    private final Queue<Tetromino> tetrominoQueue = new LinkedList<>();
    private int lastClassicTetrominoType = -1; // 마지막으로 생성된 테트로미노의 타입을 저장
    private final Random random = new Random();


    public ItemModeTetrominoGenerator() {
        refillQueue(false);
    }

    @Override
    public Tetromino peekNextTetromino() {
        return tetrominoQueue.peek();
    }

    @Override
    public Tetromino getNextTetromino(boolean shouldNextTetrominoBeItem) {
        Tetromino nextTetromino = tetrominoQueue.poll();
        refillQueue(shouldNextTetrominoBeItem);
        return nextTetromino;
    }

    public Queue<Tetromino> getTetrominoQueue() {
        return tetrominoQueue;
    }

    private void refillQueue(boolean shouldNextTetrominoBeItem) {
        while (tetrominoQueue.size() < 1) {
            if (shouldNextTetrominoBeItem) {
                // 아이템 테트로미노 생성
                tetrominoQueue.add(createItemTetrominoByRandom(random.nextInt(100)));
            } else {
                // 클래식 테트로미노 생성
                int randomInt;
                do {
                    randomInt = random.nextInt(TETROMINO_TYPES);
                } while (randomInt == lastClassicTetrominoType);
                lastClassicTetrominoType = randomInt;
                tetrominoQueue.add(createClassicTetrominoByType(randomInt));
            }
        }
    }

    private Tetromino createClassicTetrominoByType(int type) {
        return switch (type) {
            case 0 -> new TetrominoI();
            case 1 -> new TetrominoJ();
            case 2 -> new TetrominoL();
            case 3 -> new TetrominoO();
            case 4 -> new TetrominoS();
            case 5 -> new TetrominoT();
            case 6 -> new TetrominoZ();
            default -> throw new IllegalArgumentException("Unknown tetromino type: " + type);
        };
    }

    private Tetromino createItemTetrominoByRandom(int randomInt) {
        if (randomInt < 1) {
            // 1% 확률로 핵
            return new TetrominoNuclear();
        } else if (randomInt <= 28) {
            // 27% 확률로 폭탄
            return new TetrominoBomb();
        } else if (randomInt <= 51) {
            // 24% 확률로 한줄 제거 블록
            return getRandomEraseTetromino();
        } else if (randomInt <= 75) {
            // 24% 확률로 무게 추
            return new TetrominoWeight();
        } else {
            // 24% 확률로 VerticalBomb 아이템 생성
            return new TetrominoVerticalBomb();
        }
    }

    private Tetromino getRandomEraseTetromino() {
        int randomShapeInt = random.nextInt(TETROMINO_TYPES);
        Tetromino tetromino = switch (randomShapeInt) {
            case 0 -> new TetrominoEraseI();
            case 1 -> new TetrominoEraseJ();
            case 2 -> new TetrominoEraseL();
            case 3 -> new TetrominoEraseO();
            case 4 -> new TetrominoEraseS();
            case 5 -> new TetrominoEraseT();
            case 6 -> new TetrominoEraseZ();
            default -> throw new IllegalArgumentException("Unknown tetromino type: " + randomShapeInt);
        };

        int randomLocationInt = random.nextInt(4); //L이 위치할 곳
        if (tetromino.getShapeNumber() == EO) {
            tetromino.setShapeIndex(randomLocationInt);
        } else {
            tetromino.setShapeIndex(randomLocationInt * 4);
        }

        return tetromino;
    }

}