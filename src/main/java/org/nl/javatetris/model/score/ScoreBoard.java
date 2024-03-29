package org.nl.javatetris.model.score;


import java.util.ArrayList;
import java.util.List;

public class ScoreBoard {

    private List<Score> scores = new ArrayList<>();

    public ScoreBoard() {
    }

    // TODO : 점수판에 점수를 추가하는 메서드
    public boolean addScore(String name, int score) {
        /**
         * 체크해야 할 상황
         * 점수판에 동일한 이름이 존재하는지 확인 -> 기존 점수보다 높으면 추가, 아니면 추가하지 않음
         * 점수판이 꽉 찼는지 확인 -> 꽉 찼으면 10등안에 드는지 확인해서 추가, 아니면 추가하지 않음
         */

        return true;
    }

    public List<Score> getScores() {
        return scores;
    }

    // TODO : 점수판을 초기화하는 메서드


}