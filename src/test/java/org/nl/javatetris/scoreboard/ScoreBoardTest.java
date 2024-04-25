package org.nl.javatetris.scoreboard;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreBoardTest {

    /**
     * 싱글톤 패턴이 잘 적용되었는지 테스트
     */
    @Test
    public void singletonTest() {
        ScoreBoard scoreBoard1 = ScoreBoard.getInstance();
        ScoreBoard scoreBoard2 = ScoreBoard.getInstance();
        assertEquals(scoreBoard1, scoreBoard2);
    }

    /**
     * classicModeScores 에 동일한 이름이 있는 경우 더 높은 점수로 갱신되는지 테스트
     */
    @Test
    public void addScoreInClassicMode_DuplicatedNameTest() {
        // given
        ScoreBoard scoreBoard = ScoreBoard.getInstance();
        scoreBoard.clear();
        scoreBoard.addScoreInClassicModeScores("aaa", 100, 0);
        scoreBoard.addScoreInClassicModeScores("aaa", 200, 0);

        // when
        List<Score> classicModeScores = scoreBoard.getClassicModeScores();

        // then
        assertEquals(1, classicModeScores.size());
        assertEquals(200, classicModeScores.get(0).getPoint());
    }

    /**
     * classicModeScores 에 10개 기록이 다 찬 경우, 더 높은 점수가 추가되었을 때 가장 낮은 점수가 삭제되는지 테스트
     */
    @Test
    public void addScoreInClassicMode_FullScoresTest() {
        // given
        ScoreBoard scoreBoard = ScoreBoard.getInstance();
        scoreBoard.clear();
        // 10명의 플레이어 추가
        for (int i = 1; i <= 10; i++) {
            scoreBoard.addScoreInClassicModeScores("player" + Integer.toString(i), i * 100, 0);
        }
        // 11번째 플레이어 추가
        scoreBoard.addScoreInClassicModeScores("newPlayer", 10000, 0);

        // when
        List<Score> classicModeScores = scoreBoard.getClassicModeScores();

        // then
        assertEquals(10, classicModeScores.size());
        assertEquals(200, classicModeScores.get(9).getPoint());
    }

    /**
     * itemModeScores 에 동일한 이름이 있는 경우 더 높은 점수로 갱신되는지 테스트
     */
    @Test
    public void addScoreInItemMode_DuplicatedNameTest() {
        // given
        ScoreBoard scoreBoard = ScoreBoard.getInstance();
        scoreBoard.clear();
        scoreBoard.addScoreInItemModeScores("aaa", 100);
        scoreBoard.addScoreInItemModeScores("aaa", 200);

        // when
        List<Score> itemModeScores = scoreBoard.getItemModeScores();

        // then
        assertEquals(1, itemModeScores.size());
        assertEquals(200, itemModeScores.get(0).getPoint());
    }

    /**
     * itemModeScores 에 10개 기록이 다 찬 경우, 더 높은 점수가 추가되었을 때 가장 낮은 점수가 삭제되는지 테스트
     */
    @Test
    public void addScoreInItemMode_FullScoresTest() {
        // given
        ScoreBoard scoreBoard = ScoreBoard.getInstance();
        scoreBoard.clear();
        // 10명의 플레이어 추가
        for (int i = 1; i <= 10; i++) {
            scoreBoard.addScoreInItemModeScores("player" + Integer.toString(i), i * 100);
        }
        // 11번째 플레이어 추가
        scoreBoard.addScoreInItemModeScores("newPlayer", 10000);

        // when
        List<Score> itemModeScores = scoreBoard.getItemModeScores();

        // then
        assertEquals(10, itemModeScores.size());
        assertEquals(200, itemModeScores.get(9).getPoint());
    }

    /**
     * canUpdateClassicModeScores 가 정상적으로 동작하는지 테스트
     */
    @Test
    public void canUpdateClassicModeScoresTest() {
        // given
        ScoreBoard scoreBoard = ScoreBoard.getInstance();
        scoreBoard.clear();
        // 10명의 플레이어 추가
        for (int i = 1; i <= 10; i++) {
            scoreBoard.addScoreInClassicModeScores("player" + Integer.toString(i), i * 100, 0);
        }

        // when
        boolean canUpdate1 = scoreBoard.canUpdateClassicModeScores(1000);
        boolean canUpdate2 = scoreBoard.canUpdateClassicModeScores(50);

        // then
        assertEquals(true, canUpdate1);
        assertEquals(false, canUpdate2);
    }

    /**
     * canUpdateItemModeScores 가 정상적으로 동작하는지 테스트
     */
    @Test
    public void canUpdateItemModeScoresTest() {
        // given
        ScoreBoard scoreBoard = ScoreBoard.getInstance();
        scoreBoard.clear();
        // 10명의 플레이어 추가
        for (int i = 1; i <= 10; i++) {
            scoreBoard.addScoreInItemModeScores("player" + Integer.toString(i), i * 100);
        }

        // when
        boolean canUpdate1 = scoreBoard.canUpdateItemModeScores(1000);
        boolean canUpdate2 = scoreBoard.canUpdateItemModeScores(50);

        // then
        assertEquals(true, canUpdate1);
        assertEquals(false, canUpdate2);
    }

}
