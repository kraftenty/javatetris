/*package org.nl.javatetris;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.nl.javatetris.scoreboard.ScoreBoard;

public class ScoreBoardTest {

    //TODO : 테스트 수정 필요
    @Test
    // 기본적인 스코어보드 테스트
    public void scoreboardBasicTest() {

        ScoreBoard instance = ScoreBoard.getInstance();
        instance.addScoreByMode("aaa", 100, 0);
        instance.addScoreByMode("bbb", 200);
        instance.addScoreByMode("ccc", 300);

        Assertions.assertEquals(3, instance.getClassicModeScores().size());
    }

    @Test
    // 스코어보드가 10개 이하로 유지되는지 확인하는 테스트
    public void scoreboardOverflowTest() {

        ScoreBoard instance = ScoreBoard.getInstance();
        instance.addScoreByMode("aaa", 100);
        instance.addScoreByMode("bbb", 200);
        instance.addScoreByMode("ccc", 300);
        instance.addScoreByMode("ddd", 400);
        instance.addScoreByMode("eee", 500);
        instance.addScoreByMode("fff", 600);
        instance.addScoreByMode("ggg", 700);
        instance.addScoreByMode("hhh", 800);
        instance.addScoreByMode("iii", 900);
        instance.addScoreByMode("jjj", 1000);
        instance.addScoreByMode("lll", 1100);

        Assertions.assertEquals(10, instance.getClassicModeScores().size());
    }

    @Test
    // 스코어보드에 동일 이름이 들어갔을 경우의 테스트
    public void scoreboardSameNameTest() {

        ScoreBoard instance = ScoreBoard.getInstance();
        instance.addScoreByMode("aaa", 100);
        instance.addScoreByMode("aaa", 200);
        instance.addScoreByMode("aaa", 300);

        Assertions.assertEquals(1, instance.getClassicModeScores().size());
    }

    @Test
    // 스코어보드를 초기화하는 테스트
    public void scoreboardClearTest() {

        ScoreBoard instance = ScoreBoard.getInstance();
        instance.addScoreByMode("aaa", 100);
        instance.addScoreByMode("bbb", 200);
        instance.addScoreByMode("ccc", 300);

        instance.clear();

        Assertions.assertEquals(0, instance.getClassicModeScores().size());
    }

    @Test
    // 스코어보드를 저장하고, 불러오는 테스트
    public void scoreboardSaveAndLoadTest() {

        ScoreBoard instance = ScoreBoard.getInstance();
        instance.addScoreByMode("aaa", 100);
        instance.addScoreByMode("bbb", 200);
        instance.addScoreByMode("ccc", 300);

        instance.saveScoreboard();
        instance.clear();
        instance.loadScoreBoard();

        Assertions.assertEquals(3, instance.getClassicModeScores().size());
    }
}
*/