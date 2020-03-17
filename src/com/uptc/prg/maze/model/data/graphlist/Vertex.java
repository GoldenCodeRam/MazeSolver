package com.uptc.prg.maze.model.data.graphlist;

import com.uptc.prg.maze.model.data.graphlist.list.LinkedList;

public class Vertex<V, E extends Comparable<E>> extends LinkedList<Edge<V, E>> {
    private final V mVertexData;
    private Vertex<V, E> mPreviousVertex = null;
    private E mWeight = null;

    public Vertex(V vertexData, Edge<V, E> firstEdge, E weight) {
        this.mVertexData = vertexData;
        this.insertNewHead(firstEdge);
        this.mWeight = weight;
    }

    public Vertex(V vertexData, Edge<V, E> firstEdge) {
        this.mVertexData = vertexData;
        this.insertNewHead(firstEdge);
    }

    public Vertex(V vertexData) {
        this.mVertexData = vertexData;
    }

    public void addEdge(Edge<V, E> edge) {
        this.addToList(edge);
    }

    public V getVertexData() {
        return this.mVertexData;
    }

    public Vertex<V, E> getPreviousVertex() {
        return this.mPreviousVertex;
    }

    public E getWeight(){
        return this.mWeight;
    }

    public void setPreviousVertex(Vertex<V, E> vertex) {
        this.mPreviousVertex = vertex;
    }

    public void setWeight(E weight) {
        this.mWeight = weight;
    }
}
