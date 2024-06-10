package io.github.japangiserver.util.datastructure.tree.binarysearch;

public class Node<T> {

    public T value;
    public Node<T> left;
    public Node<T> right;
    public Node<T> parent;

    Node(T value) {
        this(value, null);
    }

    Node(T value, Node<T> parent) {
        this.value = value;
        this.parent = parent;
        this.right = null;
        this.left = null;
    }
}
