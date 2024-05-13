package org.nl.javatetris.settings;

import javafx.scene.paint.Color;

import java.io.Serializable;

import static org.nl.javatetris.config.constant.ControllerConst.*;
import static org.nl.javatetris.config.constant.ModelConst.*;
import static org.nl.javatetris.config.constant.ViewConst.*;

public class Settings implements Serializable {

    private static final long serialVersionUID = 3L;

    private static Settings instance;

    private SizeSetting sizeSetting;
    private KeySetting keySetting;
    private ColorSetting colorSetting;

    private Settings() {
        sizeSetting = new SizeSetting();
        keySetting = new KeySetting();
        colorSetting = new ColorSetting();
    }

    public static void ready() {
        if (instance == null) {
            instance = new Settings();
        }
    }

    public static Settings getInstance() {
        ready();
        return instance;
    }

    public static void setInstance (Settings settings) {
        instance = settings;
    }

    //세팅 초기화
    public static void initSettings() {
        instance = new Settings();
        SettingsManager.saveSettings();
    }

    public SizeSetting getSizeSetting() {
        return sizeSetting;
    }

    public KeySetting getKeySetting() {
        return keySetting;
    }

    public ColorSetting getColorSetting() {
        return colorSetting;
    }

    public class SizeSetting implements Serializable {

        private int blockSize;
        private int previewBlockSize;
        private int sidebarSize;
        private int screenWidth;
        private int screenHeight;
        private int defaultFontSize;
        private int titleFontSize;
        private int offset = 0;

        public SizeSetting() {
            this.blockSize = CELL_SIZE;
            this.previewBlockSize = PREVIEW_CELL_SIZE;
            this.sidebarSize = DEFAULT_SIDEBAR_SIZE;
            this.screenWidth = DEFAULT_WINDOW_WIDTH;
            this.screenHeight = DEFAULT_WINDOW_HEIGHT;
            this.defaultFontSize = BASE_DEFAULT_FONT_SIZE;
            this.titleFontSize = BASE_TITLE_FONT_SIZE;
        }

        public int getBlockSize() {
            return blockSize;
        }

        public int getPreviewBlockSize() {
            return previewBlockSize;
        }

        public int getSidebarSize() {
            return sidebarSize;
        }

        public int getOffset(){
            return offset;
        }

        public int getScreenWidth() {
            return screenWidth;
        }

        public int getScreenHeight() {
            return screenHeight;
        }

        public int getDefaultFontSize() {
            return defaultFontSize;
        }

        public int getTitleFontSize() {
            return titleFontSize;
        }

        public void setScreenSizeBigger() {
            if (offset < 2) {
                blockSize = (int) (blockSize * 1.5);
                sidebarSize = (int) (sidebarSize * 1.5);
                previewBlockSize = (int) (previewBlockSize * 1.5);
                screenWidth = blockSize * X_MAX + sidebarSize;
                screenHeight = blockSize * Y_MAX;
                defaultFontSize = (int) (defaultFontSize * 1.5);
                titleFontSize = (int) (titleFontSize * 1.5);
                offset++;
            }
        }

        public void setScreenSizeDefault() {
            blockSize = CELL_SIZE;
            previewBlockSize = PREVIEW_CELL_SIZE;
            sidebarSize = DEFAULT_SIDEBAR_SIZE;
            screenWidth = DEFAULT_WINDOW_WIDTH;
            screenHeight = DEFAULT_WINDOW_HEIGHT;
            defaultFontSize = BASE_DEFAULT_FONT_SIZE;
            titleFontSize = BASE_TITLE_FONT_SIZE;
            offset = 0 ;
        }

    }


    public class KeySetting implements Serializable {

        private int leftKeyValue;
        private int rightKeyValue;
        private int downKeyValue;
        private int rotateKeyValue;
        private int dropKeyValue;
        private int p1LeftKeyValue;
        private int p1RightKeyValue;
        private int p1DownKeyValue;
        private int p1RotateKeyValue;
        private int p1DropKeyValue;
        private int p2LeftKeyValue;
        private int p2RightKeyValue;
        private int p2DownKeyValue;
        private int p2RotateKeyValue;
        private int p2DropKeyValue;




