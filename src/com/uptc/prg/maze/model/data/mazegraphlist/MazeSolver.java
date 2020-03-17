package com.uptc.prg.maze.model.data.mazegraphlist;

import com.uptc.prg.maze.controller.UIController;
import com.uptc.prg.maze.model.data.Queue;
import com.uptc.prg.maze.model.data.Stack;
import com.uptc.prg.maze.model.data.graphlist.EdgeType;
import com.uptc.prg.maze.model.data.graphlist.list.LinkedList;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.HashSet;
import java.util.PriorityQueue;

public class MazeSolver {
    private static final long DEFAULT_GENERATION_TIME_DELAY = 1000L;

    private final MazeGraph mGraph;

    private int mCircleSize;
    private long mGenerationTimeDelay;
    private BufferedImage mMazeImage = null;
    private BufferedImage mGraphImage = null;


    public MazeSolver() {
        this.mGraph = new MazeGraph();
        this.mCircleSize = 1;
        this.mGenerationTimeDelay = MazeSolver.DEFAULT_GENERATION_TIME_DELAY;
    }

    /*====================================== PUBLIC METHODS ======================================*/

    public final void generateDelayedGraphFromImage(BufferedImage mazeImage, Point point, int circleSize) {
        this.mMazeImage = mazeImage;
        this.mCircleSize = circleSize;
        this.mGraphImage = this.generateBufferedImageFromMazeImage();
        MazeVertex pointToGraph = new MazeVertex(point);

        if (this.isValidVertex(pointToGraph)) {
            if (!this.mGraph.hasVertex(pointToGraph)) {
                pointToGraph.checkVertex();
                this.mGraph.addVertex(pointToGraph);
            }
            this.drawCheckedVertexOnImageGraph(pointToGraph);
            this.checkVertexPossibleDirections(pointToGraph);
        }

        MazeVertex uncheckedVertex = this.mGraph.getAnyUncheckedVertex();
        while (uncheckedVertex != null) {
            uncheckedVertex.checkVertex();
            this.drawCheckedVertexOnImageGraph(uncheckedVertex);
            this.checkVertexPossibleDirections(uncheckedVertex);
            uncheckedVertex = this.mGraph.getAnyUncheckedVertex();
            this.showGraphImageWithDelay();
        }
    }

    public final void searchPathWithDijkstra(Point initialPoint, Point targetPoint) {
        Collection<MazeVertex> uncheckedVertices = new HashSet<>(this.mGraph.size());
        Collection<MazeVertex> checkedVertices = new HashSet<>(this.mGraph.size());
        this.mGraphImage = this.generateBufferedImageFromMazeImage();
        this.mGraph.resetVerticesDistances();
        MazeVertex startVertex = this.mGraph.searchApproximateMazeVertex(
                new MazeVertex(initialPoint),
                this.mCircleSize
        );
        MazeVertex targetVertex = this.mGraph.searchApproximateMazeVertex(
                new MazeVertex(targetPoint),
                this.mCircleSize
        );
        if (startVertex != null && targetVertex != null) {
            MazeVertex currentVertex;
            int edgeWeight;
            MazeVertex adjacentVertex;
            startVertex.setVertexDistance(0);
            uncheckedVertices.add(startVertex);
            this.drawUncheckedVertexOnImageGraph(startVertex);
            while (!uncheckedVertices.isEmpty()) {
                currentVertex = this.getLowestDistanceVertex(uncheckedVertices);
                uncheckedVertices.remove(currentVertex);
                LinkedList.Node<MazeEdge> currentNode = currentVertex.getHead();
                while (currentNode != null) {
                    adjacentVertex = this.mGraph.searchMazeVertex(currentNode.getData().getTargetVertex());
                    edgeWeight = currentNode.getData().getEdgeWeight();
                    if (!checkedVertices.contains(adjacentVertex)) {
                        this.calculateMinimumDistance(adjacentVertex, edgeWeight, currentVertex);
                        uncheckedVertices.add(adjacentVertex);
                        this.drawUncheckedVertexOnImageGraph(adjacentVertex);
                    }
                    currentNode = currentNode.getNext();
                    this.showGraphImageWithDelay();
                }
                if (currentVertex == targetVertex) {
                    break;
                }
                checkedVertices.add(currentVertex);
                this.drawCheckedVertexOnImageGraph(currentVertex);
                this.showGraphImageWithDelay();
            }
            this.drawPathFromStartToTarget(targetVertex);
        }
    }

