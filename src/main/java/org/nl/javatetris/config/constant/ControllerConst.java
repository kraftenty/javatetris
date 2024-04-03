package org.nl.javatetris.config.constant;

public class ControllerConst {


    public static final int DOWN_SCORE = 1;
    public static final int LINE_CLEAR_SCORE = 100;

    public static final int LEVEL_UP_SCORE = 500;

    public static final int DEFAULT_LEFT_KEY = 37;
    public static final int DEFAULT_RIGHT_KEY = 39;
    public static final int DEFAULT_DOWN_KEY = 40;
    public static final int DEFAULT_ROTATE_KEY = 38;
    public static final int DEFAULT_DROP_KEY = 32;

    // 키 설정으로 등록되면 안되는 키.
    // 27 91 92 93 112 ~ 123 144 145 불가능 (ESC, 윈도우, F1~F12, NUMLOCK, SCROLLLOCK)
    // 윈도우 윈도우키 = 524, 맥os 커맨드키 = 768
    public static final int[] invalidKeys = {
            10, 13, 27, 91, 92, 93, 112, 113,
            114, 115, 116, 117, 118, 119,
            120, 121, 122, 123, 144, 145, 524, 768
    };

    // GameParam 에 사용될 게임모드
    public static final int MODE_CLASSIC = 0;
    public static final int MODE_ITEM = 1;

    // GameParam 에 사용될 난이도
    public static final int DIFFICULTY_EASY = 0;
    public static final int DIFFICULTY_NORMAL = 1;
    public static final int DIFFICULTY_HARD = 2;

}
