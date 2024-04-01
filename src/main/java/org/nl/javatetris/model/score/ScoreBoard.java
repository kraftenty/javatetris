package org.nl.javatetris.model.score;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.nl.javatetris.model.ModelConst.*;

public class ScoreBoard implements Serializable {

    private static ScoreBoard instance;
    private List<Score> scores;

    private ScoreBoard() {
        scores = new ArrayList<>();
        loadScoreboard(); // 인스턴스 생성 시 점수판을 자동으로 불러옴
    }

    public static ScoreBoard getInstance() {
        if (instance == null) {
            instance = new ScoreBoard();
        }
        return instance;
    }

    public List<Score> getScores() {
        return scores;
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
        for (Score s : scores) {
            if (s.getName().equals(name)) {
                if (s.getPoint() < point) {
                    scores.remove(s);
                    scores.add(new Score(name, point));
                    scores.sort(Score::compareTo);
                    return true;
                } else {
                    return false;
                }
            }
        }

        // 점수판에 동일한 이름이 없을 때
        if (scores.size() < MAX_SCOREBOARD_SIZE) {
            // 점수판이 꽉 차지 않은 경우
            scores.add(new Score(name, point));
            scores.sort(Score::compareTo);
            return true;
        } else {
            // 점수판이 꽉 찬 경우
            if (point > scores.get(scores.size() - 1).getPoint()) {
                // 새로운 점수가 최소 점수보다 높은 경우
                scores.remove(scores.size() - 1);
                scores.add(new Score(name, point));
                scores.sort(Score::compareTo);
                return true;
            } else {
                // 새로운 점수가 최소 점수보다 낮은 경우
                return false;
            }
        }
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
    public void loadScoreboard() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SCOREBOARD_FILE_NAME))) {
            scores = (List<Score>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // 파일이 없거나 읽어오는 데 실패한 경우, 새 리스트로 초기화
           scores = new ArrayList<>();
        }
    }

    // 디버그용
    public void printScoreboard() {
        for (Score s : scores) {
            System.out.println(s);
        }
    }


}