    public final void searchPathWithBFS(Point initialPoint, Point targetPoint) {
        this.mGraph.uncheckAllVertices();
        Queue<MazeVertex> queue = new Queue<>();
        this.mGraphImage = this.generateBufferedImageFromMazeImage();
        MazeVertex startVertex = this.mGraph.searchApproximateMazeVertex(
                new MazeVertex(initialPoint),
                this.mCircleSize
        );
        MazeVertex targetVertex = this.mGraph.searchApproximateMazeVertex(
                new MazeVertex(targetPoint),
                this.mCircleSize
        );
        if (startVertex != null && targetVertex != null) {
            MazeVertex searchedVertex;
            MazeVertex currentVertex;
            LinkedList.Node<MazeEdge> currentNode;
            queue.put(startVertex);
            this.drawUncheckedVertexOnImageGraph(startVertex);
            this.showGraphImageWithDelay();
            while (!queue.isEmpty()) {
                currentVertex = queue.get();
                currentNode = currentVertex.getHead();
                this.drawCheckedVertexOnImageGraph(currentVertex);
                this.showGraphImageWithDelay();
                while (currentNode != null) {
                    searchedVertex = this.mGraph.searchMazeVertex(currentNode.getData().getTargetVertex());
                    if (searchedVertex != null && !searchedVertex.isChecked()) {
                        searchedVertex.checkVertex();
                        searchedVertex.setPreviousShortestVertex(currentVertex);
                        queue.put(searchedVertex);
                        this.drawUncheckedVertexOnImageGraph(searchedVertex);
                        this.showGraphImageWithDelay();
                    }
                    currentNode = currentNode.getNext();
                }
                if (currentVertex == targetVertex) {
                    break;
                }
            }
            this.drawPathFromStartToTarget(targetVertex);
        }
        this.mGraph.checkAllVertices();
    }

    public final void searchPathWithDFS(Point initialPoint, Point targetPoint) {
        this.mGraph.uncheckAllVertices();
        Stack<MazeVertex> stack = new Stack<>();
        this.mGraphImage = this.generateBufferedImageFromMazeImage();
        MazeVertex startVertex = this.mGraph.searchApproximateMazeVertex(
                new MazeVertex(initialPoint),
                this.mCircleSize
        );
        MazeVertex targetVertex = this.mGraph.searchApproximateMazeVertex(
                new MazeVertex(targetPoint),
                this.mCircleSize
        );
        if (startVertex != null && targetVertex != null) {
            MazeVertex searchedVertex;
            MazeVertex currentVertex;
            LinkedList.Node<MazeEdge> currentNode;
            stack.push(startVertex);
            this.drawUncheckedVertexOnImageGraph(startVertex);
            this.showGraphImageWithDelay();
            while (!stack.isEmpty()) {
                currentVertex = stack.pop();
                currentNode = currentVertex.getHead();
                this.drawCheckedVertexOnImageGraph(currentVertex);
                this.showGraphImageWithDelay();
                while (currentNode != null) {
                    searchedVertex = this.mGraph.searchMazeVertex(currentNode.getData().getTargetVertex());
                    if (searchedVertex != null && !searchedVertex.isChecked()) {
                        searchedVertex.checkVertex();
                        searchedVertex.setPreviousShortestVertex(currentVertex);
                        stack.push(searchedVertex);
                        this.drawUncheckedVertexOnImageGraph(searchedVertex);
                        this.showGraphImageWithDelay();
                    }
                    currentNode = currentNode.getNext();
                }
                if (currentVertex == targetVertex) {
                    break;
                }
            }
            this.drawPathFromStartToTarget(targetVertex);
        }
        this.mGraph.checkAllVertices();
    }

