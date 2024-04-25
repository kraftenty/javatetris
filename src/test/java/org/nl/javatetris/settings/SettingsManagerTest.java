package org.nl.javatetris.settings;

import org.junit.jupiter.api.Test;

public class SettingsManagerTest {

    @Test
    public void testSaveSettings() {
        SettingsManager.saveSettings();
    }

    @Test
    public void testLoadSettings() {
        SettingsManager.loadSettings();
    }
}
