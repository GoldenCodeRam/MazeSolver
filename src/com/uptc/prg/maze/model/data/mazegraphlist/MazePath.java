package com.uptc.prg.maze.model.data.mazegraphlist;

import com.uptc.prg.maze.model.data.graphlist.EdgeType;

import java.awt.*;

public class MazePath {
    private final Point mStartVertexPosition;
    private final int mEdgeWeight;
    private final Point mEndVertexPosition;
    private final EdgeType mEdgeType;

    /*====================================== PUBLIC METHODS ======================================*/

    public MazePath(Point startVertexPosition, int edgeWeight, Point endVertexPosition, EdgeType edgeType) {
        this.mStartVertexPosition = startVertexPosition;
        this.mEdgeWeight = edgeWeight;
        this.mEndVertexPosition = endVertexPosition;
        this.mEdgeType = edgeType;
    }

    public final Point getStartVertexPosition() {
        return this.mStartVertexPosition;
    }

    public final int getEdgeWeight() {
        return this.mEdgeWeight;
    }

    public final Point getEndVertexPosition() {
        return this.mEndVertexPosition;
    }

    public final EdgeType getEdgeType() {
        return this.mEdgeType;
    }
}
