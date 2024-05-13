package org.nl.javatetris.config.manager;

import javafx.scene.text.Font;

public class FontManager {

    private static Font squareFont;
    private static Font topshowFont;

    // 클래스가 로드될 때 폰트를 로드합니다.
    static {
        load();
    }

    private static void load() {
        squareFont = Font.loadFont(FontManager.class.getResourceAsStream("/fonts/square.ttf"), 14);
        if (squareFont == null) {
            System.err.println("Failed to load Square font. Using default font instead.");
            squareFont = new Font(14); // 기본 폰트 사용
        }

        topshowFont = Font.loadFont(FontManager.class.getResourceAsStream("/fonts/topshow.otf"), 14);
        if (topshowFont == null) {
            System.err.println("Failed to load Topshow font. Using default font instead.");
            topshowFont = new Font(14); // 기본 폰트 사용
        }
    }

    public static Font getSquareFont(double size) {
        return Font.font(squareFont.getName(), size);
    }

    public static Font getTopshowFont(double size) {
        return Font.font(topshowFont.getName(), size);
    }
}
