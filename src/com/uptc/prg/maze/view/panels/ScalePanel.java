package com.uptc.prg.maze.view.panels;

import com.uptc.prg.maze.view.UIStrings;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ScalePanel extends JPanel {
    private JSlider mScaleFactor;
    private JLabel mTitle;

    private ImagePanel mImagePanel;

    public ScalePanel(ImagePanel imagePanel) {
        this.mImagePanel = imagePanel;

        this.initializeUI();
        this.initialize();
    }

    private void initializeUI() {
        this.mScaleFactor = new JSlider(SwingConstants.HORIZONTAL, 1, 10, 1);
        this.mScaleFactor.setMinorTickSpacing(1);
        this.mScaleFactor.setPaintLabels(true);
        this.mScaleFactor.setPaintTicks(true);
        this.mScaleFactor.addChangeListener(e -> this.setImagePanelScale());

        this.mTitle = new JLabel(UIStrings.getString("SCALE_FACTOR"));
        this.mTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void initialize() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(20, 20, 20, 20));
        this.add(this.mTitle);
        this.add(this.mScaleFactor);
    }

    private void setImagePanelScale() {
        this.mImagePanel.setScale(this.mScaleFactor.getValue());
    }
}
