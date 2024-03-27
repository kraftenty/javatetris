package org.nl.javatetris.model.settings;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Settings {
    private int screen;
    private KeySetting keySetting;
    private int colorBlind;



    public Settings() {
        this.keySetting = new KeySetting();
    }

    public int getScreen() {
        return screen;
    }

    public void setScreen(int screen) {
        this.screen = screen;
    }

    public KeySetting getKeySetting() {
        return keySetting;
    }

    public void setKeySetting(KeySetting keySetting) {
        this.keySetting = keySetting;
    }

    // Load settings from JSON file
    public static Settings loadSettings(String filePath) throws IOException {
        Settings settings = new Settings();
        try (FileReader reader = new FileReader(filePath)) {
            JSONObject jsonSettings = new JSONObject(new JSONTokener(reader));
            JSONObject jsonSettingsData = jsonSettings.getJSONObject("settings");
            settings.setScreen(jsonSettingsData.getInt("screen"));
            settings.setKeySetting(new KeySetting(jsonSettingsData.getJSONObject("key_mapping")));
        }
        return settings;
    }

    // Save settings to JSON file
    public void saveSettings(String filePath) throws IOException {
        JSONObject jsonSettings = new JSONObject();
        JSONObject jsonSettingsData = new JSONObject();
        jsonSettingsData.put("screen", screen);
        jsonSettingsData.put("key_mapping", keySetting.toJsonObject());
        jsonSettings.put("settings", jsonSettingsData);
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(jsonSettings.toString(4));
        }
    }

    public static class KeySetting {
        private String moveLeft;
        private String moveRight;
        private String rotate;
        private String softDrop;
        private String hardDrop;

        public KeySetting() {
        }

        public KeySetting(JSONObject jsonObject) {
            this.moveLeft = jsonObject.getString("move_left");
            this.moveRight = jsonObject.getString("move_right");
            this.rotate = jsonObject.getString("rotate");
            this.softDrop = jsonObject.getString("soft_drop");
            this.hardDrop = jsonObject.getString("hard_drop");
        }

        public String getMoveLeft() {
            return moveLeft;
        }

        public void setMoveLeft(String moveLeft) {
            this.moveLeft = moveLeft;
        }

        public String getMoveRight() {
            return moveRight;
        }

        public void setMoveRight(String moveRight) {
            this.moveRight = moveRight;
        }

        public String getRotate() {
            return rotate;
        }

        public void setRotate(String rotate) {
            this.rotate = rotate;
        }

        public String getSoftDrop() {
            return softDrop;
        }

        public void setSoftDrop(String softDrop) {
            this.softDrop = softDrop;
        }

        public String getHardDrop() {
            return hardDrop;
        }

        public void setHardDrop(String hardDrop) {
            this.hardDrop = hardDrop;
        }

        public JSONObject toJsonObject() {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("move_left", moveLeft);
            jsonObject.put("move_right", moveRight);
            jsonObject.put("rotate", rotate);
            jsonObject.put("soft_drop", softDrop);
            jsonObject.put("hard_drop", hardDrop);
            return jsonObject;
        }
    }
}
