package com.uptc.prg.maze.view.panels;

import com.uptc.prg.maze.view.UIStrings;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {
    private JLabel mTitle;

    private JButton mUploadPhotoButton;
    private JButton mConvertPhotoButton;

    private final ActionListener mUploadPhotoListener;
    private final ActionListener mConvertPhotoListener;

    public MenuPanel(ActionListener uploadPhotoListener,
                     ActionListener convertPhotoListener) {
        this.mUploadPhotoListener = uploadPhotoListener;
        this.mConvertPhotoListener = convertPhotoListener;
        this.initialize();
    }

    private void initialize() {
        this.setLayout(new GridBagLayout());
        this.setBorder(new EmptyBorder(20, 20, 20, 20));
        this.initializeUIElements();

        Panel container = new Panel();
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
        container.add(this.mTitle);
        container.add(Box.createVerticalStrut(20));
        container.add(this.mUploadPhotoButton);
        container.add(this.mConvertPhotoButton);
        this.add(container, new GridBagConstraints());
    }

    private void initializeUIElements() {
        this.mTitle = new JLabel(UIStrings.getString("ALGORITHM_PROGRAM"));
        this.mTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.mUploadPhotoButton = new JButton(UIStrings.getString("UPLOAD_PHOTO"));
        this.mUploadPhotoButton.addActionListener(this.mUploadPhotoListener);
        this.mUploadPhotoButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.mConvertPhotoButton = new JButton(UIStrings.getString("CONVERT_PHOTO"));
        this.mConvertPhotoButton.addActionListener(this.mConvertPhotoListener);
        this.mConvertPhotoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
}
