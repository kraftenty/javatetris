package org.nl.javatetris.game.tetromino.generator;

import org.nl.javatetris.game.GameParam;
import org.nl.javatetris.game.tetromino.*;
import org.nl.javatetris.game.tetromino.classic.*;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import static org.nl.javatetris.config.constant.ModelConst.*;

public class ClassicModeTetrominoGenerator implements TetrominoGenerator {
    private GameParam gameParam;
    private Queue<Tetromino> tetrominoQueue = new LinkedList<>();
    private int lastTetrominoType = -1; // 마지막으로 생성된 테트로미노의 타입을 저장
    private Random random = new Random();

    public ClassicModeTetrominoGenerator(GameParam gameParam) {
        this.gameParam = gameParam;
        refillQueue();
    }

    @Override
    public Tetromino peekNextTetromino() {
        return tetrominoQueue.peek();
    }

    @Override
    public Tetromino getNextTetromino(boolean isItItem) {
        Tetromino nextTetromino = tetrominoQueue.poll();
        refillQueue();
        return nextTetromino;
    }

    public Queue<Tetromino> getTetrominoQueue() {
        return tetrominoQueue;
    }

    private void refillQueue() {
        while (tetrominoQueue.size() < 2) {
            int randomInt;
            do {
                randomInt = rouletteWheelSelection();
            } while (randomInt == lastTetrominoType); // 이전에 생성된 타입과 다를 때까지 반복

            lastTetrominoType = randomInt; // 새로운 테트로미노 타입을 마지막 타입으로 업데이트
            tetrominoQueue.add(createTetrominoByType(randomInt));
        }
    }

    private int rouletteWheelSelection() {
        //int difficulty = gameParam.getDifficulty();
        int totalWeight = TETROMINO_TYPES * 10; // 각 블록의 가중치를 모두 합침
        int selectedWeight = random.nextInt(totalWeight); // 랜덤으로 선택된 가중치
        int currentWeight = 0;

        for (int i = 0; i < TETROMINO_TYPES; i++) {
            int blockWeight = (i == 0) ? calculateIWeight() : 10; // I 블록의 가중치를 난이도별로 설정, 나머지 블록은 10
            currentWeight += blockWeight;
            if (selectedWeight < currentWeight) {
                return i;
            }
        }
        return rouletteWheelSelection(); //모든 테트로미노가 선택되지 않았을 경우 다시 수행
    }

    //난이도에 따른 가중치 계산
    private int calculateIWeight() {
        int difficulty = gameParam.getDifficulty();
        if (difficulty == 0) {
            // Difficulty가 0일 때, I 블록이 20% 더 등장하도록 가중치 설정
            return 12;
        } else if (difficulty == 2) {
            // Difficulty가 2일 때, I 블록이 20% 낮은 가중치로 등장하도록 가중치 설정
            return 8;
        } else {
            return 10; // 기본 가중치
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
}
