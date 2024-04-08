package io.github.japangiserver.base.requesttype;

import io.github.japangiserver.util.datastructure.tree.binarysearch.BinarySearchTree;
import io.github.japangiserver.util.datastructure.tree.binarysearch.Node;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class RequestTypeBinarySearchTree extends BinarySearchTree<RequestType> {

    public RequestTypeBinarySearchTree() {
        super(Comparator.comparing(RequestType::getName));
    }

    public static RequestTypeBinarySearchTree of(RequestType[] values) {
        RequestTypeBinarySearchTree binarySearchTree = new RequestTypeBinarySearchTree();
        for (RequestType requestType : values) {
            binarySearchTree.add(requestType);
        }

        return binarySearchTree;
    }

    @Override
    public RequestType search(Object o) {
        String value = (String) o;

        Node<RequestType> node = root;
        while (node != null) {
            int res = value.compareTo(node.value.getName());
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
}
