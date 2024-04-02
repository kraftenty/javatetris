package org.nl.javatetris.model.settings;

import javafx.scene.paint.Color;
import org.nl.javatetris.view.ViewConst;

import java.io.Serializable;

import static org.nl.javatetris.controller.ControllerConst.*;
import static org.nl.javatetris.model.ModelConst.*;
import static org.nl.javatetris.view.ViewConst.*;

public class Settings implements Serializable {

    private static final long serialVersionUID = 3L;

    private static Settings instance;

    private ScreenSizeSettings screenSizeSettings;
    private KeySetting keySetting;
    private ColorSetting colorSetting;

    private Settings() {
        screenSizeSettings = new ScreenSizeSettings();
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
        SettingsUtil.saveSettings();
    }

    public ScreenSizeSettings getScreenSizeSettings() {
        return screenSizeSettings;
    }

    public KeySetting getKeySetting() {
        return keySetting;
    }

    public ColorSetting getColorSetting() {
        return colorSetting;
    }

    public class ScreenSizeSettings implements Serializable {

        private int blockSize;
        private int previewBlockSize;
        private int screenWidth;
        private int screenHeight;
        private int defaultFontSize;
        private int titleFontSize;
        private int offset = 0;

        public ScreenSizeSettings() {
            this.blockSize = CELL_SIZE;
            this.previewBlockSize = PREVIEW_CELL_SIZE;
            this.screenWidth = DEFAULT_WINDOW_WIDTH;
            this.screenHeight = DEFAULT_WINDOW_HEIGHT;
            this.defaultFontSize = BASE_DEFAULT_FONT_SIZE;
            this.titleFontSize = BASE_TITLE_FONT_SIZE;
        }

        public int getBlockSize() {
            return blockSize;
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
                previewBlockSize = (int) (previewBlockSize * 1.5);
                screenWidth = blockSize * X_MAX + DEFAULT_SIDEBAR_SIZE;
                screenHeight = blockSize * Y_MAX;
                defaultFontSize = (int) (defaultFontSize * 1.5);
                titleFontSize = (int) (titleFontSize * 1.5);
                offset++;
            }
        }

        public void setScreenSizeDefault() {
            blockSize = CELL_SIZE;
            previewBlockSize = PREVIEW_CELL_SIZE;
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

        public KeySetting() {
            this.leftKeyValue = DEFAULT_LEFT_KEY;
            this.rightKeyValue = DEFAULT_RIGHT_KEY;
            this.downKeyValue = DEFAULT_DOWN_KEY;
            this.rotateKeyValue = DEFAULT_ROTATE_KEY;
            this.dropKeyValue = DEFAULT_DROP_KEY;
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

        public String getDowntKeyString() {
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
            return Color.web(colorArray[offset][type]);
        }

        public void roundColorSetting() {
            offset = (offset + 1) % 3;
        }
    }
}
