package org.nl.javatetris.config;

import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.Objects;

public class BackgroundManager {

    private static Background mainBackground;
    private static Background playBackground;

    // 클래스가 로드될 때 배경을 로드합니다.
    static {
        load();
    }

    private static void load() {
        mainBackground = new Background(
                new BackgroundImage(new Image(Objects.requireNonNull(BackgroundManager.class.getResourceAsStream("/images/main.png"))),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true))
        );

        playBackground = new Background(
                new BackgroundImage(new Image(Objects.requireNonNull(BackgroundManager.class.getResourceAsStream("/images/play.jpg"))),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true))
        );
    }

    public static Background getMainBackground() {
        return mainBackground;
    }

    public static Background getPlayBackground() {
        return playBackground;
    }


}
