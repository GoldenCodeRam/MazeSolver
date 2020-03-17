package com.uptc.prg.maze.view;

import java.util.Locale;
import java.util.ResourceBundle;

public enum UIStrings {
    ;

    public static String getString(String key) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("config/UIStrings");
        return resourceBundle.getString(key);
    }
}
