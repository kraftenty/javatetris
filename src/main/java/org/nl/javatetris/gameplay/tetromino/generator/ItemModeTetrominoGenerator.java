package org.nl.javatetris.gameplay.tetromino.generator;

import org.nl.javatetris.gameplay.tetromino.*;
import org.nl.javatetris.gameplay.tetromino.classic.*;
import org.nl.javatetris.gameplay.tetromino.item.*;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import static org.nl.javatetris.config.constant.ModelConst.EO;
import static org.nl.javatetris.config.constant.ModelConst.TETROMINO_TYPES;

//commit 된 부분 가져옴
public class ItemModeTetrominoGenerator implements TetrominoGenerator {

    private Queue<Tetromino> tetrominoQueue = new LinkedList<>();
    private int lastClassicTetrominoType = -1; // 마지막으로 생성된 테트로미노의 타입을 저장
    private Random random = new Random();


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
        switch (type) {
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
                throw new IllegalArgumentException("Unknown tetromino type: " + type);
        }
    }

    private Tetromino createItemTetrominoByRandom(int randomInt) {
        // TODO : 핵, 폭탄, 무게추, 수직제거 구현 후에 주석 해제할 것.
        if (randomInt < 1) {
            // 1% 확률로 핵
            return new TetrominoNuclear();
        } else if (randomInt <= 2) {
            // 27% 확률로 폭탄
            return new TetrominoBomb();
        } else if (randomInt <= 3) {
            // 24% 확률로 한줄 제거 블록
            return getRandomEraseTetromino();
        } else if (randomInt <= 99) {
            // 24% 확률로 무게 추, 아래 변경 필요
            return new TetrominoWeight();
        } else {
            // 24% 확률로 VerticalBomb 아이템 생성
            return new TetrominoVerticalBomb();
        }
        // TODO : 일단은 한줄제거만 가능하게 해 두었음.
        //return getRandomEraseTetromino();
    }

    private Tetromino getRandomEraseTetromino() {
        int randomShapeInt = random.nextInt(TETROMINO_TYPES);
        Tetromino tetromino;
        switch (randomShapeInt) {
            case 0:
                tetromino = new TetrominoEraseI();
                break;
            case 1:
                tetromino = new TetrominoEraseJ();
                break;
            case 2:
                tetromino = new TetrominoEraseL();
                break;
            case 3:
                tetromino = new TetrominoEraseO();
                break;
            case 4:
                tetromino = new TetrominoEraseS();
                break;
            case 5:
                tetromino = new TetrominoEraseT();
                break;
            case 6:
                tetromino = new TetrominoEraseZ();
                break;
            default:
                throw new IllegalArgumentException("Unknown tetromino type: " + randomShapeInt);
        }

        int randomLocationInt = random.nextInt(4); //L이 위치할 곳
        if (tetromino.getShapeNumber() == EO) {
            tetromino.setShapeIndex(randomLocationInt);
        } else {
            tetromino.setShapeIndex(randomLocationInt * 4);
        }

        return tetromino;
    }

}