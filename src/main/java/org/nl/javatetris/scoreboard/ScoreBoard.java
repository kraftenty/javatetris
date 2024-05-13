package org.nl.javatetris.scoreboard;


import org.nl.javatetris.config.constant.ControllerConst;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.nl.javatetris.config.constant.ModelConst.*;

public class ScoreBoard implements Serializable {

    @Serial
    private static final long serialVersionUID = 2L;

    private static ScoreBoard instance;

    // 클래식 모드
    private List<Score> classicModeScores;
    private static int classicModeRecentScoreIndex = 0;
    private static boolean classicModeRecentScoreViewedFlag = true;

    // 아이템 모드
    private List<Score> itemModeScores;
    private static int itemModeRecentScoreIndex = 0;
    private static boolean itemModeRecentScoreViewedFlag = true;


    private ScoreBoard() {
        loadScoreBoard(); // 인스턴스 생성 시 점수판을 자동으로 불러옴
    }

    public static void ready() {
        if (instance == null) {
            instance = new ScoreBoard();
        }
    }

    public static ScoreBoard getInstance() {
        ready();
        return instance;
    }


    public List<Score> getClassicModeScores() {
        return classicModeScores;
    }

    public static int getClassicModeRecentScoreIndex() {
        return classicModeRecentScoreIndex;
    }

    public static boolean isClassicModeRecentScoreViewedFlag() {
        return classicModeRecentScoreViewedFlag;
    }

    public static void setClassicModeRecentScoreViewedFlag(boolean classicModeRecentScoreViewedFlag) {
        ScoreBoard.classicModeRecentScoreViewedFlag = classicModeRecentScoreViewedFlag;
    }

    public List<Score> getItemModeScores() {
        return itemModeScores;
    }

    public static int getItemModeRecentScoreIndex() {
        return itemModeRecentScoreIndex;
    }

    public static boolean isItemModeRecentScoreViewedFlag() {
        return itemModeRecentScoreViewedFlag;
    }

    public static void setItemModeRecentScoreViewedFlag(boolean itemModeRecentScoreViewedFlag) {
        ScoreBoard.itemModeRecentScoreViewedFlag = itemModeRecentScoreViewedFlag;
    }

    public boolean canUpdateClassicModeScores(int point) {
        // 스코어보드가 아직 꽉 차지 않았거나, 주어진 점수가 스코어보드의 마지막 점수보다 높은 경우 갱신 가능
        if (classicModeScores.size() < MAX_SCOREBOARD_SIZE || point > classicModeScores.get(classicModeScores.size() - 1).getPoint()) {
            return true;
        }
        return false;
    }

    public boolean canUpdateItemModeScores(int point) {
        // 스코어보드가 아직 꽉 차지 않았거나, 주어진 점수가 스코어보드의 마지막 점수보다 높은 경우 갱신 가능
        if (itemModeScores.size() < MAX_SCOREBOARD_SIZE || point > itemModeScores.get(itemModeScores.size() - 1).getPoint()) {
            return true;
        }
        return false;
    }

    // classic mode 점수판에 점수를 추가하는 메서드
    public boolean addScoreInClassicModeScores(String name, int point, int difficulty) {
        // 점수판에 동일한 이름이 있을 때
        for (int i = 0; i < classicModeScores.size(); i++) {
            Score s = classicModeScores.get(i);
            if (s.getName().equals(name)) {
                if (s.getPoint() < point) {
                    classicModeScores.remove(i);
                    Score newScore = new Score(name, point, difficulty);
                    classicModeScores.add(newScore);
                    classicModeScores.sort(Score::compareTo);
                    classicModeRecentScoreIndex = classicModeScores.indexOf(newScore);
                    classicModeRecentScoreViewedFlag = false;
                    return true;
                } else {
                    // 기존 점수보다 낮거나 같은 경우 순위 변경 없음
                    return false;
                }
            }
        }

        // 점수판에 동일한 이름이 없을 때
        if (classicModeScores.size() < MAX_SCOREBOARD_SIZE || point > classicModeScores.get(classicModeScores.size() - 1).getPoint()) {
            // 점수판에 여유가 있거나, 새로운 점수가 최소 점수보다 높은 경우
            Score newScore = new Score(name, point, difficulty);
            classicModeScores.add(newScore);
            classicModeScores.sort(Score::compareTo);
            if (classicModeScores.size() > MAX_SCOREBOARD_SIZE) {
                // 점수판이 꽉 찬 경우, 가장 낮은 점수 제거
                classicModeScores.remove(classicModeScores.size() - 1);
            }
            // 새로운 점수의 순위를 찾아서 반환
            classicModeRecentScoreIndex = classicModeScores.indexOf(newScore);
            classicModeRecentScoreViewedFlag = false;
            return true;
        }

        // 새로운 점수가 점수판에 들어갈 수 없는 경우
        return false;
    }

    // item mode 점수판에 점수를 추가하는 메서드
    public boolean addScoreInItemModeScores(String name, int point) {
        // 점수판에 동일한 이름이 있을 때
        for (int i = 0; i < itemModeScores.size(); i++) {
            Score s = itemModeScores.get(i);
            if (s.getName().equals(name)) {
                if (s.getPoint() < point) {
                    itemModeScores.remove(i);
                    Score newScore = new Score(name, point, ControllerConst.DIFFICULTY_NORMAL);
                    itemModeScores.add(newScore);
                    itemModeScores.sort(Score::compareTo);
                    itemModeRecentScoreIndex = itemModeScores.indexOf(newScore);
                    itemModeRecentScoreViewedFlag = false;
                    return true;
                } else {
                    // 기존 점수보다 낮거나 같은 경우 순위 변경 없음
                    return false;
                }
            }
        }

        // 점수판에 동일한 이름이 없을 때
        if (itemModeScores.size() < MAX_SCOREBOARD_SIZE || point > itemModeScores.get(itemModeScores.size() - 1).getPoint()) {
            // 점수판에 여유가 있거나, 새로운 점수가 최소 점수보다 높은 경우
            Score newScore = new Score(name, point, ControllerConst.DIFFICULTY_NORMAL);
            itemModeScores.add(newScore);
            itemModeScores.sort(Score::compareTo);
            if (itemModeScores.size() > MAX_SCOREBOARD_SIZE) {
                // 점수판이 꽉 찬 경우, 가장 낮은 점수 제거
                itemModeScores.remove(itemModeScores.size() - 1);
            }
            // 새로운 점수의 순위를 찾아서 반환
            itemModeRecentScoreIndex = itemModeScores.indexOf(newScore);
            itemModeRecentScoreViewedFlag = false;
            return true;
        }

        // 새로운 점수가 점수판에 들어갈 수 없는 경우
        return false;
    }




    // 점수판을 초기화하는 메서드
    public void clear() {
        classicModeScores.clear();
        itemModeScores.clear();
    }

    // 점수판을 파일에 저장하는 메서드
    public void saveScoreboard() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SCOREBOARD_FILE_NAME))) {
            oos.writeObject(classicModeScores);
            oos.writeObject(itemModeScores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 파일에서 점수판을 불러오는 메서드
    public void loadScoreBoard() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SCOREBOARD_FILE_NAME))) {
            classicModeScores = (List<Score>) ois.readObject();
            itemModeScores = (List<Score>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // 파일이 없거나 읽어오는 데 실패한 경우, 새 리스트로 초기화
            classicModeScores = new ArrayList<>();
            itemModeScores = new ArrayList<>();
        }
    }

}