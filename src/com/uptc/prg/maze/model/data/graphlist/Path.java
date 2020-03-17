package com.uptc.prg.maze.model.data.graphlist;

public class Path<V, E extends Comparable<E>> {
    private final V mStartVertexData;
    private final E mEdgeData;
    private final V mEndVertexData;
    private final EdgeType mEdgeType;

    /*====================================== PUBLIC METHODS ======================================*/

    public Path(V startVertexData, E edgeData, V endVertexData, EdgeType edgeType) {
        this.mStartVertexData = startVertexData;
        this.mEdgeData = edgeData;
        this.mEndVertexData = endVertexData;
        this.mEdgeType = edgeType;
    }

    public final V getStartVertexData() {
        return this.mStartVertexData;
    }

    public final E getEdgeData() {
        return this.mEdgeData;
    }

    public final V getEndVertexData() {
        return this.mEndVertexData;
    }

    public final EdgeType getEdgeType() {
        return this.mEdgeType;
    }
}
