package org.nl.javatetris.game.play;

import org.nl.javatetris.config.constant.ModelConst;
import org.nl.javatetris.game.tetromino.Tetromino;
import org.nl.javatetris.game.tetromino.generator.TetrominoGenerator;

import java.util.ArrayList;
import java.util.List;

import static org.nl.javatetris.config.constant.ModelConst.*;


public class Board {

    // 보드 자체 관련 필드
    private final int[][] board;

    // 테트로미노 관련 필드
    private final TetrominoGenerator tetrominoGenerator;
    private Tetromino currentTetromino;
    private int tetrominoY;
    private int tetrominoX;
    private int clearedLineCount;
    private final Runnable onClearCompletedLines;

    // 줄 넘김을 위한 필드
    private final List<Integer> completedLineNumbers = new ArrayList<>();

    // 공격할 라인을 담는 버퍼
    private List<LineDTO> attackLineBuffer = new ArrayList<>();
    // 공격받은 라인을 담는 버퍼
    private List<LineDTO> damagedLineBuffer = new ArrayList<>();


    // 생성자
    public Board(Runnable onClearCompletedLines, TetrominoGenerator tetrominoGenerator) {
        this.tetrominoGenerator = tetrominoGenerator;
        this.onClearCompletedLines = onClearCompletedLines;
        board = new int[Y_MAX][X_MAX];
        clearedLineCount = 0;
        initialize();
    }

    /**
     * 어택 및 데미지 처리 관련 메서드 TODO
     */
    public List<LineDTO> getDamagedLineBuffer() {
        return damagedLineBuffer;
    }

    // 외부에서 데미지 라인을 전달받아 버퍼에 추가하는 메서드
    public void receiveDamage(List<LineDTO> damagedLines) {
        // 2줄 이하면 damagedLineBuffer 에 추가하지 않음
        if (damagedLines.size() < 2) {
            return;
        }
        if (damagedLineBuffer.size()>=10){
            return;
        }
        int spaceAvailable = 10 - damagedLineBuffer.size();
        if (damagedLines.size() > spaceAvailable) {
            // 새로운 줄이 현재 빈 공간을 초과하는 경우 초과된 줄을 자름
            damagedLines = damagedLines.subList(0, spaceAvailable);
        }
        damagedLineBuffer.addAll(damagedLines);  // damagedLines 를 버퍼에 추가
    }

    // 데미지 입은 라인을 보드에 추가하는 메서드
    public void addDamagedLinesToBoard() {
        int linesToElivate = damagedLineBuffer.size();
        if (linesToElivate > 0) {
            // 현재 쌓여있는 블럭들을 damagedLineBuffer의 길이만큼 위로 올림
            for (int y = 1; y < Y_MAX - linesToElivate - 1; y++) {
                for (int x = 1; x < X_MAX - 1; x++) {
                    board[y][x] = board[y + linesToElivate][x];
                }
            }

            // 게임 하단에 데미지 입은 라인을 추가
            for (int y = 0; y < linesToElivate; y++) {
                List<Integer> newLine = damagedLineBuffer.get(y).getLine();
                int targetY = Y_MAX - linesToElivate - 1 + y; // 새 줄이 추가될 y 위치
                for (int x = 1; x < X_MAX - 1; x++) {
                    if (x <= newLine.size()) {
                        board[targetY][x] = newLine.get(x - 1) == 1 ? DAMAGED_BLOCK : EMPTY; // x - 1은 damagedLineBuffer의 인덱스 조정
                    }
                }
            }

            damagedLineBuffer.clear(); // 처리 후 버퍼 비우기
        }
    }

    // attackLineBuffer 리스트를 반환하고, attackLineBuffer 리스트를 초기화하는 메서드
    public List<LineDTO> releaseAttackLineBuffer() {
        List<LineDTO> temp = new ArrayList<>(attackLineBuffer);
        attackLineBuffer.clear();
        return temp;
    }

