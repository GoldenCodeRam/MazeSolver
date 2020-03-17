package com.uptc.prg.maze.model.data.graphmatrix;

import java.util.Objects;

/**
 * <b>SimpleList class, implementation for Adjacency Matrix</b>
 * <p>
 * Models a whole simple list of referenced lineal objects, in this case {@link Node}s. So the first
 * element has a reference to another element and so on and so forth. The {@link Node} class is
 * storing all of the data and the references, including, in this case, more information for the
 * {@link GraphAdjacencyMatrix}.
 *
 * @param <T> The type of data to store on this list.
 * @author LöwenHerz
 * @version 1.0
 * @see Node
 * @since 26/09/2019
 */
public class AdjacencyLinkedList<T> {
    private Node<T> mFirstNode = null;
    private Node<T> mEndNode = null;

    public final void insertAtBeginning(T data, int column) {
        Node<T> nodeToInsert = new Node<>(data, column);
        if (this.mFirstNode == null) {
            this.mFirstNode = nodeToInsert;
            this.mEndNode = nodeToInsert;
        } else {
            nodeToInsert.setNextNode(this.mFirstNode);
            this.mFirstNode = nodeToInsert;
        }
    }

    public final void insertAtEnd(T data, int column) {
        Node<T> nodeToInsert = new Node<>(data, column);
        if (this.mFirstNode == null) {
            this.mFirstNode = nodeToInsert;
        } else {
            this.mEndNode.setNextNode(nodeToInsert);
            this.mEndNode = nodeToInsert;
        }
    }

    public final void insertDataAt(T data, int column) {
        if (this.mFirstNode == null || this.mFirstNode.getColumn() > column) {
            this.insertAtBeginning(data, column);
        } else if (this.mEndNode.getColumn() < column) {
            this.insertAtEnd(data, column);
        } else {
            this.searchAndInsert(data, column);
        }
    }

    public final void insertRow(Node<? extends T> nodeRowToInsert) {
        Node<? extends T> rowToInsert = nodeRowToInsert;
        while (rowToInsert != null) {
            this.insertAtEnd(rowToInsert.getData(), rowToInsert.getColumn());
            rowToInsert = rowToInsert.getNext();
        }
    }

    protected final Node<T> getFirstNode() {
        return this.mFirstNode;
    }

    protected final Node<T> getEndNode() {
        return this.mEndNode;
    }

    protected final void setFirstNode(Node<T> firstNode) {
        this.mFirstNode = firstNode;
    }

    protected final void setEndNode(Node<T> endNode) {
        this.mEndNode = endNode;
    }

    protected final Node<T> search(T data, int column) {
        Node<T> foundNode = this.mFirstNode;
        while (foundNode != null && foundNode.getColumn() < column) {
            if (foundNode.getNext().getColumn() > column) {
                break;
            } else {
                foundNode = foundNode.getNext();
            }
        }
        return foundNode;
    }

    private void searchAndInsert(T data, int column) {
        Node<T> foundNode = this.search(data, column);
        if (Objects.requireNonNull(foundNode).getColumn() == column) {
            foundNode.setData(data);
        } else {
            Node<T> nodeToInsert = new Node<>(data, column, foundNode);
            foundNode.setNextNode(nodeToInsert);
        }
    }

    /**
     * <b>Node class, implementation for Adjacency Matrix</b>
     * <p>
     * Models the behaviour of a node, containing some data and a reference to another node, as a
     * simple linear list, for this implementation, the node contains the reference to a column.
     *
     * @author LöwenHerz
     * @version 1.0
     * @since 26/09/2019
     */
    public static class Node<T> {
        // Package private fields for easy read-write.
        private T mData;
        private final int mColumn;
        private Node<T> mNext = null;

        /**
         * Assigns the data and the next node for this node.
         *
         * @param data   The data to store on the node.
         * @param column The column for the data to be stored on.
         * @param next   The next node for the reference.
         */
        public Node(T data, int column, Node<T> next) {
            this.mData = data;
            this.mColumn = column;
            this.mNext = next;
        }

        /**
         * Assigns the data for the node and its column..
         *
         * @param data   The data to store on the node.
         * @param column The column for the data to be stored on.
         */
        public Node(T data, int column) {
            this.mData = data;
            this.mColumn = column;
        }

        /**
         * @return The data stored on the node.
         */
        public final T getData() {
            return this.mData;
        }

        /**
         * @return The column of this node.
         */
        public final int getColumn() {
            return this.mColumn;
        }

        /**
         * @return The next node referenced by this node.
         */
        public final Node<T> getNext() {
            return this.mNext;
        }

        /**
         * @param nextNode Sets the next node to be referenced by this node.
         */
        public final void setNextNode(Node<T> nextNode) {
            this.mNext = nextNode;
        }

        /**
         * @param data Sets the data for this node.
         */
        public final void setData(T data) {
            this.mData = data;
        }
    }
}