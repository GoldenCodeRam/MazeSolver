package com.uptc.prg.maze.view.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class WorkspacePanel extends JPanel {
    private ImagePanel mMazeImage;
    private ScalePanel mScalePanel;
    private UtilityPanel mUtilityPanel;
    private StatusBarPanel mStatusBar;

    public WorkspacePanel() {
        this.initializeUI();
        this.initialize();
    }

    public final void setImage(BufferedImage image) {
        this.mMazeImage.setImage(image);
        this.revalidate();
        this.repaint();
    }

    public final void activateWorkspace() {
        this.mMazeImage.activatePopupMenu();
        this.mUtilityPanel.activate();
    }

    public final ImagePanel getImagePanel() {
        return this.mMazeImage;
    }

    public final StatusBarPanel getStatusBar() {
        return this.mStatusBar;
    }

    /*====================================== PRIVATE METHODS =====================================*/

    private void initializeUI() {
        this.mMazeImage = new ImagePanel();
        this.mScalePanel = new ScalePanel(this.mMazeImage);
        this.mUtilityPanel = new UtilityPanel(this.mMazeImage);
        this.mStatusBar = new StatusBarPanel();
    }

    private void initialize() {
        JScrollPane scrollPane = new JScrollPane(this.mMazeImage);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(16);

        this.setLayout(new BorderLayout());
        this.setBackground(Color.GRAY);
        this.add(this.mScalePanel, BorderLayout.PAGE_START);
        this.add(this.mStatusBar, BorderLayout.PAGE_END);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(this.mUtilityPanel, BorderLayout.LINE_END);
    }
}
