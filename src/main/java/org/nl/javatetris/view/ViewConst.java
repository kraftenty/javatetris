package org.nl.javatetris.view;

import static org.nl.javatetris.model.ModelConst.*;

public class ViewConst {

    public static final int CELL_SIZE = 20;
    public static final int PREVIEW_CELL_SIZE = 15;
    public static final int DEFAULT_SIDEBAR_SIZE = 140;
    public static int DEFAULT_WINDOW_WIDTH = X_MAX * CELL_SIZE + DEFAULT_SIDEBAR_SIZE;
    public static int DEFAULT_WINDOW_HEIGHT = Y_MAX * CELL_SIZE;

    public static final int NO_SCENE = 0;
    public static final int START_MENU_SCENE = 1;
    public static final int GAME_PLAY_SCENE = 2;
    public static final int PAUSE_MENU_SCENE = 3;
    public static final int SETTINGS_MENU_SCENE = 4;
    public static final int SCORE_BOARD_SCENE = 5;

    public static final int CHECKING_INIT_SET_SCENE =6;
    public static final int CHECKING_BOARD_INIT=7;
    public static final int SETTING_KEY_MENU_SCENE=8;
    public static final int GAME_OVER_SCENE = 9;
}
