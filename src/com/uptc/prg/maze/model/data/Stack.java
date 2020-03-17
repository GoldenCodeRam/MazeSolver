package com.uptc.prg.maze.model.data;

/**
 * TODO: Document
 */
public class Stack<T> {
    private Node<T> mTop;

    /**
     * TODO: Document
     */
    public Stack() {
        this.mTop = null;
    }

    /**
     * TODO: Document
     */
    public boolean isEmpty() {
        return this.mTop == null;
    }

    /**
     * // TODO: Put data on the stack. And document.
     *
     * @param data The data from which will be created a new {@link Node}.
     */
    public void push(T data) {
        this.mTop = new Node<>(data, this.mTop);
    }

    /**
     * // TODO: Eliminate an element from the stack. And document.
     *
     * @return The information from the popped {@link Node}.
     */
    public T pop() {
        if (this.mTop != null) {
            T aux = this.mTop.mData;
            this.mTop = this.mTop.mNext;
            return aux;
        } else {
            return null;
        }
    }
}
