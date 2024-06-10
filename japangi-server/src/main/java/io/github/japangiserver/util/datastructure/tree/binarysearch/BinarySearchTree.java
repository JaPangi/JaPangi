package io.github.japangiserver.util.datastructure.tree.binarysearch;

import io.github.japangiserver.util.datastructure.tree.Tree;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class BinarySearchTree<T> implements Tree<T> {

    protected Node<T> root;
    protected int size;

    protected final Comparator<? super T> comparator;

    public BinarySearchTree(Comparator<? super T> comparator) {
        this.comparator = comparator;
        this.root = null;
        this.size = 0;
    }

    @Override
    public T add(T value) {
        Node<T> current = root;
        if (current == null) {
            root = new Node<T>(value, null);
            size++;
            return null;
        }

        Node<T> currentParent;
        int compResult;
        do {
            currentParent = current;
            compResult = comparator.compare(value, current.value);
            if (compResult < 0) {
                current = current.left;
            } else if (compResult > 0) {
                current = current.right;
            } else {
                return value;
            }
        } while (current != null);

        Node<T> newNode = new Node<T>(value, currentParent);
        if (compResult < 0) {
            currentParent.left = newNode;
        } else {
            currentParent.right = newNode;
        }
        size++;
        return null;
    }

    @Override
    public T remove(Object value) {

        if (root == null) {
            return null;
        }

        T oldVal = (T) value;
        Node<T> parent = null, current = root;
        boolean hasLeft = false;

        if (root == null) {
            return null;
        }

        T compValue = (T) value;

        do {
            int resComp = comparator.compare(compValue, current.value);
            if (resComp == 0) {
                break;
            }

            parent = current;
            if (resComp < 0) {
                hasLeft = true;
                current = current.left;
            } else {
                hasLeft = false;
                current = current.right;
            }
        } while (current != null);

        if (current == null) {
            return null;
        }

        if (parent == null) {
            deleteNode(current);
            size--;
            return oldVal;
        }

        if (hasLeft) {
            parent.left = deleteNode(current);
            if (parent.left != null) {
                parent.left.parent = parent;
            }
        } else {
            parent.right = deleteNode(current);
            if (parent.right != null) {
                parent.right.parent = parent;
            }
        }
        size--;
        return oldVal;
    }

    private Node<T> deleteNode(Node<T> node) {

        if (node != null) {
            if (node.left == null && node.right == null) {
                if (node == root) {
                    root = null;
                } else {
                    node = null;
                }
                return null;
            }

            if (node.left != null && node.right != null) {
                Node<T> replacement = getSuccessorAndUnlink(node);
                node.value = replacement.value;
            } else if (node.left != null) {
                if (node == root) {
                    node = node.left;
                    root = node;
                    root.parent = null;
                } else {
                    node = node.left;
                }
            } else {
                if (node == root) {
                    node = node.right;
                    root = node;
                    root.parent = null;
                } else {
                    node = node.right;
                }
            }
        }

        return node;
    }

    private Node<T> getSuccessorAndUnlink(Node<T> node) {

        Node<T> currentParent = node;
        Node<T> current = node.right;

        if (current.left == null) {
            currentParent.right = current.right;
            if (currentParent.right != null) {
                currentParent.right.parent = currentParent;
            }
            current.right = null;
            return current;

        }

        while (current.left != null) {
            currentParent = current;
            current = current.left;
        }

        currentParent.left = current.right;
        if (currentParent.left != null) {
            currentParent.left.parent = currentParent;
        }

        current.right = null;
        return current;
    }

    @Override
    public T search(Object o) {
        T value = (T) o;

        Node<T> node = root;
        while (node != null) {
            int res = comparator.compare(value, node.value);
            if (res < 0) {
                node = node.left;
            } else if (res > 0) {
                node = node.right;
            } else {
                return node.value;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        T value = (T) o;

        Node<T> node = root;
        while (node != null) {
            int res = comparator.compare(value, node.value);
            if (res < 0) {
                node = node.left;
            } else if (res > 0) {
                node = node.right;
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        size = 0;
        root = null;
    }
}
