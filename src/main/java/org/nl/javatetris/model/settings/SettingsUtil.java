package org.nl.javatetris.model.settings;


import java.io.*;

import static org.nl.javatetris.model.ModelConst.*;

public class SettingsUtil {

    public static void saveSettings() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SETTINGS_FILE_NAME))) {
            oos.writeObject(Settings.getInstance());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadSettings() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SETTINGS_FILE_NAME))) {
            Settings.setInstance((Settings) ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            Settings.ready();
            saveSettings();
        }
    }
}
