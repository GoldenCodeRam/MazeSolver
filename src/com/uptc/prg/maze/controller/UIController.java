package com.uptc.prg.maze.controller;

import com.uptc.prg.maze.model.data.mazegraphlist.MazeSolver;
import com.uptc.prg.maze.view.MazeSolverFrame;
import com.uptc.prg.maze.resource.ImageConverter;
import com.uptc.prg.maze.view.UIStrings;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * <b>UIController Class</b>
 * <p>
 * Controller for the communication between model and view.
 */
public final class UIController {
    /* Main frame and solver for the graph */
    private MazeSolverFrame mMainFrame;
    private MazeSolver mMazeSolver;

    // region Singleton
    private enum UIControllerInstance {
        ;
        static final UIController mInstance = new UIController();
    }

    public static UIController getInstance() {
        return UIControllerInstance.mInstance;
    }
    // endregion

    private UIController() {
        this.mMainFrame = new MazeSolverFrame();
    }

    /*====================================== PUBLIC METHODS ======================================*/

    /**
     * Converts a {@link BufferedImage} to grayscale, with a certain threshold.
     *
     * @param userImage The {@link BufferedImage} to convert.
     * @return The converted image, to grayscale.
     */
    public BufferedImage convertImageFromUserSelection(BufferedImage userImage) {
        this.showMessageOnStatusBar(UIStrings.getString("CONVERTING_TO_GRAYSCALE"));
        BufferedImage convertedImage = new ImageConverter().convertToGrayScale(userImage);
        this.showMessageOnStatusBar(UIStrings.getString("COMPLETED"));
        return convertedImage;
    }

    /**
     * Generates, with a certain delay, the
     * {@link com.uptc.prg.maze.model.data.mazegraphlist.MazeGraph} from the image. Using an initial
     * point, and a circle size for the image. Then, the graph generated will be stored for later
     * usage.
     *
     * @param image        to generate the graph with.
     * @param initialPoint from where to start the graph on the image.
     * @param circleSize   for the start of the graph and for the measures of the paths.
     */
    public void generateGraphFromImage(BufferedImage image, Point initialPoint, int circleSize) {
        this.mMazeSolver = new MazeSolver();
        new Thread(() -> {
            this.showMessageOnStatusBar(UIStrings.getString("GENERATING_GRAPH"));
            this.mMazeSolver.generateDelayedGraphFromImage(image, initialPoint, circleSize);
            this.showMessageOnStatusBar(UIStrings.getString("COMPLETED"));
        }).start();
    }

    /**
     * Renders the generated image graph, stored on a {@link BufferedImage}, on the image panel for
     * the user.
     *
     * @param generatedGraph image to show on the UI for the user.
     */
    public void showGeneratedGraph(BufferedImage generatedGraph) {
        this.mMainFrame.getWorkspacePanel().getImagePanel().setGeneratedGraphImage(generatedGraph);
    }

    public void searchPathWithDijkstra(Point initialPoint, Point finalPoint) {
        new Thread(() -> {
            this.showMessageOnStatusBar(UIStrings.getString("SEARCHING_PATH"));
            this.mMazeSolver.searchPathWithDijkstra(initialPoint, finalPoint);
            this.showMessageOnStatusBar(UIStrings.getString("COMPLETED"));
        }).start();
    }

    public void searchPathWithBFS(Point initialPoint, Point finalPoint) {
        new Thread(() -> {
            this.showMessageOnStatusBar(UIStrings.getString("SEARCHING_PATH"));
            this.mMazeSolver.searchPathWithBFS(initialPoint, finalPoint);
            this.showMessageOnStatusBar(UIStrings.getString("COMPLETED"));
        }).start();
    }

    public void searchPathWithDFS(Point initialPoint, Point finalPoint) {
        new Thread(() -> {
            this.showMessageOnStatusBar(UIStrings.getString("SEARCHING_PATH"));
            this.mMazeSolver.searchPathWithDFS(initialPoint, finalPoint);
            this.showMessageOnStatusBar(UIStrings.getString("COMPLETED"));
        }).start();
    }

    public void searchPathWithPrim(Point initialPoint, Point finalPoint) {
        new Thread(() -> {
            this.showMessageOnStatusBar(UIStrings.getString("SEARCHING_PATH"));
            this.mMazeSolver.searchPathWithPrim(initialPoint, finalPoint);
            this.showMessageOnStatusBar(UIStrings.getString("COMPLETED"));
        }).start();
    }

    /**
     * Sets the generation and rendering delay of the different graph and search methods.
     *
     * @param timeDelay for the generation and rendering methods.
     */
    public void setGenerationTimeDelay(long timeDelay) {
        this.mMazeSolver.setGenerationTimeDelay(timeDelay);
    }

    /*====================================== PRIVATE METHODS =====================================*/

    private void showMessageOnStatusBar(String message) {
        this.mMainFrame.getWorkspacePanel().getStatusBar().setStatusMessage(message);
    }
}
