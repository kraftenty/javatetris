package org.nl.javatetris.model.tetrominos;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import static org.nl.javatetris.model.ModelConst.*;

public class TetrominoGenerator {

    private Queue<Tetromino> tetrominoQueue = new LinkedList<>();
    private int lastTetrominoType = -1; // 마지막으로 생성된 테트로미노의 타입을 저장
    private Random random = new Random();

    public TetrominoGenerator() {
        refillQueue();
    }

    private void refillQueue() {
        while (tetrominoQueue.size() < 2) {
            int randomInt;
            do {
                randomInt = random.nextInt(TETROMINO_TYPES);
            } while (randomInt == lastTetrominoType); // 이전에 생성된 타입과 다를 때까지 반복

            lastTetrominoType = randomInt; // 새로운 테트로미노 타입을 마지막 타입으로 업데이트
            tetrominoQueue.add(createTetrominoByType(randomInt));
        }
    }

    private Tetromino createTetrominoByType(int type) {
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

    public Tetromino peekNextTetromino() {
        return tetrominoQueue.peek();
    }

    public Tetromino getNextTetromino() {
        Tetromino nextTetromino = tetrominoQueue.poll();
        refillQueue();
        return nextTetromino;
    }
}
