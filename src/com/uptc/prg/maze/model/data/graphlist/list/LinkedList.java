package com.uptc.prg.maze.model.data.graphlist.list;

import java.util.Comparator;

/**
 * <b>SimpleList class</b>
 * <p>
 * Models a whole simple list of referenced lineal objects, in this case {@link Node}s. So the first
 * element has a reference to another element and so on and so forth. The {@link Node} class is
 * storing all of the data and the references.
 *
 * @param <T> The type of data to store on this list.
 * @author LöwenHerz
 * @version 1.0
 * @see Node
 * @since 26/09/2019
 */
public class LinkedList<T> {
    // Reference to the first node and a comparator for the order of the nodes.
    private Node<T> mHead = null;
    private Comparator<? super T> mComparator = null;

    /**
     * Constructor for the general usage of the linked list, without a comparator.
     */
    public LinkedList() {
    }

    /**
     * Constructor for the general usage of the linked list, without a comparator.
     */
    public LinkedList(T data) {
        this.addToList(data);
    }

    /**
     * Constructor for the class, which sets the {@link Comparator} and sets the head {@link Node}
     * to null.
     *
     * @param comparator The comparator for the ordering of the {@link Node} list.
     */
    public LinkedList(Comparator<? super T> comparator) {
        this.mComparator = comparator;
    }

    /*====================================== PUBLIC METHODS ======================================*/

    /**
     * @return Whether the head {@link Node} is null or not.
     */
    public final boolean isEmpty() {
        return this.mHead == null;
    }

    /**
     * @return The data stored on the head {@link Node}, if the head is empty.
     */
    public final Node<T> getHead() {
        return this.mHead;
    }

    /**
     * It can have two options:
     * <ul>
     *     <li>
     *     If the head {@link Node} of this object is null, sets a new {@link Node} object to be the
     *     head.
     *     </li>
     *     <li>
     *     If the head is not null, it takes the data from the parameter, creates a new {@link Node}
     *     and adds it to the list; if the {@link Comparator} of this object is null, it will add
     *     the new {@link Node} on the last position, if not, it will order it given the comparator
     *     rule.
     *     </li>
     * </ul>
     *
     * @param data The data to put on the {@link Node} and add to this linked list object.
     */
    public final void addToList(T data) {
        if (this.mHead != null) {
            if (this.mComparator != null) {
                this.addSort(data);
            } else {
                this.addLast(data);
            }
        } else {
            this.mHead = new Node<>(data);
        }
    }

    /**
     * Inserts the data into a new {@link Node} and it becomes the new head, <b>only</b> if there is
     * not a comparator given to the linked list.
     *
     * @param data The data to put on a {@link Node} and on the head of this list.
     * @see Node
     */
    public final void insertNewHead(T data) {
        if (this.mComparator == null) {
            this.mHead = new Node<>(data, this.mHead);
        }
    }

    public final void removeNode(T data) {
        Node<T> currentNode = this.mHead;
        Node<T> nextNode = currentNode.getNext();
        if (this.mHead.getData().equals(data)) {
            this.mHead = this.mHead.getNext();
            return;
        }
        while (currentNode != null) {
            if (nextNode.getData().equals(data)) {
                currentNode.setNext(nextNode.getNext());
            } else {
                currentNode = currentNode.getNext();
            }
        }
    }

    /*====================================== PRIVATE METHODS =====================================*/

    /**
     * Creates a {@link Node} and adds it to the end of the list with the given data or value.
     *
     * @param data The data or value to add to the end of the list with a {@link Node}.
     */
    private void addLast(T data) {
        Node<T> aux = this.mHead;
        Node<T> next = aux.getNext();
        while (next != null) {
            aux = next;
            next = aux.getNext();
        }
        aux.setNext(new Node<>(data));
    }

    /**
     * Creates a {@link Node} and adds it given the rule of the {@link Comparator} object.
     *
     * @param data The data to add to this linked list object, with a {@link Node}.
     */
    private void addSort(T data) {
        if (this.mComparator.compare(this.mHead.getData(), data) > 0) {
            this.mHead = new Node<>(data, this.mHead);
        } else {
            Node<T> nodeNow = this.mHead;
            Node<T> nodeBefore;
            int comparision;
            do {
                nodeBefore = nodeNow;
                nodeNow = nodeNow.getNext();
                if (nodeNow == null) {
                    break;
                }
                comparision = this.mComparator.compare(nodeNow.getData(), data);
            } while (comparision > 0);
            nodeBefore.setNext(new Node<>(data, nodeNow));
        }
    }

    /**
     * <b>Node class</b>
     * <p>
     * Models the behaviour of a node, containing some data and a reference to another node, as a simple
     * linear list.
     *
     * @author LöwenHerz
     * @version 1.0
     * @since 26/09/2019
     */
    public static class Node<T> {
        // Package private fields for easy read-write.
        private T mData;
        private Node<T> mNext = null;

        /**
         * Assigns the data and the next node for this node.
         *
         * @param data The data to store on the node.
         * @param next The next node for the reference.
         */
        public Node(T data, Node<T> next) {
            this.mData = data;
            this.mNext = next;
        }

        /**
         * Assigns the data for the node and sets the next node to null.
         *
         * @param data The data to store on the node.
         */
        public Node(T data) {
            this.mData = data;
        }

        /**
         * @return The data stored on the node.
         */
        public final T getData() {
            return this.mData;
        }

        /**
         * @param data The data to store on the node.
         */
        public final void setData(T data) {
            this.mData = data;
        }

        /**
         * @return The next node referenced by this node.
         */
        public final Node<T> getNext() {
            return this.mNext;
        }

        /**
         * @param nextNode The next node to be referenced by this node.
         */
        public final void setNext(Node<T> nextNode) {
            this.mNext = nextNode;
        }
    }
}