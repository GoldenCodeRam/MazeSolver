package com.uptc.prg.maze.model.data.mazegraphlist;

import com.uptc.prg.maze.model.data.graphlist.EdgeType;

public class MazeEdge {
    private MazeVertex mTargetVertex;
    private int mEdgeWeight;
    private EdgeType mEdgeType;

    public MazeEdge(MazeVertex targetVertex, int edgeWeight, EdgeType edgeType) {
        this.mTargetVertex = targetVertex;
        this.mEdgeWeight = edgeWeight;
        this.mEdgeType = edgeType;
    }

    /**
     * @return The label of this edge.
     */
    public final int getEdgeWeight() {
        return this.mEdgeWeight;
    }

    public final MazeVertex getTargetVertex() {
        return this.mTargetVertex;
    }

    public final EdgeType getEdgeType() {
        return this.mEdgeType;
    }
}
