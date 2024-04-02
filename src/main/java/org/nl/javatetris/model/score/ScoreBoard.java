package org.nl.javatetris.model.score;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.nl.javatetris.model.ModelConst.*;

public class ScoreBoard implements Serializable {

    private static final long serialVersionUID = 2L;

    private static ScoreBoard instance;
    private List<Score> scores;

    // 최근 점수 관련 필드
    private static int recentScoreIndex = 0;
    private static boolean recentScoreViewedFlag = true;

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

    public List<Score> getScores() {
        return scores;
    }

    public static int getRecentScoreIndex() {
        return recentScoreIndex;
    }

    public static boolean isRecentScoreViewedFlag() {
        return recentScoreViewedFlag;
    }

    public static void setRecentScoreViewedFlag(boolean recentScoreViewedFlag) {
        ScoreBoard.recentScoreViewedFlag = recentScoreViewedFlag;
    }

    public boolean canUpdateScoreboard(int point) {
        // 스코어보드가 아직 꽉 차지 않았거나, 주어진 점수가 스코어보드의 마지막 점수보다 높은 경우 갱신 가능
        if (scores.size() < MAX_SCOREBOARD_SIZE || point > scores.get(scores.size() - 1).getPoint()) {
            return true;
        }
        return false;
    }


    // 점수판에 점수를 추가하는 메서드
    public boolean addScore(String name, int point) {
        // 점수판에 동일한 이름이 있을 때
        for (int i = 0; i < scores.size(); i++) {
            Score s = scores.get(i);
            if (s.getName().equals(name)) {
                if (s.getPoint() < point) {
                    scores.remove(i);
                    Score newScore = new Score(name, point);
                    scores.add(newScore);
                    scores.sort(Score::compareTo);
                    recentScoreIndex = scores.indexOf(newScore);
                    recentScoreViewedFlag = false;
                    return true;
                } else {
                    // 기존 점수보다 낮거나 같은 경우 순위 변경 없음
                    return false;
                }
            }
        }

        // 점수판에 동일한 이름이 없을 때
        if (scores.size() < MAX_SCOREBOARD_SIZE || point > scores.get(scores.size() - 1).getPoint()) {
            // 점수판에 여유가 있거나, 새로운 점수가 최소 점수보다 높은 경우
            Score newScore = new Score(name, point);
            scores.add(newScore);
            scores.sort(Score::compareTo);
            if (scores.size() > MAX_SCOREBOARD_SIZE) {
                // 점수판이 꽉 찬 경우, 가장 낮은 점수 제거
                scores.remove(scores.size() - 1);
            }
            // 새로운 점수의 순위를 찾아서 반환
            recentScoreIndex = scores.indexOf(newScore);
            recentScoreViewedFlag = false;
            return true;
        }

        // 새로운 점수가 점수판에 들어갈 수 없는 경우
        return false;
    }


    // 점수판을 초기화하는 메서드
    public void clearScoreboard() {
        scores.clear();
    }

    // 점수판을 파일에 저장하는 메서드
    public void saveScoreboard() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SCOREBOARD_FILE_NAME))) {
            oos.writeObject(scores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 파일에서 점수판을 불러오는 메서드
    public void loadScoreBoard() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SCOREBOARD_FILE_NAME))) {
            scores = (List<Score>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // 파일이 없거나 읽어오는 데 실패한 경우, 새 리스트로 초기화
            scores = new ArrayList<>();
        }
    }

}