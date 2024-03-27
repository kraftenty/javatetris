package org.nl.javatetris.model.settings;
import javafx.scene.paint.Color;

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
            // TODO : JSON 파일을 읽어오는 로직
            singletonInstance = new Settings();
        }
        return singletonInstance;
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

        private int screenWidth;

        private int screenHeight;

        private int offset = 0;

        public ScreenSizeSettings() {
            this.blockSize = CELL_SIZE;
            this.screenWidth = DEFAULT_WINDOW_WIDTH;
            this.screenHeight = DEFAULT_WINDOW_HEIGHT;
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

        public void setScreenSizeBigger() {
            if (offset < 2) {
                blockSize = (int) (blockSize * 1.5);
                screenWidth = blockSize * X_MAX + DEFAULT_SIDEBAR_SIZE;
                screenHeight = blockSize * Y_MAX;
                offset++;
            }
        }

        public void setScreenSizeDefault() {
            blockSize = CELL_SIZE;
            screenWidth = DEFAULT_WINDOW_WIDTH;
            screenHeight = DEFAULT_WINDOW_HEIGHT;
            offset = 0;
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

        public int getRightKeyValue() {
            return rightKeyValue;
        }

        public void setRightKeyValue(int rightKeyValue) {
            this.rightKeyValue = rightKeyValue;
        }

        public int getDownKeyValue() {
            return downKeyValue;
        }

        public void setDownKeyValue(int downKeyValue) {
            this.downKeyValue = downKeyValue;
        }

        public int getRotateKeyValue() {
            return rotateKeyValue;
        }

        public void setRotateKeyValue(int rotateKeyValue) {
            this.rotateKeyValue = rotateKeyValue;
        }

        public int getDropKeyValue() {
            return dropKeyValue;
        }

        public void setDropKeyValue(int dropKeyValue) {
            this.dropKeyValue = dropKeyValue;
        }
    }


    public class ColorSetting {

        private Color[][] colorArray;

        private int offset = 0;

        public ColorSetting() {
            colorArray = new Color[][] {
                    {},
                    {},
                    {}
            };
        }

        public Color getColorOfTetrominoType(Integer type) {
            return colorArray[offset][type];
        }

        public void roundColorSetting() {
            offset = (offset + 1) % 3;
        }

    }
}
