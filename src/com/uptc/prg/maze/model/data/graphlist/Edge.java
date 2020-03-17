package com.uptc.prg.maze.model.data.graphlist;

/**
 * An edge for a graph, containing a target {@link Vertex} and a label.
 *
 * @param <V> The vertex type of data stored in the target vertex.
 * @param <E> The label data type for this edge label.
 */
public class Edge<V, E extends Comparable<E>> {
    private Vertex<V, E> mTargetVertex;
    private E mEdgeLabel;
    private EdgeType mEdgeType;

    /**
     * Main constructor for the class, takes a target {@link Vertex}, a label and an
     * {@link EdgeType} for a new edge.
     *
     * @param targetVertex The target vertex of this edge.
     * @param edgeLabel    The label for this edge.
     * @param edgeType     The label type.
     */
    public Edge(Vertex<V, E> targetVertex, E edgeLabel, EdgeType edgeType) {
        this.mTargetVertex = targetVertex;
        this.mEdgeLabel = edgeLabel;
        this.mEdgeType = edgeType;
    }

    /**
     * @return The label of this edge.
     */
    public final E getEdgeLabel() {
        return this.mEdgeLabel;
    }

    /**
     * @return The target {@link Vertex} of this edge.
     */
    public final Vertex<V, E> getTargetVertex() {
        return this.mTargetVertex;
    }

    public final EdgeType getEdgeType() {
        return this.mEdgeType;
    }
}
