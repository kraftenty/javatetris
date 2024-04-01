package org.nl.javatetris.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.nl.javatetris.model.score.ScoreBoard;

public class ScoreBoardTest {

    @Test
    // 기본적인 스코어보드 테스트
    public void scoreboardBasicTest() {

        ScoreBoard instance = ScoreBoard.getInstance();
        instance.addScore("aaa", 100);
        instance.addScore("bbb", 200);
        instance.addScore("ccc", 300);

        Assertions.assertEquals(3, instance.getScores().size());
    }

    @Test
    // 스코어보드가 10개 이하로 유지되는지 확인하는 테스트
    public void scoreboardOverflowTest() {

            ScoreBoard instance = ScoreBoard.getInstance();
            instance.addScore("aaa", 100);
            instance.addScore("bbb", 200);
            instance.addScore("ccc", 300);
            instance.addScore("ddd", 400);
            instance.addScore("eee", 500);
            instance.addScore("fff", 600);
            instance.addScore("ggg", 700);
            instance.addScore("hhh", 800);
            instance.addScore("iii", 900);
            instance.addScore("jjj", 1000);
            instance.addScore("lll", 1100);

            Assertions.assertEquals(10, instance.getScores().size());
    }

    @Test
    // 스코어보드에 동일 이름이 들어갔을 경우의 테스트
    public void scoreboardSameNameTest() {

        ScoreBoard instance = ScoreBoard.getInstance();
        instance.addScore("aaa", 100);
        instance.addScore("aaa", 200);
        instance.addScore("aaa", 300);

        Assertions.assertEquals(1, instance.getScores().size());
    }

    @Test
    // 스코어보드를 초기화하는 테스트
    public void scoreboardClearTest() {

        ScoreBoard instance = ScoreBoard.getInstance();
        instance.addScore("aaa", 100);
        instance.addScore("bbb", 200);
        instance.addScore("ccc", 300);

        instance.clearScoreboard();

        Assertions.assertEquals(0, instance.getScores().size());
    }

    @Test
    // 스코어보드를 저장하고, 불러오는 테스트
    public void scoreboardSaveAndLoadTest() {

        ScoreBoard instance = ScoreBoard.getInstance();
        instance.addScore("aaa", 100);
        instance.addScore("bbb", 200);
        instance.addScore("ccc", 300);

        instance.saveScoreboard();
        instance.clearScoreboard();
        instance.loadScoreBoard();

        Assertions.assertEquals(3, instance.getScores().size());
    }
}
