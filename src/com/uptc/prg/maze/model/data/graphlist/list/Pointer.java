package com.uptc.prg.maze.model.data.graphlist.list;

public class Pointer<T> {
    private LinkedList<T> mList;
    private LinkedList.Node<T> mPointer;

    public Pointer(LinkedList<T> list) {
        this.mList = list;
        this.mPointer = this.mList.getHead();
    }

    public final T getData() {
        return this.mPointer.getData();
    }

    public final T getDataAndNext() {
        T data = this.mPointer.getData();
        this.mPointer = this.mPointer.getNext();
        return data;
    }

    public final void nextNode() {
        this.mPointer = this.mPointer.getNext();
    }

    public final boolean isOnLastNode() {
        return this.mPointer.getNext() == null;
    }

    public final boolean isOutOfNodes() {
        return this.mPointer == null;
    }

    public final void resetPointer() {
        this.mPointer = this.mList.getHead();
    }

    public final int getListSize() {
        int size = 0;
        Pointer<T> pointer = new Pointer<>(this.mList);
        while (!pointer.isOutOfNodes()) {
            size++;
            pointer.nextNode();
        }
        return size;
    }
}
