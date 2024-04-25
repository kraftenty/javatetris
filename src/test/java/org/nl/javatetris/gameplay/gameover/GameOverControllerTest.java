package org.nl.javatetris.gameplay.gameover;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.nl.javatetris.gameplay.gameover.*;
class GameOverControllerTest {
    @Test
    public void testOnBackToScoreBoard() {
        // Given
        boolean[] isRunnableCalled = {false};
        // 테스트용 Runnable 객체 생성
        Runnable runnable = () -> {
            isRunnableCalled[0] = true;
        };

        // When
        // GameOverController 객체 생성 및 Runnable 실행
        GameOverController gameOverController = new GameOverController(runnable);
        gameOverController.onBackToScoreBoard();

        // Then
        assertTrue(isRunnableCalled[0]);
    }
    }