        public KeySetting() {
            this.leftKeyValue = DEFAULT_LEFT_KEY;
            this.rightKeyValue = DEFAULT_RIGHT_KEY;
            this.downKeyValue = DEFAULT_DOWN_KEY;
            this.rotateKeyValue = DEFAULT_ROTATE_KEY;
            this.dropKeyValue = DEFAULT_DROP_KEY;

            this.p1LeftKeyValue = DEFAULT_P1_LEFT_KEY;
            this.p1RightKeyValue = DEFAULT_P1_RIGHT_KEY;
            this.p1DownKeyValue = DEFAULT_P1_DOWN_KEY;
            this.p1RotateKeyValue = DEFAULT_P1_ROTATE_KEY;
            this.p1DropKeyValue = DEFAULT_P1_DROP_KEY;

            this.p2LeftKeyValue = DEFAULT_P2_LEFT_KEY;
            this.p2RightKeyValue = DEFAULT_P2_RIGHT_KEY;
            this.p2DownKeyValue = DEFAULT_P2_DOWN_KEY;
            this.p2RotateKeyValue = DEFAULT_P2_ROTATE_KEY;
            this.p2DropKeyValue = DEFAULT_P2_DROP_KEY;
        }

        public int getLeftKeyValue() {
            return leftKeyValue;
        }

        public void setLeftKeyValue(int leftKeyValue) {
            this.leftKeyValue = leftKeyValue;
        }

        public String getLeftKeyString() {
            return getKeyString(leftKeyValue);
        }

        public int getRightKeyValue() {
            return rightKeyValue;
        }

        public String getRightKeyString() {
            return getKeyString(rightKeyValue);
        }

        public void setRightKeyValue(int rightKeyValue) {
            this.rightKeyValue = rightKeyValue;
        }

        public int getDownKeyValue() {
            return downKeyValue;
        }

        public String getDownKeyString() {
            return getKeyString(downKeyValue);
        }

        public void setDownKeyValue(int downKeyValue) {
            this.downKeyValue = downKeyValue;
        }

        public int getRotateKeyValue() {
            return rotateKeyValue;
        }

        public String getRotateKeyString() {
            return getKeyString(rotateKeyValue);
        }

        public void setRotateKeyValue(int rotateKeyValue) {
            this.rotateKeyValue = rotateKeyValue;
        }

        public int getDropKeyValue() {
            return dropKeyValue;
        }

        public String getDropKeyString() {
            return getKeyString(dropKeyValue);
        }

        public void setDropKeyValue(int dropKeyValue) {this.dropKeyValue = dropKeyValue;}

        // Player 1 Getters and Setters
        public int getP1LeftKeyValue() {
            return p1LeftKeyValue;
        }

        public String getP1LeftKeyString() {return getKeyString(p1LeftKeyValue);}

        public void setP1LeftKeyValue(int p1LeftKeyValue) {
            this.p1LeftKeyValue = p1LeftKeyValue;
        }

        public int getP1RightKeyValue() {
            return p1RightKeyValue;
        }

        public String getP1RightKeyString() {return getKeyString(p1RightKeyValue);}

        public void setP1RightKeyValue(int p1RightKeyValue) {
            this.p1RightKeyValue = p1RightKeyValue;
        }

        public int getP1DownKeyValue() {
            return p1DownKeyValue;
        }

        public String getP1DownKeyString() {return getKeyString(p1DownKeyValue);}

        public void setP1DownKeyValue(int p1DownKeyValue) {
            this.p1DownKeyValue = p1DownKeyValue;
        }

        public int getP1RotateKeyValue() {
            return p1RotateKeyValue;
        }

        public String getP1RotateKeyString() {return getKeyString(p1RotateKeyValue);}

        public void setP1RotateKeyValue(int p1RotateKeyValue) {
            this.p1RotateKeyValue = p1RotateKeyValue;
        }

        public int getP1DropKeyValue() {
            return p1DropKeyValue;
        }

        public String getP1DropKeyString() {return getKeyString(p1DropKeyValue);}

        public void setP1DropKeyValue(int p1DropKeyValue) {
            this.p1DropKeyValue = p1DropKeyValue;
        }

        // Player 2 Getters and Setters
        public int getP2LeftKeyValue() {
            return p2LeftKeyValue;
        }

        public String getP2LeftKeyString() {return getKeyString(p2LeftKeyValue);}

        public void setP2LeftKeyValue(int p2LeftKeyValue) {
            this.p2LeftKeyValue = p2LeftKeyValue;
        }

        public int getP2RightKeyValue() {
            return p2RightKeyValue;
        }

        public String getP2RightKeyString() {return getKeyString(p2RightKeyValue);}

        public void setP2RightKeyValue(int p2RightKeyValue) {
            this.p2RightKeyValue = p2RightKeyValue;
        }

