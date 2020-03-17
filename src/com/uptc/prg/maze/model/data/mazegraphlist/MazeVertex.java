package com.uptc.prg.maze.model.data.mazegraphlist;


import com.uptc.prg.maze.model.data.graphlist.list.LinkedList;

import java.awt.*;

public class MazeVertex extends LinkedList<MazeEdge> {
    private final Point mVertexPosition;
    private boolean mIsChecked = false;
    private int mDistance = Integer.MAX_VALUE;
    private MazeVertex mPreviousShortestVertex = null;

    public MazeVertex(Point vertexPosition, MazeEdge firstEdge, int distance) {
        this.mVertexPosition = vertexPosition;
        this.addEdge(firstEdge);
        this.mDistance = distance;
    }

    public MazeVertex(Point vertexPosition, MazeEdge firstEdge) {
        this.mVertexPosition = vertexPosition;
        this.addEdge(firstEdge);
    }

    public MazeVertex(Point vertexPosition) {
        this.mVertexPosition = vertexPosition;
    }

    /*====================================== PUBLIC METHODS ======================================*/

    public final boolean isChecked() {
        return this.mIsChecked;
    }

    public final void checkVertex() {
        this.mIsChecked = true;
    }

    public final void uncheckVertex() {
        this.mIsChecked = false;
    }

    public final void addEdge(MazeEdge edge) {
        this.addToList(edge);
    }

    public final Point getVertexPosition() {
        return this.mVertexPosition;
    }

    public final int getVertexDistance() {
        return this.mDistance;
    }

    public final MazeVertex getPreviousShortestVertex() {
        return this.mPreviousShortestVertex;
    }

    public final void setVertexDistance(int distance) {
        this.mDistance = distance;
    }

    public final void setPreviousShortestVertex(MazeVertex shortestVertex) {
        this.mPreviousShortestVertex = shortestVertex;
    }

}