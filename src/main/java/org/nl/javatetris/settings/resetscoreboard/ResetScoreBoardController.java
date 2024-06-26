package org.nl.javatetris.settings.resetscoreboard;

import javafx.scene.input.KeyEvent;
import org.nl.javatetris.scoreboard.ScoreBoard;

public class ResetScoreBoardController {
    private int meunItemsCount;
    private Runnable onSettings;
    private int selectedItemIndex = 0;

    public ResetScoreBoardController(int menuItemsCount, Runnable onSettings) {
        this.meunItemsCount = menuItemsCount;
        this.onSettings = onSettings;
    }

    public void handleKeyPress(KeyEvent e) {
        switch (e.getCode()) {
            case ESCAPE:
                onSettings.run();
                break;
            case UP:
                selectedItemIndex = Math.max(0, selectedItemIndex - 1);
                break;
            case DOWN:
                selectedItemIndex = Math.min(meunItemsCount - 1, selectedItemIndex + 1);
                break;
            case ENTER:
                switch (selectedItemIndex) {
                    //로직 추가예정
                    case 0:
                        //예 -> 초기화 진행
                        ScoreBoard.getInstance().clear();
                        ScoreBoard.getInstance().saveScoreboard();
                        onSettings.run();
                        break;
                    case 1:
                        //아니오-> 설정으로 돌아가기
                        onSettings.run();
                        break;

                }
        }
    }

    public int getSelectedItemIndex() {
        return selectedItemIndex;
    }

}