        public int getP2DownKeyValue() {
            return p2DownKeyValue;
        }

        public String getP2DownKeyString() {return getKeyString(p2DownKeyValue);}

        public void setP2DownKeyValue(int p2DownKeyValue) {
            this.p2DownKeyValue = p2DownKeyValue;
        }

        public int getP2RotateKeyValue() {
            return p2RotateKeyValue;
        }

        public String getP2RotateKeyString() {return getKeyString(p2RotateKeyValue);}

        public void setP2RotateKeyValue(int p2RotateKeyValue) {
            this.p2RotateKeyValue = p2RotateKeyValue;
        }

        public int getP2DropKeyValue() {
            return p2DropKeyValue;
        }

        public String getP2DropKeyString() {return getKeyString(p2DropKeyValue);}

        public void setP2DropKeyValue(int p2DropKeyValue) {
            this.p2DropKeyValue = p2DropKeyValue;
        }


        // 27 91 92 93 112 ~ 123 144 145 불가능 (ESC, 윈도우, F1~F12, NUMLOCK, SCROLLLOCK)
        // 임시로 이렇게 처리 후에 다른데로 옮기던지 방법 찾기
        public static String getKeyString(int value) {
            if (value >= 48 && value <= 90)
                return String.valueOf((char)value);
            switch (value) {
                case 8:
                    return "BACKSPACE";
                case 9:
                    return "TAB";
                case 16:
                    return "SHIFT";
                case 17:
                    return "CONTROL";
                case 18:
                    return "ALT";
                case 19:
                    return "PAUSE/BREAK";
                case 20:
                    return "CAPSLOCK";
                case 32:
                    return "SPACE";
                case 33:
                    return "PAGE_UP";
                case 34:
                    return "PAGE_DOWN";
                case 35:
                    return "END";
                case 36:
                    return "HOME";
                case 37:
                    return "LEFT";
                case 38:
                    return "UP";
                case 39:
                    return "RIGHT";
                case 40:
                    return "DOWN";
                case 45:
                    return "INSERT";
                case 46:
                    return "DELETE";
                case 96:
                    return "NUMPAD0";
                case 97:
                    return "NUMPAD1";
                case 98:
                    return "NUMPAD2";
                case 99:
                    return "NUMPAD3";
                case 100:
                    return "NUMPAD4";
                case 101:
                    return "NUMPAD5";
                case 102:
                    return "NUMPAD6";
                case 103:
                    return "NUMPAD7";
                case 104:
                    return "NUMPAD8";
                case 105:
                    return "NUMPAD9";
                case 106:
                    return "NUMPAD*";
                case 107:
                    return "NUMPAD+";
                case 109:
                    return "NUMPAD-";
                case 110:
                    return "NUMPAD.";
                case 111:
                    return "NUMPAD/";
                case 186 :
                    return ";";
                case 187:
                    return "=";
                case 188:
                    return ",";
                case 189:
                    return "-";
                case 190:
                    return ".";
                case 191:
                    return "/";
                case 192:
                    return "`";
                case 219:
                    return "[";
                case 220:
                    return "\\";
                case 221:
                    return "]";
                case 222:
                    return "'";
                default:
                    return null; // 알 수 없는 키에 대해서는 null 반환
            }
        }
    }

    public class ColorSetting implements Serializable {
        private String[][] colorArray; // Color 대신 String 사용
        private int offset = 0;

        public ColorSetting() {
//            {SKYBLUE, BLUE, ORANGE, YELLOW, LIGHTGREEN, PURPLE, RED},
//            {GRAY, BLUE, ORANGE, YELLOW, TURQUOISE, PURPLE, PINK},
//            {GRAY, PINK, ORANGE, YELLOW, GREEN, PURPLE, RED}
            colorArray = new String[][]{
                    {"#87CEEB", "#0000FF", "#FFA500", "#FFFF00", "#90EE90", "#800080", "#FF0000"},
                    {"#808080", "#0000FF", "#FFA500", "#FFFF00", "#40E0D0", "#800080", "#FFC0CB"},
                    {"#808080", "#FFC0CB", "#FFA500", "#FFFF00", "#008000", "#800080", "#FF0000"}
            };
        }

        public int getColorOffset() {
            return offset;
        }

        // 타입에 따른 Color 객체 반환
        public Color getColorOfTetrominoType(Integer type) {
            return Color.web(colorArray[offset][type-1]);
        }

        public void roundColorSetting() {
            offset = (offset + 1) % 3;
        }
    }
}
