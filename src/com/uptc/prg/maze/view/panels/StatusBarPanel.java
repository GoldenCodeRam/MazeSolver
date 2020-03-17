package com.uptc.prg.maze.view.panels;

import com.uptc.prg.maze.view.UIStrings;

import javax.swing.*;
import java.awt.*;

public class StatusBarPanel extends JPanel {
    private JLabel mStatus;

    public StatusBarPanel() {
        this.initialize();
        this.initializeStatusBar();
    }

    public final void setStatusMessage(String message) {
        this.mStatus.setText(message);
    }

    /*====================================== PRIVATE METHODS =====================================*/

    private void initializeStatusBar() {
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
        this.mStatus = new JLabel();
        this.mStatus.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.mStatus.setHorizontalAlignment(SwingConstants.CENTER);
        this.mStatus.setHorizontalTextPosition(SwingConstants.CENTER);
        content.add(this.mStatus);
        this.add(content);
    }

    private void initialize() {
        this.setLayout(new GridBagLayout());
    }
}
