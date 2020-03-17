package com.uptc.prg.maze.view;

import com.uptc.prg.maze.controller.UIController;
import com.uptc.prg.maze.view.panels.WorkspacePanel;
import com.uptc.prg.maze.view.panels.MenuPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.regex.Pattern;

public class MazeSolverFrame extends JFrame {
    private static final Pattern ACCEPTED_FILES = Pattern.compile(".*\\.(jpg|png)$");
    private MenuPanel mMenuPanel;
    private WorkspacePanel mWorkspacePanel;

    private BufferedImage mMazeImage;

    public MazeSolverFrame() {
        this.initialize();
    }

    public WorkspacePanel getWorkspacePanel() {
        return this.mWorkspacePanel;
    }

    /*====================================== PRIVATE METHODS =====================================*/

    private void initialize() {
        MazeSolverFrame.setDefaultSystemLookAndFeel();
        this.setPreferredSize(UIConstants.MAIN_FRAME_DEFAULT_DIMENSION);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.addMenu();
        this.addImagePanel();
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void addMenu() {
        ActionListener uploadPhotoListener = e -> this.uploadPhoto();
        ActionListener convertPhotoListener = e -> this.convertPhoto();
        this.mMenuPanel = new MenuPanel(
                uploadPhotoListener,
                convertPhotoListener
        );
        this.add(this.mMenuPanel, BorderLayout.LINE_START);
    }

    private void addImagePanel() {
        this.mWorkspacePanel = new WorkspacePanel();
        this.add(this.mWorkspacePanel, BorderLayout.CENTER);
    }

    private void uploadPhoto() {
        FileDialog dialog = new FileDialog((Dialog) null, UIStrings.getString("UPLOAD_PHOTO"));
        dialog.setMode(FileDialog.LOAD);
        dialog.setMultipleMode(false);
        dialog.setVisible(true);
        if (dialog.getFile() != null) {
            this.sendImageToImagePanel(dialog.getFiles()[0]);
        }
    }

    private void convertPhoto() {
        if (this.mMazeImage != null) {
            BufferedImage convertedPhoto;
            UIController controller = UIController.getInstance();
            convertedPhoto = controller.convertImageFromUserSelection(this.mMazeImage);
            this.mWorkspacePanel.setImage(convertedPhoto);
            this.mWorkspacePanel.activateWorkspace();
        } else {
            // TODO: Refactor the strings
            JOptionPane.showMessageDialog(
                    this,
                    UIStrings.getString("ERROR_IMAGE_NOT_FOUND"),
                    UIStrings.getString("ERROR"), JOptionPane.ERROR_MESSAGE);
        }
    }

    private void sendImageToImagePanel(File image) {
        if (MazeSolverFrame.ACCEPTED_FILES.matcher(image.getName().toLowerCase(Locale.getDefault())).matches()) {
            try {
                this.mMazeImage = ImageIO.read(image);
                this.mWorkspacePanel.setImage(this.mMazeImage);
                this.mWorkspacePanel.activateWorkspace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // TODO: Refactor the strings
            JOptionPane.showMessageDialog(
                    this,
                    UIStrings.getString("ERROR_FILE_EXTENSION"),
                    UIStrings.getString("ERROR"), JOptionPane.ERROR_MESSAGE);
        }
    }

    /*================================== PRIVATE STATIC METHODS ==================================*/

    private static void setDefaultSystemLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
}
