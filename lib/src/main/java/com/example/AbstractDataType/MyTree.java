package com.example.AbstractDataType;


/**
 * Project Name:DailyTask
 * Package Name:com.example
 * Created by lahm on 2017/2/26 下午3:59 .
 * https://github.com/lamster2018
 * <p>
 * Java中的compareTo()函数用法-Comparable
 * http://blog.csdn.net/nanjifengchen/article/details/6009707
 * http://www.cnblogs.com/yueliming/archive/2013/05/22/3092576.html
 */

public class MyTree<AnyType extends Comparable<? super AnyType>> {
    private static class Node<AnyType> {
        AnyType element;
        Node<AnyType> left;
        Node<AnyType> right;

        Node(AnyType element) {
            this(element, null, null);
        }

        Node(AnyType element, Node<AnyType> left, Node<AnyType> right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }
    }

    private Node<AnyType> root;

    public MyTree() {
        root = null;
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(AnyType x) {
        return contains(x, root);
    }

    private boolean contains(AnyType x, Node<AnyType> indexRoot) {
        if (indexRoot == null) return false;
        int compareResult = x.compareTo(indexRoot.element);
        if (compareResult < 0) return contains(x, indexRoot.left);
        if (compareResult > 0) return contains(x, indexRoot.right);
        else return true;
    }

    public AnyType findMin() {
        if (isEmpty()) throw new RuntimeException("empty tree");
        return findMin(root).element;// may be null exception
    }

    private Node<AnyType> findMin(Node<AnyType> indexRoot) {
        if (indexRoot == null) return null;
        else if (indexRoot.left == null) return indexRoot;
        else return findMin(indexRoot.right);
    }

    public AnyType findMax() {
        if (isEmpty()) throw new RuntimeException("empty tree");
        return findMax(root).element;
    }

    /**
     * only changed root's quote
     *
     * @param indexRoot
     * @return
     */
    private Node<AnyType> findMax(Node<AnyType> indexRoot) {
        if (indexRoot != null) {
            while (indexRoot.right != null) {
                indexRoot = root.right;
            }
        }
        return indexRoot;
    }

    public void insert(AnyType newElement) {
        root = insert(newElement, root);
    }

    private Node<AnyType> insert(AnyType newElement, Node<AnyType> indexRoot) {
        if (indexRoot == null) return new Node<AnyType>(newElement, null, null);
        int compareResult = newElement.compareTo(indexRoot.element);
        if (compareResult < 0) indexRoot.left = insert(newElement, indexRoot.left);
        else if (compareResult > 0) indexRoot.right = insert(newElement, indexRoot.right);
        else ;//do nothing
        return indexRoot;
    }

    public void remove(AnyType whichElement) {
        root = remove(whichElement, root);
    }

    private Node<AnyType> remove(AnyType whichElement, Node<AnyType> indexRoot) {
        if (indexRoot == null) return indexRoot;//nothing to remove,do nothing
        int compareResult = whichElement.compareTo(indexRoot.element);
        if (compareResult < 0) indexRoot.left = remove(whichElement, indexRoot.left);
        else if (compareResult > 0) indexRoot.right = remove(whichElement, indexRoot.right);
            // have two children,remove the min child
        else if (indexRoot.left != null && indexRoot.right != null) {
            indexRoot.element = findMin(indexRoot.right).element;
            indexRoot.right = remove(indexRoot.element, indexRoot.right);
        } else {
            //lazy deletion
            indexRoot = (indexRoot.left == null) ? indexRoot.right : indexRoot.left;
        }
        return indexRoot;
    }

    public void printTree() {
        if (isEmpty()) System.out.println("empty tree");
        else printTree(root);
    }

    /**
     * pre-order traversal
     *
     * @param indexRoot
     */
    private void printTree(Node<AnyType> indexRoot) {
        if (indexRoot != null) {
            printTree(indexRoot.left);
            System.out.println(indexRoot.element);
            printTree(indexRoot.right);
        }
    }
}
