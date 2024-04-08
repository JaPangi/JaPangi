package io.github.japangiserver.util.datastructure.tree;

public interface Tree<T> {

    T add(T e);
    T remove(Object o);
    T search(Object o);
    boolean contains(Object o);
    boolean isEmpty();
    int size();
    void clear();
}