    // 공격할 라인을 받아서 버퍼에 추가하는 메서드
    private void putCompletedLineIntoAttackLineBuffer(int lineNumber) {
        List<Integer> line = new ArrayList<>();
        for (int x = 1; x < X_MAX - 1; x++) {
            // 현재 테트로미노의 위치와 매치되는지 확인
            boolean isPartOfTetromino = false;
            for (int i = 0; i < currentTetromino.getShapeHeight(); i++) {
                for (int j = 0; j < currentTetromino.getShapeWidth(); j++) {
                    // 테트로미노의 각 블록이 라인과 일치하는지, 그리고 테트로미노 모양에서 해당 위치가 블록인지 확인
                    if (currentTetromino.getTetrominoBlock(i, j) != 0 &&
                            tetrominoY + i == lineNumber &&
                            tetrominoX + j == x) {
                        isPartOfTetromino = true;
                        break;
                    }
                }
                if (isPartOfTetromino) {
                    break;
                }
            }
            // 라인을 완성시킨 원인이 되는 현재 테트로미노는 0으로 add, 아닌 경우 1로 add
            line.add(isPartOfTetromino ? 0 : 1);
        }
        attackLineBuffer.add(new LineDTO(line));
    }


    /**
     * 유틸 메서드
     */

    // 보드 초기화 메서드
    private void initialize() {
        for (int y = 0; y < Y_MAX; y++) {
            for (int x = 0; x < X_MAX; x++) {
                if (isBorder(y, x)) {
                    board[y][x] = BORDER; // 테두리 설정
                } else {
                    board[y][x] = EMPTY; // 내부 칸은 빈 칸으로
                }
            }
        }
    }

    // 보드 위 특정 위치의 점의 값을 반환하는 메서드
    public int getValueAt(int y, int x) {
        return board[y][x];
    }

    //현재 테트로미노 반환
    public Tetromino getCurrentTetromino() {
        return currentTetromino;
    }

    public int[] getYX() {
        int[] yx = new int[2];
        yx[0] = tetrominoY;
        yx[1] = tetrominoX;
        return yx;
    }

    // completedLines 리스트를 반환하고, completedLines 리스트를 초기화하는 메서드
    public List<Integer> releaseCompletedLines() {
        List<Integer> temp = new ArrayList<>(completedLineNumbers);
        completedLineNumbers.clear();
        return temp;
    }

    //     게임 보드에서 완성된 줄을 제거하고, 줄들을 아래로 이동시키는 메서드
    private void clearCompletedLines() {
        attackLineBuffer.clear(); // TODO
        for (int y = 1; y < Y_MAX - 1; y++) {
            if (isLineComplete(y)) {
                completedLineNumbers.add(y); // 완성된 줄을 completedLines 리스트에 추가
                putCompletedLineIntoAttackLineBuffer(y);
                removeLine(y); // 한줄 지우고
                shiftLinesDown(y); // 하강
                clearedLineCount++;
                onClearCompletedLines.run();
            }
        }
    }

    private void clearItemLine() {
        attackLineBuffer.clear(); // TODO
        int y = 0;
        for (int i = 0; i < currentTetromino.getShapeHeight(); i++) {
            for (int j = 0; j < currentTetromino.getShapeWidth(); j++) {
                if (currentTetromino.getTetrominoBlock(i, j) == E) {
                    y = tetrominoY + i;
                    break;
                }
            }
        }
        completedLineNumbers.add(y); // 완성된 줄을 completedLines 리스트에 추가
        putCompletedLineIntoAttackLineBuffer(y);
        removeLine(y); // 한줄 지우고
        shiftLinesDown(y); // 하강
        clearedLineCount++;
        onClearCompletedLines.run();
    }

    // 아이템모드에서 Nuclear 아이템으로 모두 지우는 메서드
    private void clearAllLine() {
        for (int i = 1; i < Y_MAX - 1; i++) {
            removeLine(i);
        }
    }

    // 아이템모드에서 Bomb 아이템으로 3X3의 영역을 지우는 메서드
    private void clearBombArea() {
        int centerX = tetrominoX;
        int centerY = tetrominoY;

        for (int y = centerY - 1; y <= centerY + 1; y++) {
            for (int x = centerX - 1; x <= centerX + 1; x++) {
                if (isBorder(y, x)) {
                    continue;
                }
                board[y][x] = EMPTY;
            }
        }
    }

    private void clearVerticalLine() {
        int x = 0, y = 0;
        currentTetromino.getTetrominoBlock(y, x);
        x = tetrominoX;
        removeVerticalLine(x);
    }

    // 특정 줄을 제거하는 메서드
    private void removeLine(int lineIndex) {
        for (int x = 1; x < X_MAX - 1; x++) {
            board[lineIndex][x] = EMPTY; // 해당 줄의 모든 칸을 EMPTY 로 설정하여 줄을 제거
        }
    }

