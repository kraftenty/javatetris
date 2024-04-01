package org.nl.javatetris.model.settings;
import javafx.scene.paint.Color;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.FileReader;
import java.io.IOException;

import static org.nl.javatetris.controller.ControllerConst.*;
import static org.nl.javatetris.model.ModelConst.*;
import static org.nl.javatetris.view.ViewConst.*;

public class Settings {

    private ScreenSizeSettings screenSizeSettings;

    private KeySetting keySetting;

    private ColorSetting colorSetting;

    private static Settings singletonInstance;

    private Settings() {
        screenSizeSettings = new ScreenSizeSettings();
        keySetting = new KeySetting();
        colorSetting = new ColorSetting();
    }

    public static Settings getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new Settings();
            loadSettings();
        }
        return singletonInstance;
    }

    private static void loadSettings() {
        try (FileReader reader = new FileReader("src/main/java/org/nl/javatetris/model/settings/settings.json")) {
            JSONTokener tokener = new JSONTokener(reader);
            JSONObject jsonObject = new JSONObject(tokener);
            JSONObject settings = jsonObject.getJSONObject("settings");

            // Screen size 불러오기
            int screenSizeOffset = settings.getInt("screen_size");
            ScreenSizeSettings screenSizeSettings = singletonInstance.getScreenSizeSettings();
            if (screenSizeOffset == 0) screenSizeSettings.setScreenSizeDefault();
            else if (screenSizeOffset == 1) screenSizeSettings.setScreenSizeBigger();
            else if (screenSizeOffset == 2) {
                screenSizeSettings.setScreenSizeBigger();
                screenSizeSettings.setScreenSizeBigger();
            }


            // Key_setting 불러오기
            JSONObject keySettingFromJson = settings.getJSONObject("key_setting");
            KeySetting keySetting = singletonInstance.getKeySetting();
            keySetting.setLeftKeyValue(keySettingFromJson.getInt("left_key"));
            keySetting.setRightKeyValue(keySettingFromJson.getInt("right_key"));
            keySetting.setRotateKeyValue(keySettingFromJson.getInt("rotate_key"));
            keySetting.setDownKeyValue(keySettingFromJson.getInt("down_key"));
            keySetting.setDropKeyValue(keySettingFromJson.getInt("drop_key"));

            int colorSettingValue = settings.getInt("color_setting");
            ColorSetting colorSetting = singletonInstance.getColorSetting();
            for (int i = 0; i < colorSettingValue; i++) {
                colorSetting.roundColorSetting();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void saveSettings() {
        JSONObject settings = new JSONObject();
        settings.put("screen_size", singletonInstance.getScreenSizeSettings().getOffset());
        settings.put("key_setting", new JSONObject()
                .put("left_key", singletonInstance.getKeySetting().getLeftKeyValue())
                .put("right_key", singletonInstance.getKeySetting().getRightKeyValue())
                .put("rotate_key", singletonInstance.getKeySetting().getRotateKeyValue())
                .put("down_key", singletonInstance.getKeySetting().getDownKeyValue())
                .put("drop_key", singletonInstance.getKeySetting().getDropKeyValue()));
        settings.put("color_setting", singletonInstance.getColorSetting().offset);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("settings", settings);

        try {
            java.io.FileWriter file = new java.io.FileWriter("src/main/java/org/nl/javatetris/model/settings/settings.json");
            file.write(jsonObject.toString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //세팅 초기화
    public static void initSettings() {
        singletonInstance = new Settings();
        saveSettings();
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

    public class ScreenSizeSettings {

        private int blockSize;

        private int previewBlockSize;

        private int screenWidth;

        private int screenHeight;

        private int offset = 0;

        public ScreenSizeSettings() {
            this.blockSize = CELL_SIZE;
            this.previewBlockSize = PREVIEW_CELL_SIZE;
            this.screenWidth = DEFAULT_WINDOW_WIDTH;
            this.screenHeight = DEFAULT_WINDOW_HEIGHT;
        }

        public int getBlockSize() {
            return blockSize;
        }

        public int getPreviewBlockSize() {
            return previewBlockSize;
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

        public void setScreenSizeBigger() {
            if (offset < 2) {
                blockSize = (int) (blockSize * 1.5);
                previewBlockSize = (int) (previewBlockSize * 1.5);
                screenWidth = blockSize * X_MAX + DEFAULT_SIDEBAR_SIZE;
                screenHeight = blockSize * Y_MAX;
                offset++;
            }
        }

        public void setScreenSizeDefault() {
            blockSize = CELL_SIZE;
            previewBlockSize = PREVIEW_CELL_SIZE;
            screenWidth = DEFAULT_WINDOW_WIDTH;
            screenHeight = DEFAULT_WINDOW_HEIGHT;
            offset = 0 ;
        }

    }




    public class KeySetting {

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


    public class ColorSetting {

        private Color[][] colorArray;

        private int offset = 0;

        public ColorSetting() {
            colorArray = new Color[][] {
                    {Color.SKYBLUE, Color.BLUE, Color.ORANGE, Color.YELLOW, Color.LIGHTGREEN, Color.PURPLE, Color.RED},
                    {Color.GRAY, Color.BLUE, Color.ORANGE, Color.YELLOW, Color.TURQUOISE, Color.PURPLE, Color.PINK},
                    {Color.GRAY, Color.PINK, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.PURPLE, Color.RED}
            };
            offset = 0;
        }
        public int getColorOffset() {
            return offset;
        }

        public Color getColorOfTetrominoType(Integer type) {
            return colorArray[offset][type];
        }

        public void roundColorSetting() {
            offset = (offset + 1) % 3;
        }

    }
}
