package com.uptc.prg.maze.model.data;

/**
 * TODO: Document
 * @param <T>
 */
public class Queue<T> {
    private Node<T> mFirst;
    private Node<T> mLast;

    /**
     * TODO: Document
     */
    public Queue() {
        this.mFirst = null;
        this.mLast = null;
    }

    /**
     * TODO: Document
     * @return
     */
    public boolean isEmpty() {
        return this.mFirst == null;
    }

    /**
     * // TODO: Document adding an element to the queue.
     *
     * @param data The data from which will be created a new {@link Node}.
     */
    public void put(T data) {
        if (this.mFirst == null) {
            this.mFirst = this.mLast = new Node<>(data);
        } else {
            this.mLast = this.mLast.mNext = new Node<>(data);
        }
    }

    /**
     * // TODO: Document the elimination of an element from the queue.
     *
     * @return The information from the popped {@link Node}.
     */
    public T get() {
        if (this.mFirst != null) {
            T aux = this.mFirst.mData;
            this.mFirst = this.mFirst.mNext;
            return aux;
        } else {
            return null;
        }
    }
}