    // 세로 줄을 제거하는 메서드
    private void removeVerticalLine(int lineIndex) {
        for (int y = 1; y < Y_MAX - 1; y++) {
            board[y][lineIndex] = EMPTY;
        }
    }

    // 제거된 줄 위의 모든 줄을 아래로 한 칸씩 이동시키는 메서드
    private void shiftLinesDown(int fromLineIndex) {
        // 실제 게임 내용이 있는 줄만 이동
        for (int y = fromLineIndex; y > 1; y--) {
            for (int x = 1; x < X_MAX - 1; x++) {
                board[y][x] = board[y - 1][x];
            }
        }

        // 가장 위의 줄을 EMPTY 로 설정
        for (int x = 1; x < X_MAX - 1; x++) {
            board[1][x] = EMPTY;
        }
    }

    // 테트로미노를 보드 상단에 스폰시키는 메서드
    public boolean spawnTetromino(boolean shouldNextTetrominoBeItem) {
        // 상대방이 공격한 줄을 맨 밑에 넣는 코드 추가
        addDamagedLinesToBoard();
        // 테트로미노를 생성한다.
        currentTetromino = tetrominoGenerator.getNextTetromino(shouldNextTetrominoBeItem);
        // 생성한 테트로미노를 화면 상단에 위치시킨다.
        tetrominoX = X_MAX / 2 - 1;
        tetrominoY = 1;
        // 테트로미노를 스폰시킬 수 있는지 검사하고, 없으면 게임 오버
        if (!canSpawn()) {
            return false;
        } else {
            placeTetrominoOnBoard();
            return true;
        }
    }

    // 테트로미노를 보드에서 지우는 메서드
    private void clearTetrominoFromBoard() {
        for (int y = 0; y < currentTetromino.getShapeHeight(); y++) {
            for (int x = 0; x < currentTetromino.getShapeWidth(); x++) {
                if (currentTetromino.getShape()[y][x] != 0) {
                    board[tetrominoY + y][tetrominoX + x] = EMPTY;
                }
            }
        }
    }

    // 테트로미노를 보드에 배치하는 메서드
    private void placeTetrominoOnBoard() {
        for (int y = 0; y < currentTetromino.getShapeHeight(); y++) {
            for (int x = 0; x < currentTetromino.getShapeWidth(); x++) {
                if (currentTetromino.getShape()[y][x] != 0) {
                    board[tetrominoY + y][tetrominoX + x] = currentTetromino.getTetrominoBlock(y, x);
                }
            }
        }
    }


    /**
     * 무빙 메서드
     */

    // 테트로미노를 보드 내에서 왼쪽으로 이동시키는 메서드
    public void moveTetrominoLeft() {
        // 무게추 블록이면서 무게추가 이미 어떤 블럭을 삭제한 경우
        if (currentTetromino.getShapeNumber() == W && currentTetromino.getReservedFlag())
            return;

        clearTetrominoFromBoard();
        if (canMove(tetrominoY, tetrominoX - 1)) {
            tetrominoX--;
        }
        placeTetrominoOnBoard();
    }

    // 테트로미노를 보드 내에서 오른쪽으로 이동시키는 메서드
    public void moveTetrominoRight() {
        // 무게추 블록이면서 무게추가 이미 어떤 블럭을 삭제한 경우
        if (currentTetromino.getShapeNumber() == W && currentTetromino.getReservedFlag())
            return;

        clearTetrominoFromBoard();
        if (canMove(tetrominoY, tetrominoX + 1)) {
            tetrominoX++;
        }
        placeTetrominoOnBoard();
    }

    // 테트로미노를 보드 내에서 아래로 이동시키는 메서드
    public boolean moveTetrominoDown() {
        clearTetrominoFromBoard();
        if (canMove(tetrominoY + 1, tetrominoX)) {
            tetrominoY++;
            placeTetrominoOnBoard();
            return true;

        } else {
            placeTetrominoOnBoard();
            boolean shouldNextTetrominoBeItem = false;
            if (currentTetromino.getShapeNumber() >= EI && currentTetromino.getShapeNumber() <= EZ) {
                clearItemLine();
            }
            clearCompletedLines(); // TetrominoEraser 실행

            if (currentTetromino.getShapeNumber() == ModelConst.N) {
                clearAllLine(); // TetrominoNuclear 실행
            }
            if (currentTetromino.getShapeNumber() == ModelConst.B) {
                clearBombArea(); // TetrominoBomb 실행
            }
            if (currentTetromino.getShapeNumber() == ModelConst.V) {
                clearVerticalLine(); // TetrominoVerticalBomb 실행
            }
            if (clearedLineCount >= 10) {
                shouldNextTetrominoBeItem = true;
                clearedLineCount -= 10;
            }

            return spawnTetromino(shouldNextTetrominoBeItem);
        }
    }

