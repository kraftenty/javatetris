package org.nl.javatetris.gameplay.tetromino.generator;

import org.nl.javatetris.gameplay.tetromino.*;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import static org.nl.javatetris.config.constant.ModelConst.EO;
import static org.nl.javatetris.config.constant.ModelConst.TETROMINO_TYPES;

// TODO : 아이템모드 테트로미노 생성기
public class ItemModeTetrominoGenerator implements TetrominoGenerator {

    private Queue<Tetromino> tetrominoQueue = new LinkedList<>();
    private int lastTetrominoType = -1; // 마지막으로 생성된 테트로미노의 타입을 저장
    private Random random = new Random();


    public ItemModeTetrominoGenerator() {
        refillQueue();
    }

    @Override
    public Tetromino peekNextTetromino() {
        return tetrominoQueue.peek();
    }

    @Override
    public Tetromino getNextTetromino(boolean isItItem) {
        Tetromino nextTetromino = tetrominoQueue.poll();
        if (isItItem) addItem();
        refillQueue();
        return nextTetromino;
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


    private void addItem() {
        int randomInt = random.nextInt(100);
        if (randomInt < 1) { // 1% 확률로 핵, 아래 변경 필요
            tetrominoQueue.add(createItemNuclear());
        }
        else if (randomInt < 97) { //27% 확률로 폭탄, 아래 변경 필요
            tetrominoQueue.add(createItemBomb());
        }
        else if (randomInt < 98) { //24% 확률로 한줄 제거 블록
            tetrominoQueue.add(createTetrominoWithErase());
        }
        else if (randomInt < 99) { //24% 확률로 무게 추, 아래 변경 필요
            tetrominoQueue.add(createTetrominoWithErase());
        }
        else { //24% 확률로 기타 아이템
            tetrominoQueue.add(createTetrominoWithErase());
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

    private Tetromino createTetrominoWithErase() {
        int randomInt = random.nextInt(TETROMINO_TYPES);
        Tetromino tetromino;
        switch (randomInt) {
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
                throw new IllegalArgumentException("Unknown tetromino type: " + randomInt);
        }

        randomInt = random.nextInt(4); //L이 위치할 곳

        if (tetromino.getShapeNumber() == EO)
            tetromino.setShapeIndex(randomInt);
        else
            tetromino.setShapeIndex(randomInt * 4);

        return tetromino;
    }
    private Tetromino createItemNuclear() {
        Tetromino tetromino;
        tetromino = new ItemNuclear();
        return tetromino;
    }

    private Tetromino createItemBomb(){
        Tetromino tetromino;
        tetromino = new ItemBomb();
        return tetromino;
    }
}