package com.uptc.prg.maze.view.panels;

import com.uptc.prg.maze.controller.UIController;
import com.uptc.prg.maze.view.UIStrings;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class UtilityPanel extends JPanel {
    private ImagePanel mImage;
    private JPanel mContent;

    private JSpinner mCircleSizeSpinner;

    private JButton mGenerateGraphDelayButton;

    private JCheckBox mShowGeneratedGraph;

    private JSpinner mGenerationTimeSpinner;

    private JButton mSearchPathWithDijkstraButton;
    private JButton mSearchPathWithBFSButton;
    private JButton mSearchPathWithDFSButton;
    private JButton mSearchPathWithPrimButton;

    public UtilityPanel(ImagePanel imagePanel) {
        this.initializeComponents(imagePanel);
        this.initialize();
    }

    public final void activate() {
        this.mCircleSizeSpinner.setEnabled(true);
        this.mGenerateGraphDelayButton.setEnabled(true);
        this.mShowGeneratedGraph.setEnabled(true);
        this.mGenerationTimeSpinner.setEnabled(true);
        this.mSearchPathWithDijkstraButton.setEnabled(true);
        this.mSearchPathWithBFSButton.setEnabled(true);
        this.mSearchPathWithDFSButton.setEnabled(true);
        this.mSearchPathWithPrimButton.setEnabled(true);
    }

    private void initializeComponents(ImagePanel imagePanel) {
        // region CircleSizeSpinner
        SpinnerNumberModel numberModel = new SpinnerNumberModel(1, 1, 1000, 1);
        JLabel circleSizeTitle = new JLabel(UIStrings.getString("CIRCLE_SIZE"));
        circleSizeTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.mCircleSizeSpinner = new JSpinner(numberModel);
        this.mCircleSizeSpinner.addChangeListener(e -> this.changeCircleSize());
        this.mCircleSizeSpinner.setEnabled(false);
        this.mCircleSizeSpinner.setAlignmentX(Component.CENTER_ALIGNMENT);
        // endregion

        // region GenerateGraphWithDelayButton
        this.mGenerateGraphDelayButton = new JButton(UIStrings.getString("GENERATE_WITH_DELAY"));
        this.mGenerateGraphDelayButton.addActionListener(e -> this.generateGraphFromImage());
        this.mGenerateGraphDelayButton.setEnabled(false);
        this.mGenerateGraphDelayButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        // endregion

        // region ShowGraphJPanel
        JPanel showGraphGroup = new JPanel();
        showGraphGroup.setLayout(new BoxLayout(showGraphGroup, BoxLayout.LINE_AXIS));
        showGraphGroup.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel showGraphTitle = new JLabel(UIStrings.getString("SHOW_GRAPH"));
        this.mShowGeneratedGraph = new JCheckBox();
        this.mShowGeneratedGraph.addActionListener(e -> this.showGeneratedGraphImage());
        this.mShowGeneratedGraph.setEnabled(false);
        showGraphGroup.add(showGraphTitle);
        showGraphGroup.add(Box.createHorizontalStrut(10));
        showGraphGroup.add(this.mShowGeneratedGraph);
        // endregion

        // region GenerationTimeSpinner
        JLabel generationTimeTitle = new JLabel(UIStrings.getString("GENERATION_TIME"));
        generationTimeTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        SpinnerNumberModel timeModel = new SpinnerNumberModel(1000, 0, 1000, 10);
        this.mGenerationTimeSpinner = new JSpinner(timeModel);
        this.mGenerationTimeSpinner.addChangeListener(e -> this.changeGenerationTimeDelay());
        this.mGenerationTimeSpinner.setEnabled(false);
        this.mGenerationTimeSpinner.setAlignmentX(Component.CENTER_ALIGNMENT);
        // endregion

        // region SearchPathWithDijkstraButton
        this.mSearchPathWithDijkstraButton = new JButton(UIStrings.getString("SEARCH_WITH_DIJKSTRA"));
        this.mSearchPathWithDijkstraButton.addActionListener(e -> this.searchPathWithDijkstra());
        this.mSearchPathWithDijkstraButton.setEnabled(false);
        this.mSearchPathWithDijkstraButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        // endregion

        // region SearchPathWithBFSButton
        this.mSearchPathWithBFSButton = new JButton(UIStrings.getString("SEARCH_WITH_BFS"));
        this.mSearchPathWithBFSButton.addActionListener(e -> this.searchPathWithBFS());
        this.mSearchPathWithBFSButton.setEnabled(false);
        this.mSearchPathWithBFSButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        // endregion

        // region SearchPathWithDFSButton
        this.mSearchPathWithDFSButton = new JButton(UIStrings.getString("SEARCH_WITH_DFS"));
        this.mSearchPathWithDFSButton.addActionListener(e -> this.searchPathWithDFS());
        this.mSearchPathWithDFSButton.setEnabled(false);
        this.mSearchPathWithDFSButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        // endregion

        // region SearchPathWitPrimButton
        this.mSearchPathWithPrimButton = new JButton(UIStrings.getString("SEARCH_WITH_PRIM"));
        this.mSearchPathWithPrimButton.addActionListener(e -> this.searchPathWithPrim());
        this.mSearchPathWithPrimButton.setEnabled(false);
        this.mSearchPathWithPrimButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        // endregion

        // region AddingValuesToTheContentObject
        this.mContent = new JPanel();
        this.mContent.setLayout(new BoxLayout(this.mContent, BoxLayout.PAGE_AXIS));
        this.mContent.add(circleSizeTitle);
        this.mContent.add(this.mCircleSizeSpinner);
        this.mContent.add(Box.createVerticalStrut(20));
        this.mContent.add(this.mGenerateGraphDelayButton);
        this.mContent.add(Box.createVerticalStrut(20));
        this.mContent.add(showGraphGroup);
        this.mContent.add(Box.createVerticalStrut(20));
        this.mContent.add(generationTimeTitle);
        this.mContent.add(this.mGenerationTimeSpinner);
        this.mContent.add(Box.createVerticalStrut(20));
        this.mContent.add(this.mSearchPathWithDijkstraButton);
        this.mContent.add(this.mSearchPathWithBFSButton);
        this.mContent.add(this.mSearchPathWithDFSButton);
        this.mContent.add(this.mSearchPathWithPrimButton);
        // endregion

        this.mImage = imagePanel;
    }

    private void initialize() {
        this.setLayout(new GridBagLayout());
        this.setBorder(new EmptyBorder(20, 20, 20, 20));
        this.add(this.mContent, new GridBagConstraints());
    }

    private void searchPathWithDijkstra() {
        UIController.getInstance().searchPathWithDijkstra(
                this.mImage.getInitialPoint(),
                this.mImage.getFinalPoint()
        );
    }

    private void searchPathWithBFS() {
        UIController.getInstance().searchPathWithBFS(
            this.mImage.getInitialPoint(),
            this.mImage.getFinalPoint()
        );
    }

    private void searchPathWithDFS() {
        UIController.getInstance().searchPathWithDFS(
                this.mImage.getInitialPoint(),
                this.mImage.getFinalPoint()
        );
    }

    private void searchPathWithPrim() {
        UIController.getInstance().searchPathWithPrim(
                this.mImage.getInitialPoint(),
                this.mImage.getFinalPoint()
        );
    }

    private void changeCircleSize() {
        this.mImage.setCircleSize((Integer) this.mCircleSizeSpinner.getValue());
        this.mImage.revalidate();
        this.mImage.repaint();
    }

    private void generateGraphFromImage() {
        UIController.getInstance().generateGraphFromImage(
                this.mImage.getImage(),
                this.mImage.getInitialPoint(),
                (Integer) this.mCircleSizeSpinner.getValue());
    }

    private void changeGenerationTimeDelay() {
        UIController controller = UIController.getInstance();
        controller.setGenerationTimeDelay(Integer.toUnsignedLong((Integer) this.mGenerationTimeSpinner.getValue()));
    }

    private void showGeneratedGraphImage() {
        if (this.mShowGeneratedGraph.isSelected()) {
            this.mImage.showGeneratedGraphImage();
        } else {
            this.mImage.hideGeneratedGraphImage();
        }
    }
}