    // 테트로미노를 보드 내에서 회전시키는 메서드. 벽 때문에 회전할 수 없는 경우, 왼쪽 또는 오른쪽으로 이동시킨 후 회전
    public void rotateTetromino() {
        // 회전 후의 테트로미노 모양을 계산합니다.
        // 이 예시에서는 현재 테트로미노의 회전 메서드를 호출하여 "가상의" 회전을 수행하고,
        // 그 결과를 기반으로 회전 가능 여부를 판단합니다.


        clearTetrominoFromBoard();
        if (!canRotate()) {
            placeTetrominoOnBoard();
            return;
        }
        int[] tetYX = moveForRotate();
        tetrominoY = tetYX[0];
        tetrominoX = tetYX[1];
        currentTetromino.rotateRight();
        placeTetrominoOnBoard();
    }

    // 스페이스바를 눌러 테트로미노를 가장 아래로 내리는 메서드
    public int dropTetromino() {
        // 맨 밑인 경우를 검사
        clearTetrominoFromBoard();
        if (!canMove(tetrominoY + 1, tetrominoX)) {
            placeTetrominoOnBoard();
            return -1; // 맨 밑에서 drop 하는 경우, -1 을 리턴
        } else {
            placeTetrominoOnBoard();
        }

        if (currentTetromino.getShapeNumber() == W) {
            // 무게추 아이템인 경우
            clearTetrominoFromBoard();
            tetrominoY = Y_MAX - 3;
            for (int y = 1; y < Y_MAX - 1; y++) {
                for (int x = tetrominoX; x < tetrominoX + 4; x++) {
                    board[y][x] = EMPTY;
                }
            }
            placeTetrominoOnBoard();
            currentTetromino.setReservedFlag(true);
            return 20;
        } else {
            // 일반 아이템인 경우
            int offset = 0;
            clearTetrominoFromBoard();
            while (canMove(tetrominoY + offset + 1, tetrominoX)) {
                offset++;
            }
            tetrominoY += offset;
            placeTetrominoOnBoard();
            moveTetrominoDown();
            return offset;
        }
    }


    /**
     * 검사 메서드
     */

    // 특정 좌표가 보드의 경계인지 검사하는 메서드
    private boolean isBorder(int y, int x) {
        return y == 0 || y == (Y_MAX - 1) || x == 0 || x == (X_MAX - 1);
    }

    // 보드 내에서 특정 줄이 완성되었는지 검사하는 메서드
    private boolean isLineComplete(int y) {
        for (int x = 1; x < X_MAX - 1; x++) {
            if (board[y][x] == EMPTY) {
                return false; // 해당 줄에 빈 칸이 있으면, 줄이 완성되지 않은 것으로 판단
            }
        }
        return true; // 줄이 완성됨
    }