    public final void searchPathWithPrim(Point initialPoint, Point targetPoint) {
        Collection<MazeVertex> uncheckedVertices = new HashSet<>(this.mGraph.size());
        Collection<MazeVertex> checkedVertices = new HashSet<>(this.mGraph.size());
        this.mGraphImage = this.generateBufferedImageFromMazeImage();
        this.mGraph.resetVerticesDistances();
        MazeVertex startVertex = this.mGraph.searchApproximateMazeVertex(
                new MazeVertex(initialPoint),
                this.mCircleSize
        );
        MazeVertex targetVertex = this.mGraph.searchApproximateMazeVertex(
                new MazeVertex(targetPoint),
                this.mCircleSize
        );
        if (startVertex != null && targetVertex != null) {
            MazeVertex currentVertex;
            MazeVertex adjacentVertex;
            startVertex.setVertexDistance(0);
            uncheckedVertices.add(startVertex);
            this.drawUncheckedVertexOnImageGraph(startVertex);
            while (!uncheckedVertices.isEmpty()) {
                currentVertex = this.getLowestDistanceVertex(uncheckedVertices);
                uncheckedVertices.remove(currentVertex);
                LinkedList.Node<MazeEdge> currentNode = currentVertex.getHead();
                while (currentNode != null) {
                    adjacentVertex = this.mGraph.searchMazeVertex(currentNode.getData().getTargetVertex());
                    if (!checkedVertices.contains(adjacentVertex)) {
                        adjacentVertex.setPreviousShortestVertex(currentVertex);
                        adjacentVertex.setVertexDistance(currentVertex.getVertexDistance());
                        uncheckedVertices.add(adjacentVertex);
                        this.drawUncheckedVertexOnImageGraph(adjacentVertex);
                    }
                    currentNode = currentNode.getNext();
                    this.showGraphImageWithDelay();
                }
                if (currentVertex == targetVertex) {
                    break;
                }
                checkedVertices.add(currentVertex);
                this.drawCheckedVertexOnImageGraph(currentVertex);
                this.showGraphImageWithDelay();
            }
            this.drawPathFromStartToTarget(targetVertex);
        }
    }

    /*===================================== SETTERS / GETTERS ====================================*/

    public final void setGenerationTimeDelay(long timeDelay) {
        this.mGenerationTimeDelay = timeDelay;
    }

    /*====================================== PRIVATE METHODS =====================================*/

    private MazeVertex getLowestDistanceVertex(Iterable<? extends MazeVertex> uncheckedVertices) {
        int vertexDistance;

        MazeVertex lowestDistanceVertex = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (MazeVertex vertex : uncheckedVertices) {
            vertexDistance = vertex.getVertexDistance();
            if (vertex.getVertexDistance() < lowestDistance) {
                lowestDistance = vertexDistance;
                lowestDistanceVertex = vertex;
            }
        }
        return lowestDistanceVertex;
    }

    private void calculateMinimumDistance(
            MazeVertex evaluationVertex,
            int edgeWeight,
            MazeVertex sourceVertex) {
        int sourceDistance = sourceVertex.getVertexDistance();
        int vertexDistance = sourceDistance + edgeWeight;
        if (vertexDistance < evaluationVertex.getVertexDistance()) {
            evaluationVertex.setVertexDistance(vertexDistance);
            evaluationVertex.setPreviousShortestVertex(sourceVertex);
        }
    }

    private BufferedImage generateBufferedImageFromMazeImage() {
        return new BufferedImage(
                this.mMazeImage.getWidth(),
                this.mMazeImage.getHeight(),
                BufferedImage.TYPE_INT_ARGB);
    }

    private void drawPathFromStartToTarget(MazeVertex targetVertex) {
        this.drawPathVertexOnImageGraph(targetVertex);
        this.showGraphImageWithDelay();
        MazeVertex currentVertex = targetVertex.getPreviousShortestVertex();
        while (currentVertex != null) {
            this.drawPathVertexOnImageGraph(currentVertex);
            this.showGraphImageWithDelay();
            currentVertex = currentVertex.getPreviousShortestVertex();
        }
    }

