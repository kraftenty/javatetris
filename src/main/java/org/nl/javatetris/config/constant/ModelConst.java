package org.nl.javatetris.config.constant;

public class ModelConst {

    public static final int TETROMINO_TYPES = 7; // 테트로미노 종류 수

    public static final int EMPTY = 0;          // 0
    public static final int I = 1;              // 1
    public static final int J = 2;              // 2
    public static final int L = 3;              // 3
    public static final int O = 4;              // 4
    public static final int S = 5;              // 5
    public static final int T = 6;              // 6
    public static final int Z = 7;              // 7
    public static final int BORDER = 9;         // 9

    public static final int EI = 11;
    public static final int EJ = 12;
    public static final int EL = 13;
    public static final int EO = 14;
    public static final int ES = 15;
    public static final int ET = 16;
    public static final int EZ = 17;
    public static final int E = 100;          // 줄을 지우는 블록

    public static final int Y_MAX = 22;          // 세로 크기 (20 + 테두리 2칸)
    public static final int X_MAX = 12;          // 가로 크기 (10 + 테두리 2칸)


    public static final int MAX_SCOREBOARD_SIZE = 10; // 최대

    public static final String SCOREBOARD_FILE_NAME = "scoreboard.dat"; // 점수판 파일 이름
    public static final String SETTINGS_FILE_NAME = "settings.dat"; // 설정 파일 이름
}