    // 테트로미노가 보드 내에서 생성될 수 있는지 검사하는 메서드
    private boolean canSpawn() {
        for (int y = 0; y < currentTetromino.getShape().length; y++) {
            for (int x = 0; x < currentTetromino.getShape()[y].length; x++) {
                if (currentTetromino.getShape()[y][x] != 0) {
                    if (board[tetrominoY + y][tetrominoX + x] != EMPTY) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // 테트로미노가 보드 내에서 이동할 수 있는지 검사하는 메서드
    private boolean canMove(int newY, int newX) {
        for (int y = 0; y < currentTetromino.getShapeHeight(); y++) {
            for (int x = 0; x < currentTetromino.getShapeWidth(); x++) {
                if (currentTetromino.getShape()[y][x] != 0) {
                    int boardY = newY + y;
                    int boardX = newX + x;

                    // 경계 조건 검사
                    if (boardX < 0 || boardX >= X_MAX || boardY < 0 || boardY >= Y_MAX) {
                        return false;
                    }

                    if (currentTetromino.getShapeNumber() == W) {
                        // 무게추
                        if (boardY == Y_MAX - 1 || boardX == 0 || boardX == X_MAX - 1) {
                            return false;
                        }
                        if (board[boardY][boardX] != EMPTY) {
                            currentTetromino.setReservedFlag(true);
                        }
                    } else {
                        // 일반 아이템
                        if (board[boardY][boardX] != EMPTY) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    // 회전할 때 걸리는 부분을 찾고, 이동해야할 방향과 거리 리턴
    public int getRotatedMove(int tetrominoNum, int rotationIdx) { //1 2 3 4 : 상 우 하 좌 1칸, 5 6 7 8 2칸
        if (tetrominoNum == O) return 0;
        if (tetrominoNum == I) {
            int[] dy = new int[]{2, 3, 0, 2, 2, 2, 1, 0, 3, 1, 1, 1};  // I가 회전할 때 걸리는 부분
            int[] dx = new int[]{2, 2, 2, 1, 0, 3, 1, 1, 1, 2, 3, 0};

            if (board[tetrominoY + dy[rotationIdx * 3]][tetrominoX + dx[rotationIdx * 3]] != EMPTY) //1자가 두칸 밀리는 경우
                return rotationIdx + 5;
            else if (board[tetrominoY + dy[rotationIdx * 3 + 1]][tetrominoX + dx[rotationIdx * 3 + 1]] != EMPTY) //1자가 한칸 밀리는 경우
                return rotationIdx + 1;
            else if (board[tetrominoY + dy[rotationIdx * 3 + 2]][tetrominoX + dx[rotationIdx * 3 + 2]] != EMPTY) //1자가 반대편(빈칸이 한칸)에서 밀리는 경우
                return (rotationIdx + 2) % 4 + 1;
            else
                return 0;
        }
        // 그 외 블록 회전
        Tetromino rotatedTetromino = currentTetromino.getRotatedTetromino();
        int[][] shape = rotatedTetromino.getShape();

        for (int y = 0; y < 3; y++) {  //걸리는 부분 탐색
            for (int x = 0; x < 3; x++) {
                if (shape[y][x] == 0) continue;
                int boardY = tetrominoY + y;
                int boardX = tetrominoX + x;

                if (board[boardY][boardX] == EMPTY) continue;
                if (rotationIdx % 2 == 0 && y != 1) {
                    return 3 - y;
                } else if (rotationIdx % 2 == 1 && x != 1) {
                    return x + 2;
                }
            }
        }
        return 0;
    }

    //회전할 수 있도록 이동
    public int[] moveForRotate() {
        int tetrominoNum = currentTetromino.getShapeNumber();
        int rotationIdx = currentTetromino.getShapeIndex();

        if (tetrominoNum >= EI && tetrominoNum <= EZ) {  //지우는 블럭을 일반 블록 취급
            tetrominoNum -= 10;
            rotationIdx = (rotationIdx) % 4;
        }

        int[] yx = {tetrominoY, tetrominoX};
        int move = getRotatedMove(tetrominoNum, rotationIdx);

        int[] dy = new int[]{0, -1, 0, 1, 0, -2, 0, 2, 0};
        int[] dx = new int[]{0, 0, 1, 0, -1, 0, 2, 0, -2};

        yx[0] += dy[move];
        yx[1] += dx[move];
        return yx;
    }

    // 테트로미노가 보드 내에서 회전할 수 있는지 검사하는 메서드
    public boolean canRotate() {
        if (currentTetromino.getShapeNumber() == O || currentTetromino.getShapeNumber() == EO) return true;

        if (currentTetromino.getShapeNumber() >= B && currentTetromino.getShapeNumber()<=W)
            return false;

        Tetromino rotatedTetromino = currentTetromino.getRotatedTetromino();
        int[][] shape = rotatedTetromino.getShape();

        int[] tempYX = moveForRotate(); //회전할 수 있도록 이동시킨 후 위치

        for (int y = 0; y < shape.length; y++) {
            for (int x = 0; x < shape[y].length; x++) {
                if (shape[y][x] != 0) {
                    int boardY = tempYX[0] + y;
                    int boardX = tempYX[1] + x;

                    // 이미 채워진 칸(다른 테트로미노)과의 충돌 검사, 현재 테트로미노의 위치를 제외
                    if (board[boardY][boardX] != EMPTY) {
                        return false; // 이동하려는 위치가 빈 공간이 아니고, 현재 테트로미노의 위치도 아닌 경우
                    }
                }
            }
        }
        return true; // 위의 모든 검사를 통과한 경우, 회전 가능
    }

}
