package com.uptc.prg.maze.model.data;

/**
 * TODO: Document
 */
public class Node<T> {
    protected T mData;
    protected Node<T> mNext;

    /**
     * TODO: Document
     */
    public Node(T data, Node<T> next) {
        this.mData = data;
        this.mNext = next;
    }

    /**
     * TODO: Document
     */
    public Node(T data) {
        this.mData = data;
        this.mNext = null;
    }
}
