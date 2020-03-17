package com.uptc.prg.maze.view;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Fonts {


    public static Font getFont(String source, float size) {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, new File(source)).deriveFont(Font.PLAIN, size);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
