package org.nl.javatetris.settings;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SettingsTest {

    @BeforeAll
    public static void setUp() {
        Settings settings = Settings.getInstance();
    }

    /**
     * 싱글톤 패턴이 잘 유지되는지 테스트
     */
    @Test
    public void singletonTest() {
        Settings settings1 = Settings.getInstance();
        Settings settings2 = Settings.getInstance();
        assert(settings1 == settings2);
    }

    /**
     * SizeSetting 테스트
     */
    @Test
    public void sizeSettingTest() {
        Settings settings = Settings.getInstance();
        Settings.SizeSetting sizeSetting = settings.getSizeSetting();
        sizeSetting.setScreenSizeBigger();
        assert(sizeSetting.getOffset() == 1);
        sizeSetting.setScreenSizeBigger();
        assert(sizeSetting.getOffset() == 2);
        sizeSetting.setScreenSizeDefault();
        assert(sizeSetting.getOffset() == 0);
    }

    /**
     * KeySetting 테스트
     */
    @Test
    public void keySettingTest() {
        Settings settings = Settings.getInstance();
        Settings.KeySetting keySetting = settings.getKeySetting();

        // 기본 키 설정이 제대로 들어가 있는지 테스트
        Assertions.assertEquals(keySetting.getLeftKeyString(), "LEFT");
        Assertions.assertEquals(keySetting.getRightKeyString(), "RIGHT");
        Assertions.assertEquals(keySetting.getDownKeyString(), "DOWN");
        Assertions.assertEquals(keySetting.getDropKeyString(), "SPACE");
        Assertions.assertEquals(keySetting.getRotateKeyString(), "UP");

        // 키 설정을 A, B, C, D, E 로 변경
        keySetting.setLeftKeyValue(65);
        keySetting.setRightKeyValue(66);
        keySetting.setDownKeyValue(67);
        keySetting.setDropKeyValue(68);
        keySetting.setRotateKeyValue(69);

        // 변경한 키 설정이 제대로 들어가는지 테스트
        Assertions.assertEquals(keySetting.getLeftKeyString(), "A");
        Assertions.assertEquals(keySetting.getLeftKeyValue(), 65);
        Assertions.assertEquals(keySetting.getRightKeyString(), "B");
        Assertions.assertEquals(keySetting.getRightKeyValue(), 66);
        Assertions.assertEquals(keySetting.getDownKeyString(), "C");
        Assertions.assertEquals(keySetting.getDownKeyValue(), 67);
        Assertions.assertEquals(keySetting.getDropKeyString(), "D");
        Assertions.assertEquals(keySetting.getDropKeyValue(), 68);
        Assertions.assertEquals(keySetting.getRotateKeyString(), "E");
        Assertions.assertEquals(keySetting.getRotateKeyValue(), 69);

    }

    // keyString 을 반환하는 메소드가 제대로 동작하는지 테스트
    @Test
    public void getKeyStringTest() {
        Settings settings = Settings.getInstance();
        Settings.KeySetting keySetting = settings.getKeySetting();

        keySetting.setLeftKeyValue(8);
        Assertions.assertEquals(keySetting.getKeyString(8), "BACKSPACE");
        keySetting.setLeftKeyValue(9);
        Assertions.assertEquals(keySetting.getKeyString(9), "TAB");
        keySetting.setLeftKeyValue(16);
        Assertions.assertEquals(keySetting.getKeyString(16), "SHIFT");
        keySetting.setLeftKeyValue(17);
        Assertions.assertEquals(keySetting.getKeyString(17), "CONTROL");
        keySetting.setLeftKeyValue(18);
        Assertions.assertEquals(keySetting.getKeyString(18), "ALT");
        keySetting.setLeftKeyValue(19);
        Assertions.assertEquals(keySetting.getKeyString(19), "PAUSE/BREAK");
        keySetting.setLeftKeyValue(20);
        Assertions.assertEquals(keySetting.getKeyString(20), "CAPSLOCK");
        keySetting.setLeftKeyValue(32);
        Assertions.assertEquals(keySetting.getKeyString(32), "SPACE");
        keySetting.setLeftKeyValue(33);
        Assertions.assertEquals(keySetting.getKeyString(33), "PAGE_UP");
        keySetting.setLeftKeyValue(34);
        Assertions.assertEquals(keySetting.getKeyString(34), "PAGE_DOWN");
        keySetting.setLeftKeyValue(35);
        Assertions.assertEquals(keySetting.getKeyString(35), "END");
        keySetting.setLeftKeyValue(36);
        Assertions.assertEquals(keySetting.getKeyString(36), "HOME");
        keySetting.setLeftKeyValue(37);
        Assertions.assertEquals(keySetting.getKeyString(37), "LEFT");
        keySetting.setLeftKeyValue(38);
        Assertions.assertEquals(keySetting.getKeyString(38), "UP");
        keySetting.setLeftKeyValue(39);
        Assertions.assertEquals(keySetting.getKeyString(39), "RIGHT");
        keySetting.setLeftKeyValue(40);
        Assertions.assertEquals(keySetting.getKeyString(40), "DOWN");
        keySetting.setLeftKeyValue(45);
        Assertions.assertEquals(keySetting.getKeyString(45), "INSERT");
        keySetting.setLeftKeyValue(46);
        Assertions.assertEquals(keySetting.getKeyString(46), "DELETE");
        keySetting.setLeftKeyValue(96);
        Assertions.assertEquals(keySetting.getKeyString(96), "NUMPAD0");
        keySetting.setLeftKeyValue(97);
        Assertions.assertEquals(keySetting.getKeyString(97), "NUMPAD1");
        keySetting.setLeftKeyValue(98);
        Assertions.assertEquals(keySetting.getKeyString(98), "NUMPAD2");
        keySetting.setLeftKeyValue(99);
        Assertions.assertEquals(keySetting.getKeyString(99), "NUMPAD3");
        keySetting.setLeftKeyValue(100);
        Assertions.assertEquals(keySetting.getKeyString(100), "NUMPAD4");
        keySetting.setLeftKeyValue(101);
        Assertions.assertEquals(keySetting.getKeyString(101), "NUMPAD5");
        keySetting.setLeftKeyValue(102);
        Assertions.assertEquals(keySetting.getKeyString(102), "NUMPAD6");
        keySetting.setLeftKeyValue(103);
        Assertions.assertEquals(keySetting.getKeyString(103), "NUMPAD7");
        keySetting.setLeftKeyValue(104);
        Assertions.assertEquals(keySetting.getKeyString(104), "NUMPAD8");
        keySetting.setLeftKeyValue(105);
        Assertions.assertEquals(keySetting.getKeyString(105), "NUMPAD9");
        keySetting.setLeftKeyValue(106);
        Assertions.assertEquals(keySetting.getKeyString(106), "NUMPAD*");
        keySetting.setLeftKeyValue(107);
        Assertions.assertEquals(keySetting.getKeyString(107), "NUMPAD+");
        keySetting.setLeftKeyValue(109);
        Assertions.assertEquals(keySetting.getKeyString(109), "NUMPAD-");
        keySetting.setLeftKeyValue(110);
        Assertions.assertEquals(keySetting.getKeyString(110), "NUMPAD.");
        keySetting.setLeftKeyValue(111);
        Assertions.assertEquals(keySetting.getKeyString(111), "NUMPAD/");
        keySetting.setLeftKeyValue(186);
        Assertions.assertEquals(keySetting.getKeyString(186), ";");
        keySetting.setLeftKeyValue(187);
        Assertions.assertEquals(keySetting.getKeyString(187), "=");
        keySetting.setLeftKeyValue(188);
        Assertions.assertEquals(keySetting.getKeyString(188), ",");
        keySetting.setLeftKeyValue(189);
        Assertions.assertEquals(keySetting.getKeyString(189), "-");
        keySetting.setLeftKeyValue(190);
        Assertions.assertEquals(keySetting.getKeyString(190), ".");
        keySetting.setLeftKeyValue(191);
        Assertions.assertEquals(keySetting.getKeyString(191), "/");
        keySetting.setLeftKeyValue(192);
        Assertions.assertEquals(keySetting.getKeyString(192), "`");
        keySetting.setLeftKeyValue(219);
        Assertions.assertEquals(keySetting.getKeyString(219), "[");
        keySetting.setLeftKeyValue(220);
        Assertions.assertEquals(keySetting.getKeyString(220), "\\");
        keySetting.setLeftKeyValue(221);
        Assertions.assertEquals(keySetting.getKeyString(221), "]");
        keySetting.setLeftKeyValue(222);
        Assertions.assertEquals(keySetting.getKeyString(222), "'");
        keySetting.setLeftKeyValue(999);
        Assertions.assertEquals(keySetting.getKeyString(999), null);
    }

    /**
     * ColorSetting 테스트
     */

    // 테트로미노 타입 별로 색상이 제대로 설정되는지 테스트
    @Test
    public void colorSettingValueByTetrominoTypeTest() {
        Settings settings = Settings.getInstance();
        Settings.ColorSetting colorSetting = settings.getColorSetting();

        // 기본 색상 설정이 제대로 들어가 있는지 테스트 (offset = 0 일 때)
        Assertions.assertEquals(colorSetting.getColorOfTetrominoType(1), Color.web("87CEEB")); // I
        Assertions.assertEquals(colorSetting.getColorOfTetrominoType(2), Color.web("0000FF")); // J
        Assertions.assertEquals(colorSetting.getColorOfTetrominoType(3), Color.web("FFA500")); // L
        Assertions.assertEquals(colorSetting.getColorOfTetrominoType(4), Color.web("FFFF00")); // O
        Assertions.assertEquals(colorSetting.getColorOfTetrominoType(5), Color.web("90EE90")); // S
        Assertions.assertEquals(colorSetting.getColorOfTetrominoType(6), Color.web("800080")); // T
        Assertions.assertEquals(colorSetting.getColorOfTetrominoType(7), Color.web("FF0000")); // Z
    }

    // 색맹 모드 offset 이 제대로 바뀌는지 테스트
    @Test
    public void colorSettingOffsetChangingTest() {
        Settings settings = Settings.getInstance();
        Settings.ColorSetting colorSetting = settings.getColorSetting();

        Assertions.assertEquals(colorSetting.getColorOffset(), 0);
        colorSetting.roundColorSetting();
        Assertions.assertEquals(colorSetting.getColorOffset(), 1);
        colorSetting.roundColorSetting();
        Assertions.assertEquals(colorSetting.getColorOffset(), 2);
        colorSetting.roundColorSetting();
        Assertions.assertEquals(colorSetting.getColorOffset(), 0);
    }
}
