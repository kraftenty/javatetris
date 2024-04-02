package org.nl.javatetris.controller;

public class ControllerConst {


    public static final int DOWN_SCORE = 1;
    public static final int LINE_CLEAR_SCORE = 100;

    public static final int LEVEL_UP_SCORE = 500;

    public static final int DEFAULT_LEFT_KEY = 37;
    public static final int DEFAULT_RIGHT_KEY = 39;
    public static final int DEFAULT_DOWN_KEY = 40;
    public static final int DEFAULT_ROTATE_KEY = 38;
    public static final int DEFAULT_DROP_KEY = 32;

    public static final int[] invalidKeys = {
            10, 13, 27, 91, 92, 93, 112, 113,
            114, 115, 116, 117, 118, 119,
            120, 121, 122, 123, 144, 145, 524, 768
    };
    // 27 91 92 93 112 ~ 123 144 145 불가능 (ESC, 윈도우, F1~F12, NUMLOCK, SCROLLLOCK)
}