    private void showGraphImageWithDelay() {
        UIController.getInstance().showGeneratedGraph(this.mGraphImage);
        try {
            Thread.sleep(this.mGenerationTimeDelay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if the {@link MazeVertex} position can be drawn and selected from the
     * {@link BufferedImage} graph image. The check is made by a threshold for the amount of white
     * pixels contained on square formed by the circle size given by the user.
     *
     * @param mazeVertex position to check on the graph image.
     * @return If the {@link MazeVertex} is valid, or contained on the image possible path or not.
     */
    private boolean isValidVertex(MazeVertex mazeVertex) {
        //TODO: look if is important to move this threshold from here.
        int threshold = (int) ((this.mCircleSize * this.mCircleSize) * 0.8d);
        int whitePixelCount = 0;
        int pointX = mazeVertex.getVertexPosition().x;
        int pointY = mazeVertex.getVertexPosition().y;
        for (int i = pointX - this.mCircleSize / 2;
             i < pointX + this.mCircleSize / 2 && i < this.mMazeImage.getWidth() && i >= 0;
             i++) {
            for (int e = pointY - this.mCircleSize / 2;
                 e < pointY + this.mCircleSize / 2 && e < this.mMazeImage.getHeight() && e >= 0;
                 e++) {
                if (new Color(this.mMazeImage.getRGB(i, e)).equals(Color.WHITE)) {
                    whitePixelCount++;
                }
                if (whitePixelCount > threshold) {
                    return true;
                }
            }
        }
        return false;
    }

    private void checkVertexPossibleDirections(MazeVertex mazeVertex) {
        this.checkLeftFromVertex(mazeVertex);
        this.checkRightFromVertex(mazeVertex);
        this.checkUpFromVertex(mazeVertex);
        this.checkDownFromVertex(mazeVertex);
    }

    private void checkLeftFromVertex(MazeVertex mazeVertex) {
        Point vertexPointLeft = new Point(
                mazeVertex.getVertexPosition().x - this.mCircleSize,
                mazeVertex.getVertexPosition().y
        );
        MazeVertex vertexLeft = new MazeVertex(vertexPointLeft);
        this.addVertexToGraph(mazeVertex, vertexLeft);
    }

    private void checkRightFromVertex(MazeVertex mazeVertex) {
        Point vertexPointRight = new Point(
                mazeVertex.getVertexPosition().x + this.mCircleSize,
                mazeVertex.getVertexPosition().y
        );
        MazeVertex vertexRight = new MazeVertex(vertexPointRight);
        this.addVertexToGraph(mazeVertex, vertexRight);
    }

    private void checkUpFromVertex(MazeVertex mazeVertex) {
        Point vertexPointUp = new Point(
                mazeVertex.getVertexPosition().x,
                mazeVertex.getVertexPosition().y - this.mCircleSize
        );
        MazeVertex vertexUp = new MazeVertex(vertexPointUp);
        this.addVertexToGraph(mazeVertex, vertexUp);
    }

    private void checkDownFromVertex(MazeVertex mazeVertex) {
        Point vertexPointDown = new Point(
                mazeVertex.getVertexPosition().x,
                mazeVertex.getVertexPosition().y + this.mCircleSize
        );
        MazeVertex vertexDown = new MazeVertex(vertexPointDown);
        this.addVertexToGraph(mazeVertex, vertexDown);
    }

    private void addVertexToGraph(MazeVertex mainVertex,
                                  MazeVertex edgeVertex) {
        if (this.isValidVertex(edgeVertex)) {
            if (!this.mGraph.hasVertex(edgeVertex)) {
                mainVertex.addEdge(new MazeEdge(
                        edgeVertex,
                        this.mCircleSize,
                        EdgeType.UNDIRECTED
                ));
                this.mGraph.addVertex(edgeVertex);
                this.drawUncheckedVertexOnImageGraph(edgeVertex);
            }
        }
    }

    private void drawPathVertexOnImageGraph(MazeVertex mazeVertex) {
        Graphics graphicsGraphImage = this.mGraphImage.getGraphics();
        graphicsGraphImage.setColor(Color.ORANGE);
        graphicsGraphImage.fillOval(
                mazeVertex.getVertexPosition().x - this.mCircleSize / 2,
                mazeVertex.getVertexPosition().y - this.mCircleSize / 2,
                this.mCircleSize,
                this.mCircleSize
        );
        graphicsGraphImage.dispose();
    }

    private void drawCheckedVertexOnImageGraph(MazeVertex mazeVertex) {
        Graphics graphicsGraphImage = this.mGraphImage.getGraphics();
        graphicsGraphImage.setColor(Color.GREEN);
        graphicsGraphImage.fillOval(
                mazeVertex.getVertexPosition().x - this.mCircleSize / 2,
                mazeVertex.getVertexPosition().y - this.mCircleSize / 2,
                this.mCircleSize,
                this.mCircleSize
        );
        graphicsGraphImage.dispose();
    }

    private void drawUncheckedVertexOnImageGraph(MazeVertex mazeVertex) {
        Graphics graphicsGraphImage = this.mGraphImage.getGraphics();
        graphicsGraphImage.setColor(Color.BLUE);
        graphicsGraphImage.drawOval(
                mazeVertex.getVertexPosition().x - this.mCircleSize / 2,
                mazeVertex.getVertexPosition().y - this.mCircleSize / 2,
                this.mCircleSize,
                this.mCircleSize
        );
        graphicsGraphImage.dispose();
    }
}
